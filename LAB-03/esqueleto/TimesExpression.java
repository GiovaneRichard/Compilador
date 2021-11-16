public class TimesExpression extends Expression {
    public Expression left;
    public Expression right;

    public NumberDecimalExpression accept(Check check){
        
        return check.visit(this);
    }
}
