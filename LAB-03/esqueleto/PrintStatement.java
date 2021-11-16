public class PrintStatement extends Statement {
    public Expression printValue;

    public void accept(Check check){
        check.visit(this);
    }
    
}

