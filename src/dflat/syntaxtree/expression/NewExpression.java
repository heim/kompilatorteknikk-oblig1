package dflat.syntaxtree.expression;

import bytecode.CodeProcedure;
import bytecode.instructions.NEW;
import dflat.syntaxtree.type.Type;

public class NewExpression extends Expression {

	private Type type;
	public NewExpression(Type type) {
		this.type = type;
	}
	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(NEW " + type.printAst(0) + ")";
	}

    @Override
    public void checkSemantics() {
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void generateCode(CodeProcedure codeProcedure) {
        int structNum = codeProcedure.structNumber(type.getName().toString());
        codeProcedure.addInstruction(new NEW(structNum));
    }
}
