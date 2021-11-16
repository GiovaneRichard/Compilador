/**
 *  classe para represenatar os elementos da tabela de símbolo,
 *  <simbolo, type>   =  <"c", new TypeInt()>
 */



public class Pair implements Cloneable {

    public Symbol simbolo;
    public Type type;

    public Pair(){

    }


    public Pair(Symbol simbolo, Type type){
        this.simbolo = simbolo;
        this.type = type;
    }
    

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}