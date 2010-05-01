package dflat.syntaxtree.param;

import dflat.syntaxtree.expression.VariableExpression;
import dflat.syntaxtree.type.Type;

public class PassByReferenceParam extends ActualParam {

	private VariableExpression variableExpression;

	public PassByReferenceParam(VariableExpression variableExpression) {
		this.variableExpression = variableExpression;
	}
	
	public String printAst(int indent) {
		return indentTabs(indent) + "(ACTUAL_PARAM ref " + variableExpression.printAst(0) + ")";
	}

    @Override
    public void checkSemantics() {
    }

    @Override
    public Type getType() {
        return null;
    }
}
