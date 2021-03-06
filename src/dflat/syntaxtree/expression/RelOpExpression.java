package dflat.syntaxtree.expression;

import bytecode.CodeProcedure;
import dflat.exceptions.IncompatibleTypeException;
import dflat.syntaxtree.expression.op.RelOp;
import dflat.syntaxtree.type.BooleanType;
import dflat.syntaxtree.type.FloatType;
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
        super.checkSemantics();
        if(!bothExpressionsCanBeCastToFloat())
            throw new IncompatibleTypeException(this);
    }

    private boolean bothExpressionsCanBeCastToFloat() {
        return expression1.getType().equals(new FloatType()) && expression2.getType().equals(new FloatType());
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }

    @Override
    public void generateCode(CodeProcedure codeProcedure) {
        expression1.generateCode(codeProcedure);
        expression2.generateCode(codeProcedure);
        relOp.generateCode(codeProcedure);
    }
}
