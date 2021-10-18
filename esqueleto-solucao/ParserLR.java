import java.util.List;
import java.util.ArrayList;
import java.util.Stack; 
import java.util.Collections;

public class ParserLR {

    public static final char SHIFT = 's';
    public static final char GO_TO = 'g';
    public static final char REDUCE = 'r';
    public static final char ACCEPT = 'a';

    private List<List<Regra>> listaRegras = new ArrayList<List<Regra>>();
    private List<Gramatica> listaGramatica = new ArrayList<Gramatica>();
    private Stack<No> pilha = new Stack<No>();

    private int estadoAtual = 0;
    private int currenTape = 0;
    private boolean reducao = false;
    private boolean fim = false;


    public ParserLR(List<List<Regra>> listaRegras, List<Gramatica> listaGramatica){
        setListaRegras(listaRegras);
        setListaGramaticas(listaGramatica);
    }


    public void setListaRegras(List<List<Regra>> listaRegras){
        this.listaRegras = listaRegras;
    }


    public void setListaGramaticas(List<Gramatica> listaGramatica){
        this.listaGramatica = listaGramatica;
    }


    public Regra getRegra(Token token, int estadoAtual){

        Regra regra = null;

        for(int i=0; i<listaRegras.get(estadoAtual).size(); ++i)
        {
            Regra rule = listaRegras.get(estadoAtual).get(i);
            TokenType tokenTypeRegra = rule.tokenType;

            if(token.type.equals(tokenTypeRegra))
                regra = rule;
            
            // System.out.println(rule);
        }

        return regra;
    }


    public No setNo(No noAtual, Regra regra, boolean isTerminal, String simbolo){
        noAtual.acao = regra.acao;
        noAtual.simbolo = simbolo;
        noAtual.estadoAtual = estadoAtual;
        noAtual.tokenType = regra.tokenType;
        noAtual.isTerminal = isTerminal;
        
        return noAtual;
    }


    // REDUÇÃO
    public void reducao(No noAtual, Regra regra, List<Token> entrada){

        List<TokenType> derivacoes = listaGramatica.get(regra.nextStado).tokenTypeDerivacao;

        for(int i=1; i <= derivacoes.size(); ++i)
        {
            // metodo peek() - verifica o topo da pilha
            if(pilha.peek().tokenType.equals(derivacoes.get(derivacoes.size() - i)))
            {
                // System.out.println( derivacoes.get(derivacoes.size() - i) );

                if(!pilha.empty())
                    estadoAtual = pilha.peek().estadoAtual;
                else
                    estadoAtual = 0;

                noAtual.filho.add(pilha.pop());
            }
        }

        // reverte filho
        Collections.reverse(noAtual.filho);

        noAtual.estadoAtual = estadoAtual;
        noAtual.simbolo = listaGramatica.get(regra.nextStado).naoTerminal;
        noAtual.tokenType = listaGramatica.get(regra.nextStado).tokenTypeNaoTerminal;

        pilha.push(noAtual);
        reducao = true;
    }

    //GO_TO
    public void goTo(Regra regra){
        estadoAtual = regra.nextStado;
    }


    // ACEITAÇÃO
    public No accept(No noAtual, Regra regra, List<Token> entrada)throws Exception{
       
        List<TokenType> derivacoes = listaGramatica.get(0).tokenTypeDerivacao;

        // verifica se é o estado de aceitação
        if(entrada.get(currenTape).type.equals(TokenType.ACC) && estadoAtual == 3)
        {
            for(int i=0; i < derivacoes.size(); ++i)
                noAtual.filho.add(pilha.pop()); 
        

            noAtual.simbolo = listaGramatica.get(0).naoTerminal;
            noAtual.tokenType = listaGramatica.get(0).tokenTypeNaoTerminal;
            pilha.push(noAtual);

            // reverte os filhos
            Collections.reverse(noAtual.filho);

            return noAtual;
        }
        else{
            throw new Exception("Erro: accept");
        }
    }

    public void consome(No noAtual, Regra regra){
        pilha.push(noAtual);
        estadoAtual = regra.nextStado;
        currenTape++;
    }

    public Regra ambiguidade(Regra regra, List<Token> entrada){

        if( (estadoAtual == 14 || estadoAtual == 48) && entrada.get(currenTape).type.equals(TokenType.ID))
        {
            if(entrada.get(currenTape+1).type.equals(TokenType.IGUAL))
            {
                regra.acao = REDUCE;
                regra.nextStado = 7;
            }
            else if(entrada.get(currenTape+1).type.equals(TokenType.ID))
            {
                regra.acao = SHIFT;
                regra.nextStado = 18;
            }
        }
        return regra;
    }

    public Program parse(List<Token> entrada) {

	return null;
    }

 
    public No parser(List<Token> entrada) throws Exception{

        No noAtual = new No();
        Token tokenParse = new Token();
        Regra regra = new Regra();

        Token tokenFinal = new Token();
        tokenFinal.type = TokenType.ACC;
        tokenFinal.image = "$";

        entrada.add(tokenFinal);
        
        while(!fim)
        {
            Token token = entrada.get(currenTape);

            if(reducao)
            {
                tokenParse.type = pilha.peek().tokenType;
                tokenParse.image = pilha.peek().simbolo;
            }
            else{
                tokenParse.type = token.type;
                tokenParse.image = token.image;
            }

            if(regra == null)
                throw new Exception("Token Invalido!");

            regra = getRegra(tokenParse, estadoAtual);

            // System.out.println(regra.acao + " " + regra.nextStado);
            if( (estadoAtual == 48 || estadoAtual == 14) && pilha.peek().acao != REDUCE )
                regra = ambiguidade(regra, entrada);

            System.out.println(regra.acao + " " + regra.nextStado);


            noAtual = new No();

            switch(regra.acao)
            {

                case SHIFT:
                    noAtual = setNo(noAtual, regra, true, tokenParse.image);
                    consome(noAtual, regra);
                    reducao = false;
                    break;

                case GO_TO:
                    goTo(regra);
                    pilha.peek().acao = GO_TO;
                    reducao = false;
                    break;

                case REDUCE:
                    noAtual = setNo(noAtual, regra, false, tokenParse.image);
                    reducao(noAtual, regra, entrada);
                    break;


                case ACCEPT:
                    noAtual = setNo(noAtual, regra, false, tokenParse.image);
                    noAtual = accept(noAtual, regra, entrada);

                    if(noAtual.tokenType.equals(TokenType.I))
                    {
                        System.out.println("Aceita!!");
                        fim = true;
                        reducao = false;
                    }
                    break;

                default:
                    throw new Exception("Acao invalida!");
        
            }

        }

        return noAtual;
    }



}// FIM DA CLASS
