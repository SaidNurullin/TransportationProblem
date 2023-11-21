public class NorthWestCornerMethod {
    private final Matrix supplyCoefficients;
    private final Matrix costsCoefficients;
    private final Matrix demandCoefficients;

    /**
     * Constructs a north west corner method solver with specified supply, demand,
     * and costs.
     * The sum of supply coefficients is assumed to be equal to the sum of demand
     * coefficients.
     * If this condition is not satisfied, the behavior of solver is undefined.
     * 
     * @param coefficientsOfSupply Supply coefficients
     * @param coefficientsOfCosts  Cost coefficients
     * @param coefficientsOfDemand Demand coefficients
     */
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
     * This method does not change the state of solver, thus, when invoked
     * several times, the result is consistent between calls.
     * 
     * @return initial solution X0 obtained using North-West corner method
     */
    public Matrix solve() {
        Matrix solution = new Matrix(costsCoefficients.getRows(), costsCoefficients.getColumns());
        Matrix demand = new Matrix(demandCoefficients);
        Matrix supply = new Matrix(supplyCoefficients);

        int i = 0;
        int j = 0;
        while (i < solution.getRows() && j < solution.getColumns()) {
            double allocation = min(demandCoefficients.getElement(j, 0), supplyCoefficients.getElement(i, 0));
            demand.setElement(j, 0, demand.getElement(j, 0) - allocation);
            supply.setElement(i, 0, supply.getElement(i, 0) - allocation);

            solution.setElement(i, j, allocation);

            if (demand.getElement(j, 0) == 0) {
                j += 1;
            }

            if (supply.getElement(i, 0) == 0) {
                i += 1;
            }
        }

        return solution;
    }
}
