package dflat.syntaxtree.op;

import dflat.syntaxtree.Expression;

public abstract class LogOpExpression extends OpExpression {

	public LogOpExpression(Expression exp1, Expression exp2) {
		super(exp1, exp2);
	}

}
