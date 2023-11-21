import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MethodOfVogel {
    private final Matrix supplyCoefficients;
    private final Matrix costsCoefficients;
    private final Matrix demandCoefficients;

    public MethodOfVogel(Matrix coefficientsOfSupply,
                         Matrix coefficientsOfCosts,
                         Matrix coefficientsOfDemand){
        supplyCoefficients = coefficientsOfSupply;
        costsCoefficients = coefficientsOfCosts;
        demandCoefficients = coefficientsOfDemand;
    }

    /**
     * In sequence: 2 6 1 0 5 8
     * First min = 0
     * Second min = 1
     * if column == -1, then search min inside costsCoefficients in row with index 'row'
     * if row == -1, then search min inside costsCoefficients in column with index 'column'
     * if row != -1 && column != -1, then return -1
     * @param column column index where to look for min.
     * @param row row index where to look for min.
     * @return index of first minimum value within the provided row or column, or -1 if it doesn't exist.
     */
    private int firstMin(int column, int row){
        if (row == -1 && column == -1)
            return -1;

        double firstMin = Double.MAX_VALUE;
        int index = -1;
        if (column == -1){
            for (int i = 0; i < costsCoefficients.getColumns(); ++i){
                double element = costsCoefficients.getElement(row, i);
                if (element < firstMin){
                    firstMin = element;
                    index = i;
                }
            }
        }
        else{
            for (int i = 0; i < costsCoefficients.getRows(); ++i){
                double element = costsCoefficients.getElement(i, column);
                if (element < firstMin){
                    firstMin = element;
                    index = i;
                }
            }
        }

        return index;
    }

    /**
     * In sequence: 2 6 1 0 5 8
     * First min = 0
     * Second min = 1
     * if column == -1, then search min inside costsCoefficients in row with index 'row'
     * if row == -1, then search min inside costsCoefficients in column with index 'column'
     * if row != -1 && column != -1, then return -1
     * @param column column index where to look for min.
     * @param row row index where to look for min.
     * @param firstMinIdx the index of first minimum in the provided row or column
     * @return index of second minimum value within the provided row or column, or -1 if it doesn't exist.
     */
    private int secondMin(int column, int row, int firstMinIdx){
        if (row == -1 && column == -1)
            return -1;

        double secondMin = Double.MAX_VALUE;
        int index = -1;
        if (column == -1){
            for (int i = 0; i < costsCoefficients.getColumns(); ++i){
                if (i == firstMinIdx)
                    continue;

                double element = costsCoefficients.getElement(row, i);
                if (element < secondMin){
                    secondMin = element;
                    index = i;
                }
            }
        }
        else{
            for (int i = 0; i < costsCoefficients.getRows(); ++i){
                if (i == firstMinIdx)
                    continue;

                double element = costsCoefficients.getElement(i, column);
                if (element < secondMin){
                    secondMin = element;
                    index = i;
                }
            }
        }

        return index;
    }

    /**
     * @return second array of differences of minimal elements of rows.
     */
    private ArrayList<Double> getRowDifferences(){
        int size = costsCoefficients.getRows();
        ArrayList<Double> differences = new ArrayList<>(Collections.nCopies(size, 0.0));

        for (int row = 0; row < size; ++row){
            if (supplyCoefficients.getElement(row, 0) == 0){
                differences.set(row, Double.MAX_VALUE);
                continue;
            }

            int first = firstMin(-1, row);
            int second = secondMin(-1, row, first);

            if (first == -1)
                differences.set(row, Double.MAX_VALUE);
            else if (second == -1)
                differences.set(row, 0.0);
            else
                differences.set(row, costsCoefficients.getElement(row, second) -
                        costsCoefficients.getElement(row, first));
        }

        return differences;
    }

    /**
     * @return second array of differences of minimal elements of columns.
     */
    private ArrayList<Double> getColumnDifferences(){
        int size = costsCoefficients.getColumns();
        ArrayList<Double> differences = new ArrayList<>(Collections.nCopies(size, 0.0));

        for (int column = 0; column < size; ++column){
            if (demandCoefficients.getElement(column, 0) == 0){
                differences.set(column, Double.MAX_VALUE);
                continue;
            }

            int first = firstMin(column, -1);
            int second = secondMin(column, -1, first);
            if (first == -1)
                differences.set(column, Double.MAX_VALUE);
            else if (second == -1)
                differences.set(column, 0.0);
            else
                differences.set(column, costsCoefficients.getElement(second, column) -
                        costsCoefficients.getElement(first, column));
        }

        return differences;
    }

    /**
     * Find the max element ignoring the element which value equal Double.MAX_VALUE
     * @param array array
     * @return index of max element
     */
    private int getIndexOfMaxElement(ArrayList<Double> array){
        Double maxElement = -1.0;
        int index = -1;
        for (int i = 0; i < array.size(); ++i){
            Double element = array.get(i);
            if (element == Double.MAX_VALUE)
                continue;

            if (element > maxElement){
                maxElement = element;
                index = i;
            }
        }

        return index;
    }

    /**
     *
     * @return ArrayList of size 2: 1st value is 0 if max difference found in the rowDifferences,
     * 1st value is 1 if max difference found in the columnDifferences.
     * 1st otherwise value is -1
     * 2nd value is the index of the maximum value from rowDifferences and columnDifferences
     */
    private ArrayList<Integer> maxDifference(){
        ArrayList<Double> rowDifferences = getRowDifferences();
        ArrayList<Double> columnDifferences = getColumnDifferences();

        int rowIndex = getIndexOfMaxElement(rowDifferences);
        int columnIndex = getIndexOfMaxElement(columnDifferences);

        if (rowIndex == -1 && columnIndex == -1)
            return new ArrayList<>(Arrays.asList(-1, -1));
        else if (rowIndex == -1)
            return new ArrayList<>(Arrays.asList(1, columnIndex));
        else if (columnIndex == -1)
            return new ArrayList<>(Arrays.asList(0, rowIndex));

        return rowDifferences.get(rowIndex) >= columnDifferences.get(columnIndex) ?
                new ArrayList<>(Arrays.asList(0, rowIndex)) : new ArrayList<>(Arrays.asList(1, columnIndex));
    }

    /**
     * if column == -1, then search index of min value inside costsCoefficients in row with index 'row'
     * if row == -1, then search index of min value inside costsCoefficients in column with index 'column'
     * if row != -1 && column != -1, then return -1
     * @param column column index where to look for min.
     * @param row row index where to look for min.
     * @return the index of minimum value within the provided row or column.
     */
    private int minCoefficientIdx(int column, int row){
        return firstMin(column, row);
    }
    /**
     *
     * @param a 1st double number
     * @param b 2nd double number
     * @return minimum from numbers a and b
     */
    private double min(double a, double b){
        return Math.min(a, b);
    }

    /**
     * if the row or column is zero, we reset the cost in the table
     * @param row the index of the row that we check for zeroing (value = 0)
     * @param column the index of the column that we check for zeroing (value = 0)
     */
    private void blockEmptyRowAndColumnByPoint(int row, int column){
        if (supplyCoefficients.getElement(row, 0) == 0)
            for (int i = 0; i < costsCoefficients.getColumns(); ++i)
                costsCoefficients.setElement(row, i, Double.MAX_VALUE);
        if (demandCoefficients.getElement(column, 0) == 0)
            for (int i = 0; i < costsCoefficients.getRows(); ++i)
                costsCoefficients.setElement(i, column, Double.MAX_VALUE);
    }

    /**
     *
     * @return initial solution X0 obtained using Vogel’s approximation method.
     */
    public Matrix solve(){
        Matrix initialSolution = new Matrix(costsCoefficients.getRows(), costsCoefficients.getColumns());
        for (int i = 0; i < costsCoefficients.getRows(); ++i)
            for (int j = 0; j < costsCoefficients.getColumns(); ++j)
                initialSolution.setElement(i, j, 0);

        int k = 0;
        while (true){
            ArrayList<Integer> place = maxDifference();
            int type = place.get(0);
            if (type == -1)
                break;

            int row, column;
            if (type == 0){
                row = place.get(1);
                column = minCoefficientIdx(-1, row);
            }
            else{
                column = place.get(1);
                row = minCoefficientIdx(column, -1);
            }

            double supply = supplyCoefficients.getElement(row, 0);
            double demand = demandCoefficients.getElement(column, 0);

            double minValue = min(supply, demand);

            initialSolution.setElement(row, column, minValue);

            costsCoefficients.setElement(row, column, Double.MAX_VALUE); // has never been used
            supplyCoefficients.setElement(row, 0, supply - minValue);
            demandCoefficients.setElement(column, 0, demand - minValue);
            blockEmptyRowAndColumnByPoint(row, column);
        }

        return initialSolution;
    }
}