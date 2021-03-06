package dflat.syntaxtree.expression;

import bytecode.CodeProcedure;
import bytecode.instructions.AND;
import dflat.syntaxtree.type.BooleanType;
import dflat.syntaxtree.type.Type;

public class AndOpExpression extends LogOpExpression {

	public AndOpExpression(Expression exp1, Expression exp2) {
		super(exp1, exp2);
	}
	
	@Override
	public String printAst(int indent) {
		String retVal = indentTabs(indent) + "(LOG OP &&\n" + expression1.printAst(indent + 1) + "\n" + expression2.printAst(indent + 1) + "\n" + indentTabs(indent) + ")";
		return retVal;
	}

    @Override
    public void checkSemantics() {
        super.checkSemantics();
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }

    @Override
    public void generateCode(CodeProcedure codeProcedure) {
        expression1.generateCode(codeProcedure);
        expression2.generateCode(codeProcedure);
        codeProcedure.addInstruction(new AND());
    }
}
