import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Scanner;

public class ReadRegras{

    public static final char SHIFT = 's';
    public static final char GO_TO = 'g';
    public static final char REDUCE = 'r';
    public static final char ACCEPT = 'a';

    private File arqRegras;
    private List<List<Regra>> listRegras = new ArrayList<List<Regra>>();
    private Scanner scan;

    public ReadRegras() throws Exception{
        setFile();
        setList();
    }

    public void setFile() throws Exception{
        arqRegras = new File("../gramaticas/regras.txt");
        scan = new Scanner(arqRegras);
    }


    public List<List<Regra>> getList(){
        return listRegras;
    }


    public TokenType getTokenType(String simbolo){

        TokenType tokenType = null;

        switch(simbolo)
        {
            case "class":
            tokenType = TokenType.CLASS;
            break;
        
        case "public":
            tokenType = TokenType.PUBLIC;
            break;

        case "static":
            tokenType = TokenType.STATIC;
            break;

        case "void":
            tokenType = TokenType.VOID;
            break;
        
        case "main":
            tokenType = TokenType.MAIN;
            break;

        case "A_PALAVRA_STRING":
            tokenType = TokenType.A_PALAVRA_STRING;
            break;

        case "extends":
            tokenType = TokenType.EXTENDS;
            break;

        case "ID":
            tokenType = TokenType.ID;
            break;
        
        case "NUM_DECIM":
            tokenType = TokenType.NUM_DECIM;
            break;

        case "BOOLEAN_VALUE":
            tokenType = TokenType.BOOLEAN_VALUE;
            break;

        case "{":
            tokenType = TokenType.ABRE_CHAVE;
            break;
        
        case "}":
            tokenType = TokenType.FECHA_CHAVE;
            break;

        case "[":
            tokenType = TokenType.ABRE_COLCHETE;
            break;

        case "]":
            tokenType = TokenType.FECHA_COLCHETE;
            break;

        case "(":
            tokenType = TokenType.ABRE_PARENTESE;
            break;

        case ")":
            tokenType = TokenType.FECHA_PARENTESE;
            break;

        case ".":
            tokenType = TokenType.PONTO;
            break;

        case ",":
            tokenType = TokenType.VIRGULA;
            break;

        case ";":
            tokenType = TokenType.PONTO_E_VIRGULA;
            break;

        case "=":
            tokenType = TokenType.IGUAL;
            break;

        case "&&":
            tokenType = TokenType.OP_E;
            break;

        case "||":
            tokenType = TokenType.OP_OU;
            break;

        case "<":
            tokenType = TokenType.OP_MENOR;
            break;

        case "+":
            tokenType = TokenType.OP_MAIS;
            break;

        case "-":
            tokenType = TokenType.OP_MENOS;
            break;

        case "*":
            tokenType = TokenType.OP_VEZES;
            break;

        case "!":
            tokenType = TokenType.OP_EXCLAMACAO;
            break;

        case "int":
            tokenType = TokenType.INT;
            break;

        case "boolean":
            tokenType = TokenType.BOOLEAN_TYPE;
            break;

        case "new":
            tokenType = TokenType.NEW;
            break;

        case "this":
            tokenType = TokenType.THIS;
            break;

        case "System.out.println":
            tokenType = TokenType.SYSTEM_OUT_PRINTLN;
            break;

        case "while":
            tokenType = TokenType.WHILE;
            break;

        case "if":
            tokenType = TokenType.IF;
            break;

        case "else":
            tokenType = TokenType.ELSE;
            break;

        case "return":
            tokenType = TokenType.RETURN;
            break;

        case "String":
            tokenType = TokenType.TYPE_NAME_STRING;
            break;

        case "length":
            tokenType = TokenType.LENGTH;
            break;

        case "I":
            tokenType = TokenType.I;
            break;

        case "Cx":
            tokenType = TokenType.Cx;
            break;

        case "M":
            tokenType = TokenType.M;
            break;

        case "C":
            tokenType = TokenType.C;
            break;

        case "Vx":
            tokenType = TokenType.Vx;
            break;

        case "V":
            tokenType = TokenType.V;
            break;

        case "Fx":
            tokenType = TokenType.Fx;
            break;

        case "F":
            tokenType = TokenType.F;
            break;

        case "Px":
            tokenType = TokenType.Px;
            break;
        
        case "Pz":
            tokenType = TokenType.Pz;
            break;

        case "T":
            tokenType = TokenType.T;
            break;

        case "Sx":
            tokenType = TokenType.Sx;
            break;

        case "E":
            tokenType = TokenType.E;
            break;

        case "Ex":
            tokenType = TokenType.Ex;
            break;

        case "Ez":
            tokenType = TokenType.Ez;
            break;

        default:
            break;
        }

        return tokenType;
    }


    public void setList() throws Exception{

        int estadoAtual = 0;
        List<Regra> regras = new ArrayList<Regra>();

        while(scan.hasNext())
        {
            String[] strSplit = scan.nextLine().split(" ");
            Regra rule = new Regra();

            estadoAtual = Integer.parseInt(strSplit[0]);
            rule.stado = estadoAtual;
            rule.tokenType = getTokenType(strSplit[1]);
            rule.simbolo = strSplit[1];

            if(strSplit[2].charAt(0) == SHIFT)
            {
                rule.acao = SHIFT;
                rule.nextStado = Integer.parseInt(strSplit[2].substring(1));
            }
            else if(strSplit[2].charAt(0) == REDUCE)
            {
                rule.acao = REDUCE;
                rule.nextStado = Integer.parseInt(strSplit[2].substring(1));
            }
            else if(strSplit[2].charAt(0) == ACCEPT)
            {
                rule.acao = ACCEPT;
            }
            else
            {
                rule.acao = GO_TO;
                rule.nextStado = Integer.parseInt(strSplit[2]);
            }

            regras.add(rule);
        }

        int cont = 0;
        for(int i=0; i <= estadoAtual; ++i){
            List<Regra> regr = new ArrayList<Regra>();

            while(cont < regras.size())
            {
                if(regras.get(cont).stado == i)
                {
                    regr.add(regras.get(cont));
                    cont++;
                }else{
                    break;
                }
            }

            if(!regr.isEmpty())
                listRegras.add(regr);

        }
    }

} // Fim da class