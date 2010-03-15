package dflat.syntaxtree;

public class PassByValueParam extends ActualParam {

	private Expression expression;
	public PassByValueParam(Expression e) {
		this.expression = e;
	}
	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(ACTUAL_PARAM " + expression.printAst(0) + ")";
	}

}
