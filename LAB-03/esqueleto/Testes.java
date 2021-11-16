
import java.util.*;

public class Testes {

    public static void main(String[] args){

        /**
         *   FUNÇÃO PARA TESTE
         * 
         *  boolean funcao_1 (boolean a){
         *      int b;
         *      int c;
         *      functionBody
         * 
         *      return a; 
         *  }
         */

        

        // declaração da função
        FunctionDeclaration fun = new FunctionDeclaration();

        // tipo de retorno da função
        fun.returnType = new TypeBoolean();
        // nome da função
        fun.functionName = new ID("funcao_1");

        // definindo os parametros da função
        VariableDeclaration param_a = new VariableDeclaration();
        param_a.variableName = new ID("a");
        param_a.variableType = new TypeBoolean();

        List<VariableDeclaration> listParametros = new ArrayList<VariableDeclaration>();
        listParametros.add(param_a);

        fun.parameterList = listParametros;

        // definindo as variáveis (b e c) da função
        VariableDeclaration var_b = new VariableDeclaration();
        var_b.variableName = new ID("b");
        var_b.variableType = new TypeInt();

        VariableDeclaration var_c = new VariableDeclaration();
        var_c.variableName = new ID("c");
        var_c.variableType = new TypeInt();
        

        List<VariableDeclaration> listVariaveis = new ArrayList<VariableDeclaration>();
        listVariaveis.add(var_b);
        listVariaveis.add(var_c);
        
        fun.variableDeclarationList = listVariaveis;

        // definindo os statements da função

        int testes = 4; // indice dos casos de teste
        AttributionStatement attributionStatement = null;
        List<Statement> statementList = new ArrayList<Statement>();

        switch (testes) {

            
            case 1: // a = b < c

                System.out.println("\n\n***************************************");
                System.out.println("\nTestando -> a = b < c");

                attributionStatement = new AttributionStatement(new ID("a"), new LessExpression(
                    new VariableExpression( new ID("b") ),
                    new VariableExpression( new ID("c") )
                ));

                statementList.add(attributionStatement);
                break;

            case 2: // c = (b + c) < (b - c)

                System.out.println("\n\n***************************************");
                System.out.println("\nTestando -> c = (b + c) < (b - c)");

                attributionStatement = new AttributionStatement(new ID("c"), new LessExpression(
                        new PlusExpression( new VariableExpression(new ID("b")), new VariableExpression(new ID("c")) ),
                        new MinusExpression( new VariableExpression(new ID("b")), new VariableExpression(new ID("c")) )
                ));

                statementList.add(attributionStatement);
                break;

            case 3: // a = (b + c) < (b < c)

                System.out.println("\n\n***************************************");
                System.out.println("\nTestando -> a = (b + c) < (b < c)");

                attributionStatement = new AttributionStatement(new ID("a"), new LessExpression(
                        new PlusExpression( new VariableExpression(new ID("b")), new VariableExpression(new ID("c")) ),
                        new LessExpression( new VariableExpression(new ID("b")), new VariableExpression(new ID("c")))
                    )
                );

                statementList.add(attributionStatement);
                break;

            case 4: // if(a < b)

                System.out.println("\n\n***************************************");
                System.out.println("\nTestando -> if(a < b)");

                IfStatement IF = new IfStatement(
                    new LessExpression( new VariableExpression(new ID("a")), new VariableExpression(new ID("b")) )
                );

                WhileStatement WHILE = new WhileStatement();
                

                // new LessExpression( new VariableExpression(new ID("a")), new VariableExpression(new ID("b")) );
                // IF.condition = LessExpression;


                break;
        
            default:
                break;
        }


        // corpo da função
        fun.functionBody = statementList;

        // Expressão de retorno para a função
        Expression exprReturn = new VariableExpression(new ID("a"));
        fun.returnExpression = exprReturn;

        // método CheckType
        CheckType check = new CheckType();
        boolean resultado = check.correctTypes(fun);

        System.out.println("\n\n*****  RESULTADO DA VERIFICACAO *****");
        System.out.println("\nResposta do CorrectTypes -> [" + resultado + "]");
        System.out.println("\n***************************************\n\n");
    }


}