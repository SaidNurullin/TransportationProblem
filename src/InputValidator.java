public class InputValidator {
    public boolean isMethodApplicable(Matrix supply, Matrix costs, Matrix demand){
        for (int i = 0; i < supply.getRows(); i++){
            if(supply.getElement(i, 0) < 0){
                return false;
            }
        }
        for (int i = 0; i < costs.getRows(); i++){
            for (int j = 0; j < costs.getColumns(); j++){
                if(costs.getElement(i, j) < 0){
                    return false;
                }
            }
        }
        for (int i = 0; i < demand.getRows(); i++){
            if(demand.getElement(i, 0) < 0){
                return false;
            }
        }
        return true;
    }

    public boolean isProblemBalanced(Matrix supply, Matrix demand){
        double sum1 = 0;
        double sum2 = 0;
        for (int i = 0; i < supply.getRows(); i++){
            sum1 += supply.getElement(i, 0);
        }
        for (int i = 0; i < demand.getRows(); i++){
            sum2 += demand.getElement(i, 0);
        }
        return sum1 == sum2;
    }
}
