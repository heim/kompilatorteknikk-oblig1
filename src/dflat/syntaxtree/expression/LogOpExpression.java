package dflat.syntaxtree.expression;

import dflat.syntaxtree.expression.Expression;

public abstract class LogOpExpression extends OpExpression {

	public LogOpExpression(Expression exp1, Expression exp2) {
		super(exp1, exp2);
	}

}
