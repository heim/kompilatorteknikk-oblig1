package dflat.syntaxtree.param;

import dflat.syntaxtree.expression.Expression;
import dflat.syntaxtree.type.Type;

public class PassByValueParam extends ActualParam {

	private Expression expression;
	public PassByValueParam(Expression e) {
		this.expression = e;
	}
	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(ACTUAL_PARAM " + expression.printAst(0) + ")";
	}

    @Override
    public void checkSemantics() {
        expression.checkSemantics();
    }

    @Override
    public Type getType() {
        return expression.getType();
    }
}
