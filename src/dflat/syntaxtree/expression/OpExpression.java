package dflat.syntaxtree.expression;

import dflat.syntaxtree.expression.Expression;

public abstract class OpExpression extends Expression {

	protected Expression expression1;
	protected Expression expression2;

	public OpExpression(Expression exp1, Expression exp2) {
		this.expression1 = exp1;
		this.expression2 = exp2;
	}
}
