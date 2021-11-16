public class IfStatement extends Statement {
    public Expression condition;
    public Statement thenStatement;
    public Statement elseStatement;

    public IfStatement(){}

    public IfStatement(Expression condition){
        this.condition = condition;
    }
    
    public void accept(Check check){
        check.visit(this);
    }

}

