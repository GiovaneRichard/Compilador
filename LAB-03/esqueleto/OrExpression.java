public class OrExpression extends Expression {
    public Expression left;
    public Expression right;

    public BooleanValueExpression accept(Check check){
        return check.visit(this);
    }
}
