package dflat.syntaxtree.expression;

import dflat.exceptions.IncompatibleTypeException;
import dflat.syntaxtree.expression.Expression;
import dflat.syntaxtree.type.BooleanType;

public abstract class LogOpExpression extends OpExpression {

	public LogOpExpression(Expression exp1, Expression exp2) {
		super(exp1, exp2);
	}

    @Override
    public void checkSemantics() {
        super.checkSemantics();
        if(!bothExpressionsAreBoolean()) {
           throw new IncompatibleTypeException(this); 
        }
    }

    private boolean bothExpressionsAreBoolean() {
        return (expression1.getType().equals(new BooleanType()) && expression2.getType().equals(new BooleanType()));
    }
}
