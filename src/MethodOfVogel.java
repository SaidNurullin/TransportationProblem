import java.util.ArrayList;

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
     * @return first minimum value within the provided row or column.
     */
    private double firstMin(int column, int row){
        double min = Double.MAX_VALUE;

        return min;
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
     * @return second minimum value within the provided row or column.
     */
    private double secondMin(int column, int row, int firstMinIdx){
        double secondMin = Double.MAX_VALUE;

        return secondMin;
    }

    /**
     *
     * @param rowDifferences list of differences of first min and second min for rows of costsCoefficients
     * @param columnDifferences list of differences of first min and second min for columns of costsCoefficients
     * @return ArrayList of size 2: 1st value is 0 if max difference found in the rowDifferences,
     * 1st value is 1 if max difference found in the columnDifferences.
     * 2nd value is the maximum value from rowDifferences and columnDifferences
     */
    private ArrayList<Double> maxDifference(ArrayList<Double> rowDifferences, ArrayList<Double> columnDifferences){
        ArrayList<Double> place = new ArrayList<>(2);
        return place;
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
        int idx = 0;

        return idx;
    }
    /**
     *
     * @param a 1st double number
     * @param b 2nd double number
     * @return minimum from numbers a and b
     */
    private double min(double a, double b){
        return 0;
    }

    /**
     *
     * @return initial solution X0 obtained using Vogel’s approximation method.
     */
    public Matrix solve(){
        Matrix initialSolution = new Matrix(costsCoefficients.getRows(), costsCoefficients.getColumns());
        return initialSolution;
    }
}
