import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Scanner;



public class ReadTokens{

    private File arqTokens;
    private List<Token> ListaTokens = new ArrayList<Token>();
    private Scanner scan;


    public ReadTokens() throws Exception{
        setFile();
        setList();
    }

    public void setFile() throws Exception{
        arqTokens = new File("../gramaticas/tokens.txt");
        // arqTokens = new File("../tokens.txt");
        scan = new Scanner(arqTokens);
    }

    public List<Token> getList() throws Exception{
        return ListaTokens;
    }

    
    public void setList() throws Exception{
        
        String str;

        while(scan.hasNext())
        {
            str = scan.nextLine();
            Token token = new Token();
            String[] strSplit = str.split(" ");

            // teste
            //System.out.println("strSplit -> " + strSplit[0]);

            switch (strSplit[0]) 
            {
                case "class":
                    token.type = TokenType.CLASS;
                    token.image = "class";
                    ListaTokens.add(token);   
                    break;
                
                case "public":
                    token.type = TokenType.PUBLIC;
                    token.image = "public";
                    ListaTokens.add(token);
                    break;

                case "static":
                    token.type = TokenType.STATIC;
                    token.image = "static";
                    ListaTokens.add(token);
                    break;

                case "void":
                    token.type = TokenType.VOID;
                    token.image = "void";
                    ListaTokens.add(token);
                    break;
                
                case "main":
                    token.type = TokenType.MAIN;
                    token.image = "main";
                    ListaTokens.add(token);
                    break;

                case "STRING":
                    token.type = TokenType.A_PALAVRA_STRING;
                    token.image = strSplit[1];
                    ListaTokens.add(token);
                    break;
                
                case "TYPE_NAME_STRING":
                    token.type = TokenType.TYPE_NAME_STRING;
                    token.image = "String";
                    ListaTokens.add(token);
                    break;

                case "extends":
                    token.type = TokenType.EXTENDS;
                    token.image = "extends";
                    ListaTokens.add(token);
                    break;
                
                case "ID":
                    token.type = TokenType.ID;
                    token.image = strSplit[1];
                    ListaTokens.add(token);
                    break;

                case "NUM_DECIM":
                    token.type = TokenType.NUM_DECIM;
                    token.image = strSplit[1];
                    ListaTokens.add(token);
                    break;
                
                case "{":
                    token.type = TokenType.ABRE_CHAVE;
                    token.image = "{";
                    ListaTokens.add(token);
                    break;

                case "}":
                    token.type = TokenType.FECHA_CHAVE;
                    token.image = "}";
                    ListaTokens.add(token);
                    break;
                
                case "[":
                    token.type = TokenType.ABRE_COLCHETE;
                    token.image = "[";
                    ListaTokens.add(token);
                    break;

                case "]":
                    token.type = TokenType.FECHA_COLCHETE;
                    token.image = "]";
                    ListaTokens.add(token);
                    break;

                case "(":
                    token.type = TokenType.ABRE_PARENTESE;
                    token.image = "(";
                    ListaTokens.add(token);
                    break;

                case ")":
                    token.type = TokenType.FECHA_PARENTESE;
                    token.image = ")";
                    ListaTokens.add(token);
                    break;

                case ".":
                    token.type = TokenType.PONTO;
                    token.image = ".";
                    ListaTokens.add(token);
                    break;
                
                case ",":
                    token.type = TokenType.VIRGULA;
                    token.image = ",";
                    ListaTokens.add(token);
                    break;

                case ";":
                    token.type = TokenType.PONTO_E_VIRGULA;
                    token.image = ";";
                    ListaTokens.add(token);
                    break;

                case "=":
                    token.type = TokenType.IGUAL;
                    token.image = "=";
                    ListaTokens.add(token);
                    break;
                
                case "&&":
                    token.type = TokenType.OP_E;
                    token.image = "&&";
                    ListaTokens.add(token);
                    break;

                case "||":
                    token.type = TokenType.OP_OU;
                    token.image = "||";
                    ListaTokens.add(token);
                    break;

                case "<":
                    token.type = TokenType.OP_MENOR;
                    token.image = "<";
                    ListaTokens.add(token);
                    break;

                case "+":
                    token.type = TokenType.OP_MAIS;
                    token.image = "+";
                    ListaTokens.add(token);
                    break;

                case "-":
                    token.type = TokenType.OP_MENOS;
                    token.image = "-";
                    ListaTokens.add(token);
                    break;
                
                case "*":
                    token.type = TokenType.OP_VEZES;
                    token.image = "*";
                    ListaTokens.add(token);
                    break;

                case "!":
                    token.type = TokenType.OP_EXCLAMACAO;
                    token.image = "!";
                    ListaTokens.add(token);
                    break;

                case "int":
                    token.type = TokenType.INT;
                    token.image = "int";
                    ListaTokens.add(token);
                    break;

                case "BOOLEAN_VALUE":
                    token.type = TokenType.BOOLEAN_VALUE;
                    token.image = strSplit[1];
                    ListaTokens.add(token);
                    break;

                case "boolean":
                    token.type = TokenType.BOOLEAN_TYPE;
                    token.image = "boolean";
                    ListaTokens.add(token);
                    break;

                case "new":
                    token.type = TokenType.NEW;
                    token.image = "new";
                    ListaTokens.add(token);
                    break;

                case "this":
                    token.type = TokenType.THIS;
                    token.image = "this";
                    ListaTokens.add(token);
                    break;

                case "acc":
                    token.type = TokenType.ACC;
                    token.image = "acc";
                    ListaTokens.add(token);
                    break;

                case "length":
                    token.type = TokenType.LENGTH;
                    token.image = "length";
                    ListaTokens.add(token);
                    break;

                case "System.out.println":
                    token.type = TokenType.SYSTEM_OUT_PRINTLN;
                    token.image = "System.out.println";
                    ListaTokens.add(token);
                    break;
                
                case "while":
                    token.type = TokenType.WHILE;
                    token.image = "while";
                    ListaTokens.add(token);
                    break;
                
                case "if":
                    token.type = TokenType.IF;
                    token.image = "if";
                    ListaTokens.add(token);
                    break;

                case "else":
                    token.type = TokenType.ELSE;
                    token.image = "else";
                    ListaTokens.add(token);
                    break;

                case "return":
                    token.type = TokenType.RETURN;
                    token.image = "return";
                    ListaTokens.add(token);
                    break;

                default:
                    break;
            }

        }
    }


}