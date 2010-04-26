package dflat.syntaxtree.expression;

public class NegatedExpression extends Expression {

	private Expression expression;

	public NegatedExpression(Expression exp) {
		this.expression = exp;
	}
	
	public String printAst(int indent){
		return indentTabs(indent) + "(NEGATED_EXP " + expression.printAst(0) + ")";
	}
}
