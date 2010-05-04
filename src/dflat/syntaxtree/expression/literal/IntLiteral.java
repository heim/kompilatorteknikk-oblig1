package dflat.syntaxtree.expression.literal;

import dflat.syntaxtree.type.IntegerType;
import dflat.syntaxtree.type.Type;

public class IntLiteral extends FloatLiteral {
	

	public IntLiteral(String value) {

		 super(value);
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
