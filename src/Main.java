public class Main {
    public static void main(String[] args) {
        InputReader reader = new InputReader();
        System.out.println("Enter the number of sources:");
        int numOfSupply = reader.readInt();
        System.out.println("Enter the number of destinations:");
        int numOfDemand = reader.readInt();
        Matrix supplyCoefficients = new Matrix(numOfSupply, 1);
        System.out.println("Enter a vector of coefficients of supply - S:");
        reader.readMatrixNx1(supplyCoefficients);
        Matrix costsCoefficients = new Matrix(numOfSupply, numOfDemand);
        System.out.println("Enter a matrix of coefficients of costs - C:");
        reader.readMatrixNxM(costsCoefficients);
        Matrix demandCoefficients = new Matrix(numOfDemand, 1);
        System.out.println("Enter a vector of coefficients of demand - D:");
        reader.readMatrixNx1(demandCoefficients);

        InputValidator validator = new InputValidator();
        boolean isApplicable = validator.isMethodApplicable(supplyCoefficients, costsCoefficients, demandCoefficients);
        boolean isBalanced = validator.isProblemBalanced(supplyCoefficients, demandCoefficients);

        if(!isApplicable){
            System.out.println("The method is not applicable!");
            return;
        }
        if(!isBalanced){
            System.out.println("The problem is not balanced!");
            return;
        }

        NorthWestCornerMethod method1 = new NorthWestCornerMethod(new Matrix(supplyCoefficients),
                new Matrix(costsCoefficients), new Matrix(demandCoefficients));
        MethodOfVogel method2 = new MethodOfVogel(new Matrix(supplyCoefficients),
                new Matrix(costsCoefficients), new Matrix(demandCoefficients));
        MethodOfRussell method3 = new MethodOfRussell(new Matrix(supplyCoefficients),
                new Matrix(costsCoefficients), new Matrix(demandCoefficients));

        Matrix initialSolution1 = method1.solve();
        Matrix initialSolution2 = method2.solve();
        Matrix initialSolution3 = method3.solve();

        OutputPrinter printer = new OutputPrinter();
        printer.printTable(supplyCoefficients, costsCoefficients, demandCoefficients);

        System.out.println("Initial basic feasible solution - x0 using North-West corner method:");
        printer.printSolution(initialSolution1);
        System.out.println("Initial basic feasible solution - x0 using Vogel’s approximation method:");
        printer.printSolution(initialSolution2);
        System.out.println("Initial basic feasible solution - x0 using Russell’s approximation method:");
        printer.printSolution(initialSolution3);
    }
}

