package dflat.syntaxtree.param;

import bytecode.CodeProcedure;
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
        variableExpression.checkSemantics();
    }

    @Override
    public Type getType() {
        return variableExpression.getType();
    }

    @Override
    public boolean getIsRef() {
        return true;
    }

    @Override
    public void generateCode(CodeProcedure procedure) {
        variableExpression.generateCode(procedure);
    }
}
