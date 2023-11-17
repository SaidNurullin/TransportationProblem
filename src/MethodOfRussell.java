import java.util.ArrayList;
import java.util.List;

public class MethodOfRussell {
    private final Matrix supplyCoefficients;
    private final Matrix costsCoefficients;
    private final Matrix demandCoefficients;

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
    private double maxColumnCoefficient(int column){
        double max = 0;
        return max;
    }
    /**
     *
     * @param row row index where to look for max.
     * @return max value from the costsCoefficients in the column with index 'column'
     */
    private double maxRowCoefficient(int row){
        double max = 0;
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
    private Matrix deltasMatrix(List<Double> rowCoefficients, List<Double> columnCoefficients){
        Matrix deltas = new Matrix(costsCoefficients.getRows(), costsCoefficients.getColumns());
        return deltas;
    }
    private List<Integer> largestNegativeDeltaCoordinates(Matrix deltas){
        List<Integer> coordinates = new ArrayList<>(2);
        return coordinates;
    }
    /**
     *
     * @param a 1st double number
     * @param b 2nd double number
     * @return minimum from numbers a and b.
     */
    private double min(double a, double b){
        return 0;
    }
    /**
     *
     * @return initial solution X0 obtained using  Russell’s approximation method
     */
    public Matrix solve(){
        Matrix initialSolution = new Matrix(costsCoefficients.getRows(), costsCoefficients.getColumns());
        return initialSolution;
    }
}
