import java.util.Scanner;

public class InputReader{
    private final Scanner scanner;

    public InputReader() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads integer value from input
     * @return integer value read from input.
     */
    public int readInt() {
        return scanner.nextInt();
    }

    /**
     * Reads column vector (Nx1 matrix) from input and puts inside provided buffer 'column'
     * @param column buffer for the column vector (Nx1 matrix) read from input.
     */
    public void readMatrixNx1(Matrix column) {
        int rows = column.getRows();
        for (int i = 0; i < rows; i++) {
            double value = scanner.nextDouble();
            column.setElement(i, 0, value);
        }
    }

    /**
     * Reads NxM matrix from input and puts inside provided buffer 'matrix'
     * @param matrix buffer for the NxM matrix read from input.
     */
    public void readMatrixNxM(Matrix matrix) {
        int rows = matrix.getRows();
        int cols = matrix.getColumns();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double value = scanner.nextDouble();
                matrix.setElement(i, j, value);
            }
        }
    }


}
