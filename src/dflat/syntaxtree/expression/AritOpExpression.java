package dflat.syntaxtree.expression;

import bytecode.CodeProcedure;
import dflat.exceptions.IncompatibleTypeException;
import dflat.syntaxtree.expression.op.AritOp;
import dflat.syntaxtree.expression.op.ExponentOp;
import dflat.syntaxtree.type.FloatType;
import dflat.syntaxtree.type.IntegerType;
import dflat.syntaxtree.type.Type;

public class AritOpExpression extends OpExpression {

	private AritOp op;
    private Type type;

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
        super.checkSemantics();

        checkCompatibleTypesOnExpressions();
    }

    private void checkCompatibleTypesOnExpressions() {
        Type e1Type = expression1.getType();
        Type e2Type = expression2.getType();

        if(e2Type.canBeCastTo(new FloatType()) && e1Type.canBeCastTo(new FloatType())) {
            if(e1Type instanceof FloatType || e2Type instanceof FloatType || op instanceof ExponentOp)
                type = new FloatType();
            else
                type = new IntegerType();
        }else {
            throw new IncompatibleTypeException(this);
        }

    }

    @Override
    public Type getType() {


        return type;
    }

    @Override
    public void generateCode(CodeProcedure codeProcedure) {
        expression1.generateCode(codeProcedure);
        expression2.generateCode(codeProcedure);
        op.generateCode(codeProcedure);
    }
}
