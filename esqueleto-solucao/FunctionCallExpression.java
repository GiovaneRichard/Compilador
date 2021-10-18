import java.util.List;

public class FunctionCallExpression extends Expression {
    public Expression object;
    public ID functionName;
    public List<Expression> arguments;
}
