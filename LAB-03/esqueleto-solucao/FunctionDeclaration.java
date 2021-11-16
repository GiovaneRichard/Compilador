import java.util.List;
/***
 * Declaracao de uma funcao.
 */
public class FunctionDeclaration {
    public Type returnType;
    public ID functionName;
    public List<VariableDeclaration> parameterList; // VariableDeclaration pode ser usado para descrever um parametro
    public List<VariableDeclaration> variableDeclarationList;
    public List<Statement> functionBody;
    public Expression returnExpression;
    
}
