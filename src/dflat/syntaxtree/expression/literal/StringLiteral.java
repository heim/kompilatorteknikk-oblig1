package dflat.syntaxtree.expression.literal;

import bytecode.CodeProcedure;
import bytecode.instructions.PUSHSTRING;
import dflat.syntaxtree.type.StringType;
import dflat.syntaxtree.type.Type;

public class StringLiteral extends Literal {
	
	private String value;

	public StringLiteral(String value) {
		this.value = value;
	}

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(STRING_LITERAL \" " + value + " \")";
	}

    @Override
    public void checkSemantics() {
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public void generateCode(CodeProcedure codeProcedure) {
        codeProcedure.addInstruction(new PUSHSTRING(codeProcedure.addStringConstant(value)));
    }
}
