public class OutputPrinter {
    public void printSolution(Matrix solution){
        for(int i = 0; i < solution.getRows(); i++){
            for(int j = 0; j < solution.getColumns(); j++){
                if(solution.getElement(i, j) == 0){
                    continue;
                }
                String s = "X";
                s += i;
                s += j;
                s += " = ";
                s += solution.getElement(i, j);
                System.out.println(s);
            }
        }
    }

    public void printTable(Matrix supply, Matrix costs, Matrix demand){
        Matrix table = new Matrix(costs.getRows()+1, costs.getColumns()+1);
        for(int i = 0; i < supply.getRows(); i++){
            table.setElement(i, costs.getColumns(), supply.getElement(i, 0));
        }
        for (int i = 0; i < costs.getRows(); i++){
            for (int j = 0; j < costs.getColumns(); j++){
                table.setElement(i, j, costs.getElement(i, j));
            }
        }
        for(int i = 0; i < demand.getRows(); i++){
            table.setElement(costs.getRows(), i, demand.getElement(i, 0));
        }
        System.out.println("    COSTS               SUPPLY  ");

        for (int i = 0; i < table.getRows(); i++){
            String row = "";
            for (int j = 0; j < table.getColumns(); j++){
                if(j != costs.getColumns()){
                    row += table.getElement(i, j) + " ";
                }
                else if (i != costs.getRows()) {
                    row += "   |" + table.getElement(i, j);
                }

            }
            System.out.println(row);
            if(i == costs.getRows()-1){
                String s = "";
                for(int k = 0; k < costs.getColumns(); k++){
                    s += "_____";
                }
                System.out.print(s);
                System.out.println();
            }
        }
        System.out.println("      DEMAND      ");

    }
}
