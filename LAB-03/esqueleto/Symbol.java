import java.util.*;

public class Symbol{

    private String name;

    private Symbol(String nome){
        this.name = nome;
    }

    public String nomeSymbol() {return name;}


    private static Dictionary<String, Symbol> dicionario = new Hashtable<>();


    public static Symbol simbolo(String nome){

        Symbol simbolo = null;
        String strName = nome.intern();

        simbolo = (Symbol) dicionario.get(strName);

        if(simbolo == null)
        {
            simbolo = new Symbol(strName);
            dicionario.put(strName, simbolo);
        }

        return simbolo;
    }


}// Fim da class