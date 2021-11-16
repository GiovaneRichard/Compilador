public class BooleanValueExpression extends Expression {
    public BooleanValue value;

    public BooleanValueExpression accept(Check check){
        return check.visit(this);
    }
}
