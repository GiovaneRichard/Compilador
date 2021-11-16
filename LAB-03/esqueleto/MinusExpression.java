public class MinusExpression extends Expression {
    public Expression left;
    public Expression right;

    public MinusExpression(){}

    public MinusExpression(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }


    public NumberDecimalExpression accept(Check check){
        return check.visit(this);
    }
}
