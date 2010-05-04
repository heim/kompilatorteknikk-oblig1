package dflat.syntaxtree.expression.literal;

import dflat.syntaxtree.type.FloatType;
import dflat.syntaxtree.type.Type;

public class FloatLiteral extends Literal {
	protected String value;

	public FloatLiteral(String value) {
		this.value = value; 
	}
	
	public String printAst(int indent) {
		return indentTabs(indent) + "(FLOAT_LITERAL " + value + ")";
	}

    @Override
    public void checkSemantics() {
    }

    @Override
    public Type getType() {
        return new FloatType();
    }
}
