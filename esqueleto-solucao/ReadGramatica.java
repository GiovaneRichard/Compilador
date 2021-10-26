import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Scanner;


public class ReadGramatica{

private File arqGramatica = null;
private Scanner scan;
private List<Gramatica> listGramatica = new ArrayList<Gramatica>();


public ReadGramatica() throws Exception{
    setFile();
    setList();
}


public void setFile() throws Exception{
    arqGramatica = new File("../gramaticas/gram1.grammar");
    scan = new Scanner(arqGramatica);

    
}


public List<Gramatica> getList(){
    return listGramatica;
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

            case "String":
                tokenType = TokenType.TYPE_NAME_STRING;
                break;

            case "extends":
                tokenType = TokenType.EXTENDS;
                break;

            case "NUM_DECIM":
                tokenType = TokenType.NUM_DECIM;
                break;

            case "ID":
                tokenType = TokenType.ID;
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

            case "BOOLEAN_VALUE":
                tokenType = TokenType.BOOLEAN_VALUE;
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

            case "I":
                tokenType = TokenType.I;
                break;

            case "M":
                tokenType = TokenType.M;
                break;

            case "Cx":
                tokenType = TokenType.Cx;
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

            case "S":
                tokenType = TokenType.S;
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

    while(scan.hasNext())
    {
        String[] strSplit = scan.nextLine().split(" -> ");

        Gramatica gramatica = new Gramatica();
        gramatica.naoTerminal = strSplit[0];
        gramatica.derivacao = strSplit[1];
        gramatica.tokenTypeNaoTerminal = getTokenType(strSplit[0]);

        String[] derivacoes = strSplit[1].split(" ");

        for(int i=0; i < derivacoes.length; ++i)
        {
            TokenType token = getTokenType(derivacoes[i]);
            gramatica.tokenTypeDerivacao.add(token);
        }

        listGramatica.add(gramatica);
    }
}


}// Fim da class