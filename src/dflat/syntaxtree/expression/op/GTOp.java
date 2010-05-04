package dflat.syntaxtree.expression.op;

import bytecode.CodeProcedure;
import bytecode.instructions.GT;


public class GTOp extends RelOp {

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + ">";
	}

    @Override
    public void checkSemantics() {
    }

    @Override
    public void generateCode(CodeProcedure codeProcedure) {
        codeProcedure.addInstruction(new GT());
    }
}
