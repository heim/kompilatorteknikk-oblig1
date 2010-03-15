package dflat.syntaxtree;

public class PassByReferenceParam extends ActualParam {

	private VariableExpression variableExpression;

	public PassByReferenceParam(VariableExpression variableExpression) {
		this.variableExpression = variableExpression;
	}
	
	public String printAst(int indent) {
		return indentTabs(indent) + "(ACTUAL_PARAM ref " + variableExpression.printAst(0) + ")";
	}

}
