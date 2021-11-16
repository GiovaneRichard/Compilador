
public class LessExpression extends Expression {

    public Expression left;
    public Expression right;

    public LessExpression(){}

    public LessExpression(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    public BooleanValueExpression accept(Check check){
        return check.visit(this);
    }

}