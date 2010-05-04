package dflat.syntaxtree.type;

import bytecode.type.CodeType;

public class FloatType extends Type {

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(TYPE float)";
	}

    @Override
    public void checkSemantics() {
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof FloatType;
    }

    @Override
    public Name getName() {
        return new Name("float");
    }

    @Override
    public boolean canBeCastTo(Type otherType) {
        return otherType instanceof FloatType;
    }

    @Override
    public CodeType getByteCodeType() {
        return bytecode.type.FloatType.TYPE;
    }
}
