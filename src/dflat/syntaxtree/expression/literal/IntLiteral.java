package dflat.syntaxtree.expression.literal;

import dflat.syntaxtree.type.IntegerType;
import dflat.syntaxtree.type.Type;

public class IntLiteral extends Literal {
	
	private String value;

	public IntLiteral(String value) {
		this.value = value;
	}

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(INT_LITERAL " + value + ")";
	}

    @Override
    public void checkSemantics() {
    }

    @Override
    public Type getType() {
        return new IntegerType();
    }
}
