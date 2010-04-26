package dflat.syntaxtree.expression.literal;

public class BooleanLiteral extends Literal {

	private boolean value;

	public BooleanLiteral(boolean value) {
		this.value = value;
	}
	
	public String printAst(int indent) {
		return indentTabs(indent) + "(BOOL_LITERAL " + String.valueOf(value) + ")";
	}
}
