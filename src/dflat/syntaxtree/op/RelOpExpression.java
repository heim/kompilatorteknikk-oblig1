package dflat.syntaxtree.op;

import dflat.syntaxtree.Expression;

public class RelOpExpression extends OpExpression {

	private RelOp relOp;
	public RelOpExpression(Expression exp1, RelOp op, Expression exp2) {
		super(exp1, exp2);
		this.relOp = op;
	}
	@Override
	public String printAst(int indent) {
		String retVal = indentTabs(indent) + "(REL_OP " + relOp.printAst(0) + "\n";
		
		retVal += expression1.printAst(indent + 1) + "\n" + expression2.printAst(indent + 1) + "\n";
		retVal += indentTabs(indent) + ")\n";
		return retVal;
	}

}
