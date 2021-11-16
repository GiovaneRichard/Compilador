public class WhileStatement extends Statement {
    public Expression condition;
    public Statement body;

    public WhileStatement(){}

    public WhileStatement(Expression condition, Statement body){
        this.condition = condition;
        this.body = body;
    }

    public void accept(Check check){
        check.visit(this);
    }

}

