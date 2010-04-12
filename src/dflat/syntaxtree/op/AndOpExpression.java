package dflat.syntaxtree.op;

import dflat.syntaxtree.Expression;

public class AndOpExpression extends LogOpExpression {

	public AndOpExpression(Expression exp1, Expression exp2) {
		super(exp1, exp2);
	}
	
	@Override
	public String printAst(int indent) {
		String retVal = indentTabs(indent) + "(LOG OP &&\n" + expression1.printAst(indent + 1) + "\n" + expression2.printAst(indent + 1) + "\n" + indentTabs(indent) + ")";
		return retVal;
	}

}
