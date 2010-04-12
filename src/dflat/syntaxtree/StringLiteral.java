package dflat.syntaxtree;

public class StringLiteral extends Literal {
	
	private String value;

	public StringLiteral(String value) {
		this.value = value;
	}

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(STRING_LITERAL \" " + value + " \")";
	}
}
