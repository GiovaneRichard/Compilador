
public interface Check {


    public void visit(VariableDeclaration var);

    public BooleanValueExpression visit(BooleanValueExpression expr);

    public NumberDecimalExpression visit(NumberDecimalExpression expr);

    // E * E
    public NumberDecimalExpression visit(TimesExpression expr);
    // E || E
    public BooleanValueExpression visit(OrExpression expr);
    // E < E
    public NumberDecimalExpression visit(MinusExpression expr);
    // E + E
    public NumberDecimalExpression visit(PlusExpression expr);
    // E - E
    public BooleanValueExpression visit(LessExpression expr);
    // E && E
    public BooleanValueExpression visit(AndExpression expr);
    // NegateExpression
    public BooleanValueExpression visit(NegateExpression expr);
    // VariableExpression
    public Expression visit(VariableExpression varExpr);


    public void visit(StatementList stl);
    // if
    public void visit(IfStatement stm);
    // while
    public void visit(WhileStatement stm);
    // print
    public void visit(PrintStatement stm);
    // AttributionStatement
    public void visit(AttributionStatement stm);
    // declaração de uma função
    public void visit(FunctionDeclaration fd);


}