package dflat.syntaxtree;

public class BooleanLiteral extends Literal {

	private boolean value;

	public BooleanLiteral(boolean value) {
		this.value = value;
	}
	
	public String printAst(int indent) {
		return indentTabs(indent) + String.valueOf(value);
	}
}
