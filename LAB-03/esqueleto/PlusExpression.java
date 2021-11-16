public class PlusExpression extends Expression {
    public Expression left;
    public Expression right;


    public PlusExpression(){}

    public PlusExpression(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    public NumberDecimalExpression accept(Check check){
        return check.visit(this);
    }
}
