import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Teste{

    public static void main(String[] args) {
        

    try {
        
        ReadTokens loadTokens = new ReadTokens();
        ReadRegras loadRegras = new ReadRegras(); 
        ReadGramatica loadGramatica = new ReadGramatica();
        

        // TESTES ARQ DAS REGRAS
        List<List<Regra>> listaRegras = new ArrayList<List<Regra>>();
        listaRegras = loadRegras.getList();

        // for(List<Regra> lista: listaRegras){
        //     // System.out.println(lista);
        //    for(Regra r: lista){
        //         System.out.println(r.simbolo + "  " + r.acao + " " + r.nextStado );
        //    }
        // }

        

        // // TESTES ARQ DAS GRAMATICAS
        List<Gramatica> listaGramatica = new ArrayList<Gramatica>();
        listaGramatica = loadGramatica.getList();
        
        // for(Gramatica g: listaGramatica){
        //     System.out.println(g.naoTerminal + " " + g.derivacao);
    
        // }

        
        // TESTES ARQ DOS TOKENS
        List<Token> listaTokens = new ArrayList<Token>();
        listaTokens = loadTokens.getList();

        // for(Token t: listaTokens){
        //     System.out.println(t.image);
        // }


         // TESTES ARQ DO PARSER LR
        ParserLR p = new ParserLR(listaRegras, listaGramatica);
        p.parser(listaTokens);

    } catch (Exception e) {
        System.out.println("Erro nos Testes!!!" + e);
    }
   

    }

}