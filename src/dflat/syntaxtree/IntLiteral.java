package dflat.syntaxtree;

public class IntLiteral extends Literal {
	
	private String value;

	public IntLiteral(String value) {
		this.value = value;
	}

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(INT_LITERAL " + value + ")";
	}

}
