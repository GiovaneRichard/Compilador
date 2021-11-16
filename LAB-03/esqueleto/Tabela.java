import java.util.*;

public class Tabela{

    private List<Pair> tabelaSimbolos;


    // construtor
    public Tabela(){
        tabelaSimbolos = new ArrayList<Pair>();
    }   


    // devolve a tabela de símbolos
    public List<Pair> gettabelaSimbolos(){
        return this.tabelaSimbolos;
    }

    //  Método Put para adição do par(Symbolo, Type) na tabela de síbolos
    //  key     -> Simbolo
    //  value   -> tipo do simbolo
    public void put(Symbol key, Type value){

        boolean encontrado = false;

        // verifica se o simbolo já está presente na tabela de simbolos
        for(Pair pair: tabelaSimbolos)
        {
            if(pair.simbolo == key)
            {
                pair.type = value;
                encontrado = true;
            }
        }

        if(!encontrado)
        {
            Pair novoPair = new Pair();
            novoPair.type = value;
            novoPair.simbolo = key;
            tabelaSimbolos.add(novoPair);
        }
    }


    // Devolve um Type de acordo com o simbolo passado
    public Type getType(Symbol key){

        Type typeSimbolo = null;

        for(Pair pair: tabelaSimbolos)
        {
            if(pair.simbolo == key)
            {
                typeSimbolo = pair.type;
                return typeSimbolo;
            }
        }

        return null;
    }


    // Método início do scope
    public void beginScope(List<Pair> tabelaclone) throws Exception {

        for(Pair pair: tabelaSimbolos)
        {
            Pair clonePair = (Pair) pair.clone();
            tabelaSimbolos.add(clonePair);
        }
    }


    public void imprimeTabelaSimbol(){

        System.out.println("TABELA DE SIMBOLOS");

        // for(Pair pair: tabelaSimbolos)
            // System.out.println("< " + pair.name.toString() + "," + pair.type + " >");

    }



}// Fim da class