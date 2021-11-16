import java.util.List;

public class StatementList extends Statement {
    public List<Statement> statementList;

    public void accept(Check check){
        check.visit(this);
    }

   
}

