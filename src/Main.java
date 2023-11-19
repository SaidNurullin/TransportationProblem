public class Main {
    public static void main(String[] args) {
        InputReader reader = new InputReader();
        int numOfSupply = reader.readInt();
        int numOfDemand = reader.readInt();
        Matrix supplyCoefficients = new Matrix(numOfSupply, 1);
        reader.readMatrixNx1(supplyCoefficients);
        Matrix costsCoefficients = new Matrix(numOfSupply, numOfDemand);
        reader.readMatrixNxM(costsCoefficients);
        Matrix demandCoefficients = new Matrix(numOfDemand, 1);
        reader.readMatrixNx1(demandCoefficients);
        reader.close();

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

        printer.printSolution(initialSolution1);
        printer.printSolution(initialSolution2);
        printer.printSolution(initialSolution3);
    }
}