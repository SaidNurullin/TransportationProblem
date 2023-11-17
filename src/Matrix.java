public class Matrix {
    private final int rows;
    private final int columns;
    private final double[][] data;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.data = new double[rows][columns];
    }

    public Matrix(Matrix m) {
        this.rows = m.rows;
        this.columns = m.columns;
        this.data = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                setElement(i, j, m.getElement(i, j));
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public double getElement(int row, int column) {
        return data[row][column];
    }

    public void setElement(int row, int column, double value) {
        data[row][column] = value;
    }

    private double[] getRow(int idx) {
        return data[idx];
    }

    private void setRow(int idx, double[] row) {
        data[idx] = row;
    }

    public Matrix transpose(){
        Matrix result = new Matrix(getColumns(), getRows());
        for(int i = 0; i<result.getRows(); i++){
            for(int j = 0; j<result.getColumns(); j++){
                result.setElement(i, j, getElement(j, i));
            }
        }
        return result;
    }

    public Matrix subtract(Matrix other) {
        Matrix result = new Matrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.setElement(i, j, getElement(i, j) - other.getElement(i, j));
            }
        }

        return result;
    }

    public Matrix add(Matrix other) {
        Matrix result = new Matrix(rows, columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.setElement(i, j, getElement(i, j) + other.getElement(i, j));
            }
        }

        return result;
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix result = new Matrix(a.rows, b.columns);

        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < b.columns; j++) {
                double sum = 0;
                for (int k = 0; k < a.columns; k++) {
                    sum += a.getElement(i, k) * b.getElement(k, j);
                }
                result.setElement(i, j, sum);
            }
        }

        return result;
    }

    public Matrix inverse() {
        Matrix identity = new Matrix(getColumns(), getRows());

        for (int i = 0; i < getRows(); ++i) {
            identity.setElement(i, i, 1);
        }

        Matrix output = new Matrix(identity);
        Matrix input = new Matrix(this);

        for (int j = 0; j < input.getColumns(); ++j) {
            if (Math.abs(input.getElement(j, j)) < 1e-10) {
                input.setElement(j, j, 0);
            }

            Matrix p = new Matrix(identity);
            double maxElem = input.getElement(j, j);
            int idx = j;

            for (int k = j; k < input.getRows(); ++k) {
                if (Math.abs(input.getElement(k, j)) < 1e-10) {
                    input.setElement(k, j, 0);
                }

                if (Math.abs(input.getElement(k, j)) > maxElem) {
                    maxElem = Math.abs(input.getElement(k, j));
                    idx = k;
                }
            }

            if (idx != j) {
                double[] temp = p.getRow(idx);
                p.setRow(idx, p.getRow(j));
                p.setRow(j, temp);
                input = multiply(p, input);
                output = multiply(p, output);
            }

            for (int i = j + 1; i < input.getRows(); ++i) {
                if (Math.abs(input.getElement(i, j)) < 1e-10) {
                    input.setElement(i, j, 0);
                }

                if (input.getElement(i, j) == 0) {
                    continue;
                }

                Matrix e = new Matrix(identity);
                e.setElement(i, j, -input.getElement(i, j) / input.getElement(j, j));
                input = multiply(e, input);
                output = multiply(e, output);
            }
        }

        for (int j = input.getColumns() - 1; j >= 0; j--) {
            for (int i = input.getRows() - 1; i >= 0; i--) {
                if (Math.abs(input.getElement(i, j)) < 1e-10) {
                    input.setElement(i, j, 0);
                }

                if (input.getElement(i, j) == 0) {
                    continue;
                }

                Matrix e = new Matrix(identity);
                e.setElement(i, j, -input.getElement(i, j) / input.getElement(j, j));
                input = multiply(e, input);
                output = multiply(e, output);
            }
        }

        Matrix scale = new Matrix(identity);

        for (int i = 0; i < input.getRows(); ++i) {
            scale.setElement(i, i, 1 / input.getElement(i, i));
        }

        output = multiply(scale, output);

        return output;
    }

    public Matrix eliminateRow(int row){
        Matrix result = new Matrix(getRows()-1, getColumns());
        for (int i = 0; i < row; i++){
            for (int j = 0; j < getColumns(); j++){
                result.setElement(i, j, getElement(i, j));
            }
        }
        for (int i = row+1; i < getRows(); i++){
            for (int j = 0; j < getColumns(); j++){
                result.setElement(i-1, j, getElement(i, j));
            }
        }
        return result;
    }

    public Matrix eliminateColumn(int column){
        Matrix result = new Matrix(getRows(), getColumns()-1);
        for (int i = 0; i < getRows(); i++){
            for (int j = 0; j < column; j++){
                result.setElement(i, j, getElement(i, j));
            }
        }
        for (int i = 0; i < getRows(); i++){
            for (int j = column+1; j < getColumns(); j++){
                result.setElement(i, j-1, getElement(i, j));
            }
        }
        return result;
    }
}