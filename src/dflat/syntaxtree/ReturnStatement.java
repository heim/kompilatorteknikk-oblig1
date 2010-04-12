package dflat.syntaxtree;

public class ReturnStatement extends Statement {

	private Expression expression;

	public ReturnStatement(Expression e) {
		this.expression = e;
	}
	
	public String printAst(int indent) {
		if (expression == null) {
			return indentTabs(indent) + "(RETURN_STMT)\n";
		} else {
			return indentTabs(indent) + "(RETURN_STMT \n" + expression.printAst(indent + 1) + "\n" + indentTabs(indent) + ")\n";
		}	
	}
}
