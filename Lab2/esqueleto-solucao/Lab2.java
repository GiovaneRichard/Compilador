import java.util.List;
import java.util.ArrayList;

import javax.swing.*;


public class Lab2{

    public static void main(String[] args) {
        
        List<Token> listaTokens = new ArrayList<Token>();
        List<Gramatica> listaGramatica = new ArrayList<Gramatica>();
        List<List<Regra>> listaRegras = new ArrayList<List<Regra>>();


        try {

            ReadTokens tok = new ReadTokens();
            listaTokens = tok.getList();

            ReadGramatica gra = new ReadGramatica();
            listaGramatica = gra.getList();

            ReadRegras reg = new ReadRegras();
            listaRegras = reg.getList(); 

            // for(Token t: listaTokens){
            //     System.out.println(t.type + " -> " + t.image);
            // }
            
            ParserLR2 parser = new ParserLR2(listaRegras, listaGramatica);
            No raiz = new No();
            raiz = parser.parser(listaTokens);

            
            
            ArvParser arv = new ArvParser();
            Program program = arv.constroiProgram(raiz);
            arv.imprimeArv(raiz);

            // System.out.println(program.mainClass.className.id);
            

            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

}