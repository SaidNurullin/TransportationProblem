import java.util.ArrayList;
import java.util.List;

public class MethodOfRussell {
    private Matrix supplyCoefficients;
    private Matrix costsCoefficients;
    private Matrix demandCoefficients;

    public MethodOfRussell(Matrix coefficientsOfSupply,
                         Matrix coefficientsOfCosts,
                         Matrix coefficientsOfDemand){
        supplyCoefficients = coefficientsOfSupply;
        costsCoefficients = coefficientsOfCosts;
        demandCoefficients = coefficientsOfDemand;
    }

    /**
     *
     * @param column column index where to look for max.
     * @return max value from the costsCoefficients in the column with index 'column'
     */
    private double maxColumnCoefficient(int column, List<Integer> ignoredRows){
        double max = 0;
        for (int i = 0; i < costsCoefficients.getRows(); i++){
            if(!ignoredRows.contains(i) && costsCoefficients.getElement(i, column) > max){
                max = costsCoefficients.getElement(i, column);
            }
        }
        return max;
    }
    /**
     *
     * @param row row index where to look for max.
     * @return max value from the costsCoefficients in the column with index 'column'
     */
    private double maxRowCoefficient(int row, List<Integer> ignoredColumns){
        double max = 0;
        for (int i = 0; i < costsCoefficients.getColumns(); i++){
            if(!ignoredColumns.contains(i) && costsCoefficients.getElement(row, i) > max){
                max = costsCoefficients.getElement(row, i);
            }
        }
        return max;
    }

    /**
     *
     * @param rowCoefficients List of coefficients, each coefficient is max coefficient from i-th row
     * @param columnCoefficients List of coefficients, each coefficient is max coefficient from i-th column
     * @return Matrix of deltas, delta(i, j) = c(i, j) - u(i) - v(j);
     * c - costsCoefficients;
     * u - rowCoefficients;
     * v - columnCoefficients.
     */
    private Matrix deltasMatrix(List<Double> rowCoefficients, List<Double> columnCoefficients,
                                List<Integer> ignoredRows, List<Integer> ignoredColumns){
        Matrix deltas = new Matrix(costsCoefficients.getRows(), costsCoefficients.getColumns());
        for(int i = 0; i < rowCoefficients.size(); i++){
            for (int j = 0; j < columnCoefficients.size(); j++){
                if(!ignoredRows.contains(i) && !ignoredColumns.contains(j)) {
                    deltas.setElement(i, j, costsCoefficients.getElement(i, j) -
                            rowCoefficients.get(i) - columnCoefficients.get(j));
                }
            }
        }
        return deltas;
    }
    private List<Integer> largestNegativeDeltaCoordinates(Matrix deltas){
        List<Integer> coordinates = new ArrayList<>();
        coordinates.add(0);
        coordinates.add(0);
        double min = 0;
        for (int i = 0; i < deltas.getRows(); i++){
            for (int j = 0; j < deltas.getColumns(); j++){
                if(deltas.getElement(i, j) < min){
                    min = deltas.getElement(i, j);
                    coordinates.set(0, i);
                    coordinates.set(1, j);
                }
            }
        }
        return coordinates;
    }
    /**
     *
     * @param a 1st double number
     * @param b 2nd double number
     * @return minimum from numbers a and b.
     */
    private double min(double a, double b){
        return a < b ? a: b;
    }
    /**
     *
     * @return initial solution X0 obtained using  Russell’s approximation method
     */
    public Matrix solve(){
        Matrix initialSolution = new Matrix(costsCoefficients.getRows(), costsCoefficients.getColumns());
        List<Integer> ignoredColumns = new ArrayList<>();
        List<Integer> ignoredRows = new ArrayList<>();
        while (true) {
            List<Double> rowCoefficients = new ArrayList<>();
            List<Double> columnCoefficients = new ArrayList<>();
            for (int i = 0; i < costsCoefficients.getRows(); i++){
                rowCoefficients.add(0.0);
            }
            for (int i = 0; i < costsCoefficients.getColumns(); i++){
                columnCoefficients.add(0.0);
            }

            for (int i = 0; i < costsCoefficients.getRows(); i++) {
                if(!ignoredRows.contains(i)){
                    rowCoefficients.set(i, maxRowCoefficient(i, ignoredColumns));
                }
            }
            for (int i = 0; i < costsCoefficients.getColumns(); i++) {
                if(!ignoredColumns.contains(i)) {
                    columnCoefficients.set(i, maxColumnCoefficient(i, ignoredRows));
                }
            }
            Matrix deltas = deltasMatrix(rowCoefficients, columnCoefficients, ignoredRows, ignoredColumns);
            List<Integer> minDeltaCoordinates = largestNegativeDeltaCoordinates(deltas);
            int x = minDeltaCoordinates.get(0);
            int y = minDeltaCoordinates.get(1);
            double supply = supplyCoefficients.getElement(x, 0);
            double demand = demandCoefficients.getElement(y, 0);
            if (min(supply, demand) == supply) {
                initialSolution.setElement(x, y, supply);
                demandCoefficients.setElement(y, 0, demand - supply);
                ignoredRows.add(x);
            } else {
                initialSolution.setElement(x, y, demand);
                supplyCoefficients.setElement(x, 0, supply - demand);
                ignoredColumns.add(y);
            }
            if(ignoredRows.size() == costsCoefficients.getRows() ||
                    ignoredColumns.size() == costsCoefficients.getColumns()){
                break;
            }
        }
        return initialSolution;
    }
}
