import java.util.ArrayList;
import java.util.List;

public class No{
    String simbolo;
    int estadoAtual;
    char acao;
    boolean isTerminal;
    List<No> filho = new ArrayList<No>();
    TokenType tokenType;
}