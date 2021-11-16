public class NumberDecimalExpression extends Expression {
    public NumberDecimal value;

    public NumberDecimalExpression accept(Check check){

        return check.visit(this);
    }
}
