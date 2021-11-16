public class VariableExpression extends Expression {
    public ID varName;

    public VariableExpression(){}

    public VariableExpression(ID varName){
        this.varName = varName;
    }

    public Expression accept(Check check){
        return check.visit(this);
    }
}
