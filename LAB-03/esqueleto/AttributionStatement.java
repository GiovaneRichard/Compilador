public class AttributionStatement extends Statement {
    public ID leftSide;
    public Expression rightSide;

    AttributionStatement(){}

    public AttributionStatement(ID leftSide, Expression rightSide){
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public void accept(Check check){
        check.visit(this);
    }
    
}

