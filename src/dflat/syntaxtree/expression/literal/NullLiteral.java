package dflat.syntaxtree.expression.literal;

import dflat.syntaxtree.type.Type;
import dflat.syntaxtree.type.VoidType;

public class NullLiteral extends Literal {

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(NULL)";
	}

    @Override
    public void checkSemantics() {
    }

    @Override
    public Type getType() {
        //TODO: Funker dette?
        return new VoidType();
    }
}
