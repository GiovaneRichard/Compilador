import java.util.*;

public class CheckType implements Check {


   private Tabela tabelaSimbolos = new Tabela();
   private boolean isCorrect = true;


    // Declaração de uma variável
    public void visit(VariableDeclaration var){

        Symbol simbolName = null;

        if ( (var.variableType instanceof TypeInt || var.variableType instanceof TypeBoolean)  && (var.variableName instanceof ID) )
        {
            // tabelaSimbolos.put(Symbol.simbolo(var.variableName.id), var.variableType);
            simbolName = Symbol.simbolo(var.variableName.id);
            tabelaSimbolos.put(simbolName, var.variableType);

            // System.out.println("Inserindo -> " + simbolName.nomeSymbol());
        }
        else{

            isCorrect = false;
            if( !(var.variableType instanceof TypeInt) || !(var.variableType instanceof TypeBoolean) )
                System.out.println("\nTipo do parametro da variavel [" + var.variableName.id + "] invalido!!");
                

            if( !(var.variableName instanceof ID))
                System.out.println("\nNome da variavel [" + var.variableName.id + "] invalido!!");
        }

    }

    
    public void listDeclaration(List<VariableDeclaration> varList){

        for(VariableDeclaration variableDeclaration: varList)
            visit(variableDeclaration);
    }


    public BooleanValueExpression visit(BooleanValueExpression expr){
        return new BooleanValueExpression();
    }


    public NumberDecimalExpression visit(NumberDecimalExpression expr){
        return new NumberDecimalExpression();
    }

    // E * E
    public NumberDecimalExpression visit(TimesExpression expr){

        NumberDecimalExpression numberDecimalExpression = new NumberDecimalExpression();

        if( !(expr.left.accept(this) instanceof NumberDecimalExpression))
        {
            System.out.println("\nO lado esquerdo da expressao deve ser um Inteiro!!");
            isCorrect = false;
        }

        if( !(expr.right.accept(this) instanceof NumberDecimalExpression))
        {
            System.out.println("\nO lado direito da expressao deve ser um Inteiro");
            isCorrect = false;
        }

        return numberDecimalExpression;
    }

    // E || E
    public BooleanValueExpression visit(OrExpression expr){

        BooleanValueExpression booleanValueExpression = new BooleanValueExpression();

        if( !(expr.left.accept(this) instanceof BooleanValueExpression))
        {
            System.out.println("\nO lado esquerdo da expressao deve ser um boolean");
            isCorrect = false;
        }

        if( !(expr.right.accept(this) instanceof BooleanValueExpression))
        {
            System.out.println("\nO lado direito da expressao deve ser um boolean");
            isCorrect = false;
        }

        return booleanValueExpression;
    }


    // E - E
    public NumberDecimalExpression visit(MinusExpression expr){

        NumberDecimalExpression numberDecimalExpression  = new NumberDecimalExpression();

        if( !(expr.left.accept(this) instanceof NumberDecimalExpression))
        {
            System.out.println("\nO lado esquerdo da expressao deve ser um Inteiro!!");
            isCorrect = false;
        }

        if( !(expr.right.accept(this) instanceof NumberDecimalExpression))
        {
            System.out.println("\nO lado direito da expressao deve ser um Inteiro");
            isCorrect = false;
        }

        return numberDecimalExpression;

    }

    // NegateExpression
    public BooleanValueExpression visit(NegateExpression expr){

        BooleanValueExpression booleanValueExpression = new BooleanValueExpression();

        if( !(expr.exp.accept(this) instanceof BooleanValueExpression))
        {
            System.out.println("\nA expressao deve ser do tipo booleana!!");
            isCorrect = false;
        }

        return booleanValueExpression;
    }

    // VariableExpression
    public Expression visit(VariableExpression varExpr){

        Type type = null;

        type = tabelaSimbolos.getType(Symbol.simbolo(varExpr.varName.id));
        

        if(type == null)
        {
            System.out.println("Variavel " + varExpr.varName.id + " Variavel deve ser instanciada." );
            isCorrect = false;
        }

        if(type instanceof TypeInt)
            return new NumberDecimalExpression();


        return new BooleanValueExpression();
    }

    // E + E
    public NumberDecimalExpression visit(PlusExpression expr){

        NumberDecimalExpression numberDecimalExpression = new NumberDecimalExpression();

        if( !(expr.left.accept(this) instanceof NumberDecimalExpression))
        {
            System.out.println("\nO lado esquerdo da expressao deve ser um inteiro!!");
            isCorrect = false;
        }

        if(!(expr.right.accept(this) instanceof NumberDecimalExpression))
        {
            System.out.println("\nO lado direito da expressao deve ser um inteiro!!");
            isCorrect = false;
        }

        return numberDecimalExpression;
    }

    // E < E
    public BooleanValueExpression visit(LessExpression expr) {

        BooleanValueExpression booleanValueExpression = new BooleanValueExpression();

        if(!(expr.left.accept(this) instanceof NumberDecimalExpression))
        {
            System.out.println("\nO lado esquerdo da expressao deve ser um inteiro!! less " + expr.left.accept(this));
            isCorrect = false;
        }

        if( !(expr.right.accept(this) instanceof NumberDecimalExpression))
        {
            System.out.println("\nO lado direito da expressao deve ser um inteiro!!");
            isCorrect = false;
        }

        return booleanValueExpression;
    }

    // E && E
    public BooleanValueExpression visit(AndExpression expr){

        BooleanValueExpression booleanValueExpression = new BooleanValueExpression();

        if( !(expr.left.accept(this) instanceof BooleanValueExpression))
        {
            System.out.println("\nO lado esquerdo da expressao deve ser um booleano!! and ");
            isCorrect = false;
        }

        if(!(expr.right.accept(this) instanceof BooleanValueExpression))
        {
            System.out.println("\nO lado direito da expressao deve ser um booleano!!");
            isCorrect = false;
        }

        return booleanValueExpression;
    }


    // StatementList
    public void visit(StatementList stl){

        for(Statement statement: stl.statementList) 
            statement.accept(this);
    }


    // if
    public void visit(IfStatement stm){

        if( !(stm.condition.accept(this) instanceof BooleanValueExpression)){
            System.out.println("\nO if deve ter como condicao uma expressao do tipo booleana!!");
        }

        stm.thenStatement.accept(this);
        stm.elseStatement.accept(this);
    }

    // while
    public void visit(WhileStatement stm){
        if( !(stm.condition.accept(this) instanceof BooleanValueExpression))
            System.out.println("\nO while deve ter como condicao uma expressao booleana!!");

        stm.body.accept(this);
    }


    // print
    public void visit(PrintStatement stm){

        stm.printValue.accept(this);
    }

    // AttributionStatement
    public void visit(AttributionStatement stm){
        
        Type ladoEsq = null;
        ladoEsq = tabelaSimbolos.getType(Symbol.simbolo(stm.leftSide.id));

        // verifica se a variável não foi instanciada
        if(ladoEsq.equals(null))
        {
            System.out.println("A variavel deve ser instanciada!");
            isCorrect = false;
        }

        
        if( !(ladoEsq.equals(null)) && (ladoEsq instanceof TypeBoolean) && (stm.rightSide.accept(this) instanceof NumberDecimalExpression) )
        {
            System.out.println("\nA variavel [" + stm.leftSide.id + "] deve receber um tipo int");
            isCorrect = false;
        }
        else if( !(ladoEsq.equals(null)) && ladoEsq instanceof TypeInt && stm.rightSide.accept(this) instanceof BooleanValueExpression)
        {
            System.out.println("\nA variavel [" + stm.leftSide.id + "] deve ser do tipo boolean");
            isCorrect = false;
        }

    }

    // listStatement
    public void listStatement(List<Statement> listStm){

        for(Statement statement: listStm)
            statement.accept(this);
    }

    // declaração de uma função
    public void visit(FunctionDeclaration fd){

        listDeclaration(fd.parameterList); // lista de parâmetros
        listDeclaration(fd.variableDeclarationList);   // lista de variáveis de declaração
        listStatement(fd.functionBody); // corpo da função

        // checa o nome da função
        if( !(fd.functionName instanceof ID))
        {
            System.out.println("A funcao " + fd.functionName.id + " nao possui um nome valido!");
            isCorrect = false;
        }

        if( !(fd.returnType instanceof TypeBoolean) || (fd.returnType instanceof TypeInt))
        {
            System.out.println("\nErro no tipo retornado pela função!");
            isCorrect = false;
        }
        else if( fd.returnType instanceof TypeBoolean && fd.returnExpression.accept(this) instanceof NumberDecimalExpression)
        {
            System.out.println("A funcao " + fd.functionName.id + " deve ter o seguite tipo de retorno -> " +  fd.returnExpression.accept(this)); 
            isCorrect = false;
        }
        else if( fd.returnType instanceof TypeInt && fd.returnExpression.accept(this) instanceof BooleanValueExpression)
        {
            System.out.println("A funcao " + fd.functionName.id + " deve ter o seguite tipo de retorno -> " +  fd.returnExpression.accept(this)); 
            isCorrect = false;
        }
    }


    public boolean correctTypes(FunctionDeclaration fun) {

        visit(fun);

        return isCorrect;  
    }


}
