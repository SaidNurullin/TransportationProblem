public class NorthWestCornerMethod {
    private final Matrix supplyCoefficients;
    private final Matrix costsCoefficients;
    private final Matrix demandCoefficients;

    public NorthWestCornerMethod(Matrix coefficientsOfSupply,
            Matrix coefficientsOfCosts,
            Matrix coefficientsOfDemand) {
        supplyCoefficients = coefficientsOfSupply;
        costsCoefficients = coefficientsOfCosts;
        demandCoefficients = coefficientsOfDemand;
    }

    /**
     *
     * @param a 1st double number
     * @param b 2nd double number
     * @return minimum from numbers a and b
     */
    private static double min(double a, double b) {
        return Math.min(a, b);
    }

    /**
     *
     * @return initial solution X0 obtained using North-West corner method
     */
    public Matrix solve() {
        Matrix initialSolution = new Matrix(costsCoefficients.getRows(), costsCoefficients.getColumns());
        return initialSolution;
    }
}
