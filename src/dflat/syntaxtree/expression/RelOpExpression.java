package dflat.syntaxtree.expression;

import dflat.syntaxtree.expression.op.RelOp;
import dflat.syntaxtree.type.BooleanType;
import dflat.syntaxtree.type.Type;

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

    @Override
    public void checkSemantics() {
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }
}
