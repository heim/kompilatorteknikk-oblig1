package dflat.syntaxtree.expression;

import dflat.syntaxtree.expression.op.AritOp;
import dflat.syntaxtree.type.Type;

public class AritOpExpression extends OpExpression {

	private AritOp op;

	public AritOpExpression(Expression exp1, AritOp op, Expression exp2) {
		super(exp1, exp2);
		this.op = op;
		
	}

	@Override
	public String printAst(int indent) {
		String retVal = indentTabs(indent) + "(ARIT_OP " + op.printAst(0) + "\n";
		
		retVal += expression1.printAst(indent + 1) + "\n" + expression2.printAst(indent + 1) + "\n";
		retVal += indentTabs(indent) + ")";
		return retVal;
	}

    @Override
    public void checkSemantics() {
    }

    @Override
    public Type getType() {
        return null;
    }
}
