public class NegateExpression extends Expression {
    public Expression exp;

    public BooleanValueExpression accept(Check check){

        return check.visit(this);
    }
}
