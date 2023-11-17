import java.util.Scanner;

public class InputReader implements AutoCloseable {
    private final Scanner scanner;

    public InputReader() {
        scanner = new Scanner(System.in);
    }

    public int readInt() {
        return scanner.nextInt();
    }

    public void readMatrixNx1(Matrix column) {
        int rows = column.getRows();
        for (int i = 0; i < rows; i++) {
            double value = scanner.nextDouble();
            column.setElement(i, 0, value);
        }
    }

    public void readMatrixNxN(Matrix matrix) {
        int rows = matrix.getRows();
        int cols = matrix.getColumns();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double value = scanner.nextDouble();
                matrix.setElement(i, j, value);
            }
        }
    }

    @Override
    public void close() {
        scanner.close();
    }
}
