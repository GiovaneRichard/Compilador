import java.util.*;


public class Lab3{

    public static void main(String[] args){

        // instancia a declaração de uma função
        FunctionDeclaration fun = new FunctionDeclaration();

        fun.returnType = new TypeBoolean();
        fun.functionName = new ID("funcao_1");

        VariableExpression returnExpression = new VariableExpression();
        returnExpression.varName = new ID("ans");
        fun.returnExpression = returnExpression;

        // declaração da variável a
        VariableDeclaration var_a = new VariableDeclaration();
        var_a.variableName = new ID("a");
        var_a.variableType = new TypeBoolean();

        // declaração da variável b
        VariableDeclaration var_b = new VariableDeclaration();
        var_b.variableName = new ID("b");
        var_b.variableType = new TypeInt();

        // declaração da variável c
        VariableDeclaration var_c = new VariableDeclaration();
        var_c.variableName = new ID("c");
        var_c.variableType = new TypeInt();

        // lista de declaração de variáveis
        // fun.variableDeclarationList = new ArrayList<>();
        // fun.variableDeclarationList.add(var_a);
        // fun.variableDeclarationList.add(var_b);
        // fun.variableDeclarationList.add(var_c);

        // declaração da variável ans
        VariableDeclaration declareAns = new VariableDeclaration();
        declareAns.variableName = new ID("ans");
        declareAns.variableType = new TypeBoolean();

        fun.variableDeclarationList = new ArrayList<>();
        fun.variableDeclarationList.add(var_c);
        fun.variableDeclarationList.add(declareAns);


        // declaração da variável A
        VariableDeclaration declare_A = new VariableDeclaration();
        declare_A.variableName = new ID("A");
        declare_A.variableType = new TypeInt();

        fun.parameterList = new ArrayList<>();
        fun.parameterList.add(declare_A);

        // AttributionStatement
        AttributionStatement attributionStatement = new AttributionStatement();

        // E + E
        TimesExpression timesExpr = new TimesExpression();
        // E - E
        MinusExpression minusExpr = new MinusExpression();

        VariableExpression ladoEsq = new VariableExpression();

        // lado esquerdo da expressão
        ladoEsq.varName = new ID("A");
        minusExpr.left = ladoEsq;
        minusExpr.right = new NumberDecimalExpression();

        timesExpr.left = ladoEsq;
        timesExpr.right = new NumberDecimalExpression();

        attributionStatement.leftSide = new ID("c");
        attributionStatement.rightSide = timesExpr;

        IfStatement IF = new IfStatement();
        LessExpression lessExpression = new LessExpression();



        CheckType check = new CheckType();
        boolean resultado = check.correctTypes(fun);

        System.out.println(resultado);


    }

}