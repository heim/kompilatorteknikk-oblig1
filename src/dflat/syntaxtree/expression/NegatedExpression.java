package dflat.syntaxtree.expression;

import dflat.syntaxtree.type.BooleanType;
import dflat.syntaxtree.type.Type;

public class NegatedExpression extends Expression {

	private Expression expression;

	public NegatedExpression(Expression exp) {
		this.expression = exp;
	}
	
	public String printAst(int indent){
		return indentTabs(indent) + "(NEGATED_EXP " + expression.printAst(0) + ")";
	}

    @Override
    public void checkSemantics() {
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }
}
