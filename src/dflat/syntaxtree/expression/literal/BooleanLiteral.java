package dflat.syntaxtree.expression.literal;

import dflat.syntaxtree.type.BooleanType;
import dflat.syntaxtree.type.Type;

public class BooleanLiteral extends Literal {

	private boolean value;

	public BooleanLiteral(boolean value) {
		this.value = value;
	}
	
	public String printAst(int indent) {
		return indentTabs(indent) + "(BOOL_LITERAL " + String.valueOf(value) + ")";
	}

    @Override
    public void checkSemantics() {
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }
}
