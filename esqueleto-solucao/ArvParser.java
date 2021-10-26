import java.util.*;

public class ArvParser{

    public String getString(TokenType token){

        String str = null;

        switch(token)
        {

            case CLASS:
            str = "class";
            break;
        
        case PUBLIC:
            str = "public";
            break;

        case STATIC:
            str = "static";
            break;

        case VOID :
            str = "void";
            break;
        
        case MAIN:
            str = "main";
            break;

        case A_PALAVRA_STRING:
            str = "A_PALAVRA_STRING";
            break;

        case EXTENDS:
            str = "extends";
            break;

        case ID:
            str = "ID";
            break;
        
        case NUM_DECIM:
            str = "NUM_DECIM";
            break;

        case BOOLEAN_VALUE:
            str = "BOOLEAN_VALUE";
            break;

        case ABRE_CHAVE:
            str = "{";
            break;
        
        case FECHA_CHAVE:
            str = "}";
            break;

        case ABRE_COLCHETE:
            str = "[";
            break;

        case FECHA_COLCHETE:
            str = "]";
            break;

        case ABRE_PARENTESE:
            str = "(";
            break;

        case FECHA_PARENTESE:
            str = ")";
            break;

        case PONTO:
            str = ".";
            break;

        case VIRGULA:
            str = ",";
            break;

        case PONTO_E_VIRGULA:
            str = ";";
            break;

        case IGUAL:
            str = "=";
            break;

        case OP_E:
            str = "&&";
            break;

        case OP_OU:
            str = "||";
            break;

        case OP_MENOR:
            str = "<";
            break;

        case OP_MAIS:
            str = "+";
            break;

        case OP_MENOS:
            str = "-";
            break;

        case OP_VEZES:
            str = "*";
            break;

        case OP_EXCLAMACAO:
            str = "!";
            break;

        case INT:
            str = "int";
            break;

        case BOOLEAN_TYPE:
            str = "boolean";
            break;

        case NEW:
            str = "new";
            break;

        case THIS:
            str = "this";
            break;

        case SYSTEM_OUT_PRINTLN:
            str = "System.out.println";
            break;

        case WHILE:
            str = "while";
            break;

        case IF:
            str = "if";
            break;

        case ELSE:
            str = "else";
            break;

        case RETURN:
            str = "return";
            break;

        case TYPE_NAME_STRING:
            str = "String";
            break;

        case LENGTH:
            str = "length";
            break;

        case I:
            str = "I";
            break;

        case Cx:
            str = "Cx";
            break;

        case M:
            str = "M";
            break;

        case C:
            str = "C";
            break;

        case Vx:
            str = "Vx";
            break;

        case V:
            str = "V";
            break;

        case Fx:
            str = "Fx";
            break;

        case F:
            str = "F";
            break;

        case Px:
            str = "Px";
            break;
        
        case Pz:
            str = "Pz";
            break;

        case T:
            str = "T";
            break;

        case Sx:
            str = "Sx";
            break;

        case E:
            str = "E";
            break;

        case Ex:
            str = "Ex";
            break;

        case Ez:
            str = "Ez";
            break;

            default:
                System.out.println("Erro ao se ler o Token");
                break;
        }

        return str;
    }


    public String filhoToStr(List<No> filho){

        String str = "";

        for(int i=0; i < filho.size(); ++i)
        {
            str += getString(filho.get(1).tokenType);
            
            if(i + 1 < filho.size())
                str += " ";
        }

        return str;
    }


    // Constroi o Program
    public Program constroiProgram(No raiz){

        String strProgram = filhoToStr(raiz.filho);
        Program program = new Program();
    
        System.out.println("ProgramString: " + strProgram);
    
        switch(strProgram)
        {
            case "M Cx":
                program.mainClass = constroiMainClass(raiz.filho.get(0));
                program.classDeclarationList = constroiClassDeclarationList(raiz.filho.get(1));
                break;
    
            default:
                break;
        }
    
        return program;
    }


    // constroi lista de classe de declaração
    public List<ClassDeclaration> constroiClassDeclarationList(No listClassDeclaration){

        String strClassDeclaration = filhoToStr(listClassDeclaration.filho);
        List<ClassDeclaration> novaListClassDeclaration = new ArrayList<ClassDeclaration>();

        switch(strClassDeclaration)
        {
            case "C Cx":

                List<ClassDeclaration> listClassDeclarationInterna = new ArrayList<ClassDeclaration>();
                ClassDeclaration declaracao = constroClassDeclaration(listClassDeclaration.filho.get(0));
                novaListClassDeclaration.add(declaracao);

                listClassDeclarationInterna = constroiClassDeclarationList(listClassDeclaration.filho.get(1));

                for(ClassDeclaration classDeclaracao: listClassDeclarationInterna)
                    novaListClassDeclaration.add(classDeclaracao);
                
                break;

            default:
                break;
        }


        return novaListClassDeclaration;
    }


    // constroi a class main
    public MainClass constroiMainClass(No mainClass){

        String strMainClass = filhoToStr(mainClass.filho);
        MainClass novaMainClass = new MainClass();

        System.out.println("[DEBUG] :: " + strMainClass);

        switch(strMainClass)
        {
            case "class ID { public static void main ( String [ ] ID ) { Sx } }":

                novaMainClass.className = new ID(mainClass.filho.get(1).simbolo);
                novaMainClass.mainArgName = new ID(mainClass.filho.get(11).simbolo);
                novaMainClass.mainStatementList = criaListStatement(mainClass.filho.get(14));
                break;

            default:
                break;
        }

        return novaMainClass;
    }

    
     // Constroi a declaração de uma class
     public ClassDeclaration constroClassDeclaration(No declaracaoClass){

        String strDeclaracaoClass = filhoToStr(declaracaoClass.filho);
        ClassDeclaration novaDeclaracaoClass = new ClassDeclaration();

        switch(strDeclaracaoClass)
        {
            case "class ID { Vx Fx }":
                novaDeclaracaoClass.className = new ID(declaracaoClass.filho.get(1).simbolo);
                novaDeclaracaoClass.variableDeclarationList = constroiVariableDeclarationList(declaracaoClass.filho.get(3));
                novaDeclaracaoClass.functionDeclarationList = criaListFunctionDeclaration(declaracaoClass.filho.get(4));
                break;

            case "class ID extends ID { Vx Fx }":
                novaDeclaracaoClass.className = new ID(declaracaoClass.filho.get(1).simbolo);
                novaDeclaracaoClass.extend = new ID(declaracaoClass.filho.get(3).simbolo);
                novaDeclaracaoClass.variableDeclarationList = constroiVariableDeclarationList(declaracaoClass.filho.get(5));
                novaDeclaracaoClass.functionDeclarationList = criaListFunctionDeclaration(declaracaoClass.filho.get(6));
                break;
            
            default:
                break;
        }

        return novaDeclaracaoClass;
     }



    // constroi Declaração de Variável
    public VariableDeclaration constroiVariableDeclaration(No declaracaoVariavel){

        String strDeclaracaoVariavel = filhoToStr(declaracaoVariavel.filho);
        VariableDeclaration novaDeclaracaoVariavel = new VariableDeclaration();

        if(declaracaoVariavel.equals("T ID ;"))
        {
            novaDeclaracaoVariavel.variableType = constroiType(declaracaoVariavel.filho.get(0));
            novaDeclaracaoVariavel.variableName = new ID(declaracaoVariavel.filho.get(1).simbolo);
        }

        return novaDeclaracaoVariavel;
    }

   

    // constroi uma lista de declaração de variáveis
    public List<VariableDeclaration> constroiVariableDeclarationList(No listDeclaracaoVariavel){

        String strListDeclaracaoVariavel = filhoToStr(listDeclaracaoVariavel.filho);
        List<VariableDeclaration> novaListVariableDeclaration = new ArrayList<VariableDeclaration>();

        if(strListDeclaracaoVariavel.equals("V Vx"))
        {
            List<VariableDeclaration> listVariableDeclaracaoInterna = new ArrayList<VariableDeclaration>();
            VariableDeclaration declaracao = constroiVariableDeclaration(listDeclaracaoVariavel.filho.get(0));

            novaListVariableDeclaration.add(declaracao);
            listVariableDeclaracaoInterna = constroiVariableDeclarationList(listDeclaracaoVariavel.filho.get(1));

            for(VariableDeclaration variavelDeclaracao: listVariableDeclaracaoInterna)
                novaListVariableDeclaration.add(variavelDeclaracao);
        }

        return novaListVariableDeclaration;
    }

    // Declaracao de uma função
    public FunctionDeclaration constroiFunctionDeclaration(No declaracaoFuncao){

        String strFunctionDeclaration = filhoToStr(declaracaoFuncao.filho);
        FunctionDeclaration novaFuncaoDeclaracao = new FunctionDeclaration();
    
        if(strFunctionDeclaration.equals("public T ID ( Px ) { Vx Sx return E ; }"))
        {
            novaFuncaoDeclaracao.returnType = constroiType(declaracaoFuncao.filho.get(1));
            novaFuncaoDeclaracao.functionName = new ID(declaracaoFuncao.filho.get(2).simbolo);
            novaFuncaoDeclaracao.parameterList = constroiParameterList(declaracaoFuncao.filho.get(4));
            novaFuncaoDeclaracao.variableDeclarationList = constroiVariableDeclarationList(declaracaoFuncao.filho.get(7));
            novaFuncaoDeclaracao.functionBody = criaListStatement(declaracaoFuncao.filho.get(8));
            novaFuncaoDeclaracao.returnExpression = constroiExpression(declaracaoFuncao.filho.get(10));
        }
    
        return novaFuncaoDeclaracao;
    }
    

    // Declaração da list de função
    public List<FunctionDeclaration> criaListFunctionDeclaration(No listDeclaracaoFuncao){
    
            String strDeclaracaoFuncao = filhoToStr(listDeclaracaoFuncao.filho);
            List<FunctionDeclaration> novalistDeclaracaoFuncao = new ArrayList<FunctionDeclaration>();
    
            if(strDeclaracaoFuncao.equals("F Fx"))
            {
                novalistDeclaracaoFuncao.add(constroiFunctionDeclaration(listDeclaracaoFuncao.filho.get(0)));
                List<FunctionDeclaration> listDeclaracaoFuncaoInterna = criaListFunctionDeclaration(listDeclaracaoFuncao.filho.get(1));
    
                for(FunctionDeclaration declaracao: listDeclaracaoFuncaoInterna)
                    novalistDeclaracaoFuncao.add(declaracao);
            }
    
            return novalistDeclaracaoFuncao;
        }

    
        // Constroi Lista de Parametros
    
        public List<VariableDeclaration> constroiParameterList(No parameterList){

        String strListParameter = filhoToStr(parameterList.filho);
        List<VariableDeclaration> novaListParameter = new ArrayList<VariableDeclaration>();

        if(strListParameter.equals("T ID Pz"))
        {
            VariableDeclaration variableDeclaration = new VariableDeclaration();
            variableDeclaration.variableType = constroiType(parameterList.filho.get(0));
            variableDeclaration.variableName = new ID(parameterList.filho.get(1).simbolo);

            novaListParameter.add(variableDeclaration);

            List<VariableDeclaration> parameterListRest = constroiParameterListRest(parameterList.filho.get(2));

            for(VariableDeclaration declaracao: parameterListRest)
                novaListParameter.add(declaracao);

        }

        return novaListParameter;
    }


    // Constroi lista de declaração de parameter
    public List<VariableDeclaration> constroiParameterListRest(No parameterListRest){

        String strListParameter = filhoToStr(parameterListRest.filho);
        List<VariableDeclaration> novaListParameterRest = new ArrayList<VariableDeclaration>();

        if(strListParameter.equals(", T ID Pz"))
        {
            VariableDeclaration variableDeclaration = new VariableDeclaration();
            variableDeclaration.variableType = constroiType(parameterListRest.filho.get(1));
            variableDeclaration.variableName = new ID(parameterListRest.filho.get(2).simbolo);

            List<VariableDeclaration> parameterListInterno = new ArrayList<VariableDeclaration>();

            for(VariableDeclaration declaracao: parameterListInterno)
            novaListParameterRest.add(declaracao);

        }

        return novaListParameterRest;
    }

    // Cosntroi o Type
    public Type constroiType(No typeNo){

        String strType = filhoToStr(typeNo.filho);
        Type novoType = new Type();

        switch(strType)
        {

            case "int [ ]":
                TypeIntVector typeIntVector = new TypeIntVector();
                novoType = typeIntVector;
                break;

            case "boolean":
                TypeBoolean typeBoolean = new TypeBoolean();
                novoType = typeBoolean;
                break;

            case "int":
                TypeInt typeInt = new TypeInt();
                novoType = typeInt;
                break;

            case "ID":
                TypeID typeID = new TypeID();
                novoType = typeID;
                break;


            default:
                break;

        }

        return novoType;
    }


    // Constoi um Statement
    public Statement constroiStatement(No statement){

        String strExpressao = filhoToStr(statement.filho);
        Statement novoStatement = new Statement();


        switch(strExpressao)
        {
            case "{ Sx }":
                StatementList statementList = new StatementList();
                statementList.statementList = criaListStatement(statement.filho.get(1));
                novoStatement = statementList;
                break;

            case "if ( E ) S else S":
                IfStatement ifStatement = new IfStatement();
                ifStatement.condition = constroiExpression(statement.filho.get(2));
                ifStatement.thenStatement = constroiStatement(statement.filho.get(4));
                ifStatement.elseStatement = constroiStatement(statement.filho.get(6));
                novoStatement = ifStatement;
                break;

            case "while ( E ) S":
                WhileStatement whileStatement = new WhileStatement();
                whileStatement.condition = constroiExpression(statement.filho.get(2));
                whileStatement.body = constroiStatement(statement.filho.get(4));
                novoStatement = whileStatement;
                break;

            case "System.out.println ( E )":
                PrintStatement printStatement = new PrintStatement();
                printStatement.printValue = constroiExpression(statement.filho.get(2));
                novoStatement =  printStatement;
                break;

            case "ID = E ;":
                AttributionStatement attribuitionStatement = new AttributionStatement();
                attribuitionStatement.leftSide = new ID(statement.filho.get(0).simbolo);
                attribuitionStatement.rightSide = constroiExpression(statement.filho.get(2));
                novoStatement = attribuitionStatement;
                break;

            case "ID [ E ] = E ;":
                ArrayAttributionStatement arrayAttribuitionStatement = new ArrayAttributionStatement();
                arrayAttribuitionStatement.arrayName = new ID(statement.filho.get(0).simbolo);
                arrayAttribuitionStatement.arrayIndex = constroiExpression(statement.filho.get(2));
                arrayAttribuitionStatement.arrayIndex = constroiExpression(statement.filho.get(5));
                novoStatement = arrayAttribuitionStatement;
                break;

            default:
                break;
        }

        return novoStatement;
    }

    // Constroi uma list de Statement
    public List<Statement> criaListStatement(No listStatement){

        String strStatement = filhoToStr(listStatement.filho);
        List<Statement> novaListaStatement = new ArrayList<Statement>();

        if(strStatement.equals("S Sx"))
        {
            novaListaStatement.add(constroiStatement(listStatement.filho.get(0)));
            List<Statement> subListStatement =  criaListStatement(listStatement.filho.get(1));
    

            for(Statement statement: subListStatement)
                novaListaStatement.add(statement);
        }

        return novaListaStatement;
    }

    // constroi uma Expression
    public Expression constroiExpression(No expressao){

        Expression novaExpressao = new Expression();
        String strExpressao = filhoToStr(expressao.filho);


        switch(strExpressao)
        {
            case "E && E":
                AndExpression expressao_And = new AndExpression();
                expressao_And.left = constroiExpression(expressao.filho.get(0));
                expressao_And.right = constroiExpression(expressao.filho.get(2));
                novaExpressao = expressao_And;
                break;

            case "E || E":
                OrExpression expressao_Or = new OrExpression();
                expressao_Or.left = constroiExpression(expressao.filho.get(0));
                expressao_Or.right = constroiExpression(expressao.filho.get(2));
                novaExpressao = expressao_Or;
                break;
            
            case "E < E":
                SmallExpression expressao_menor = new SmallExpression();
                expressao_menor.left = constroiExpression(expressao.filho.get(0));
                expressao_menor.right = constroiExpression(expressao.filho.get(2));
                novaExpressao = expressao_menor;
                break;

            case "E + E":
                PlusExpression  expressao_plus = new PlusExpression();
                expressao_plus.left = constroiExpression(expressao.filho.get(0));
                expressao_plus.right = constroiExpression(expressao.filho.get(2));
                novaExpressao = expressao_plus;
                break;

            case "E - E":
                MinusExpression expressao_minus = new MinusExpression();
                expressao_minus.left = constroiExpression(expressao.filho.get(0));
                expressao_minus.right = constroiExpression(expressao.filho.get(2));
                novaExpressao = expressao_minus;
                break;

            case "E * E":
                TimesExpression expressao_times = new TimesExpression();
                expressao_times.left = constroiExpression(expressao.filho.get(0));
                expressao_times.right = constroiExpression(expressao.filho.get(2));
                novaExpressao = expressao_times;
                break;

            case "E [ E ]":
                ArrayIndexExpression arrayIndexExpression = new ArrayIndexExpression();
                arrayIndexExpression.array = constroiExpression(expressao.filho.get(0));
                arrayIndexExpression.index = constroiExpression(expressao.filho.get(2));
                novaExpressao = arrayIndexExpression;
                break;

            case "E . length":
                ArrayLengthExpression arrayLengthExpression = new ArrayLengthExpression();
                arrayLengthExpression.array = constroiExpression(expressao.filho.get(0));
                novaExpressao = arrayLengthExpression;
                break;

            case "E . ID ( Ex )":
                FunctionCallExpression functionCallExpression = new FunctionCallExpression();
                functionCallExpression.object = constroiExpression(expressao.filho.get(0));
                functionCallExpression.functionName = new ID(expressao.filho.get(2).simbolo);
                functionCallExpression.arguments = criaListExpressao(expressao.filho.get(4));
                break;

            case "NUM_DECIM":
                NumberDecimalExpression expressao_numDecimal = new NumberDecimalExpression();
                expressao_numDecimal.value = new NumberDecimal(expressao.filho.get(0).simbolo);
                novaExpressao = expressao_numDecimal;
                break;

            case "BOOLEAN_VALUE":
                BooleanValueExpression expressao_valorBoolean = new BooleanValueExpression();
                expressao_valorBoolean.value = new BooleanValue(expressao.filho.get(0).simbolo);
                novaExpressao = expressao_valorBoolean;
                break;

            case "ID":
                VariableExpression variableExpression = new VariableExpression();
                variableExpression.varName = new ID(expressao.filho.get(0).simbolo);
                novaExpressao = variableExpression;
                break;

            case "this":
                ThisExpression thisExpressao = new ThisExpression();
                novaExpressao = thisExpressao;
                break;

            case "new int [ E ]":
                NewIntArrayExpression newIntArrayExpressao = new NewIntArrayExpression();
                newIntArrayExpressao.arraySize = constroiExpression(expressao.filho.get(3));
                novaExpressao = newIntArrayExpressao;
                break;

            case "new ID ( )":
                NewObjectExpression newObjectEpressao = new NewObjectExpression();
                newObjectEpressao.objectName = new ID(expressao.filho.get(1).simbolo);
                novaExpressao = newObjectEpressao;
                break;
            
            case "! E":
                NegateExpression negateExpressao = new NegateExpression();
                negateExpressao.exp = constroiExpression(expressao.filho.get(1));
                novaExpressao = negateExpressao;
                break;

            case "( E )":
                novaExpressao = constroiExpression(expressao.filho.get(1));
                break;

            default:
                break;
        }

        return null;
    }


    // Constroi lista de Expressão
    public List<Expression> criaListExpressao(No listExpressao){
        String strExpressao = filhoToStr(listExpressao.filho);
        List<Expression> novaListExpressao = new ArrayList<Expression>();

        if(strExpressao.equals("E Ez")){
            novaListExpressao.add(constroiExpression(listExpressao.filho.get(0)));
            
            List<Expression> novaSubListExpressao = criaSubListExpressao(listExpressao.filho.get(1));

            for(int i=0; i < novaSubListExpressao.size(); ++i )
                novaListExpressao.add(novaSubListExpressao.get(i));
        }

        return novaListExpressao;
    }

    // Cria uma subList de Expressão
    public List<Expression> criaSubListExpressao(No subListExpressaoNo){

        String strExpressao = filhoToStr(subListExpressaoNo.filho);
        List<Expression> subListExpressao = new ArrayList<Expression>();

        if(strExpressao.equals(", E Ez")){
            subListExpressao.add(constroiExpression(subListExpressaoNo.filho.get(1)));

            List<Expression> subListInterna = criaSubListExpressao(subListExpressaoNo.filho.get(2));

            for(int i=0; i < subListInterna.size(); ++i)
                subListExpressao.add(subListInterna.get(i));

        }

        return subListExpressao;
    }


    // Imprime a Arv de Parser
    public void imprimeArv(No raiz){

        Queue<No> fila = new LinkedList<>();

        fila.add(raiz);

        while(!fila.isEmpty())
        {
            No e = fila.peek();
            fila.remove();

            Collections.reverse(e.filho);

            for(int i=0; i < e.filho.size(); ++i)
            {
                System.out.println(
                    i + " " + e.filho.get(i).simbolo + " " + e.simbolo + " " + (e.filho.get(i).isTerminal ? "SIM" : "NAO")
                );

                fila.add(e.filho.get(i));
            }
        }

    }



}// Fim da class