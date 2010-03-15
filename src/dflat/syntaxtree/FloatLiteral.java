package dflat.syntaxtree;

public class FloatLiteral extends Literal {
	private String value;

	public FloatLiteral(String value) {
		this.value = value; 
	}
	
	public String printAst(int indent) {
		return indentTabs(indent) + "(FLOAT_LITERAL " + value + ")";
	}
}
