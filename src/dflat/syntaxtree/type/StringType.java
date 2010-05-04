package dflat.syntaxtree.type;

import bytecode.type.CodeType;

public class StringType extends Type {

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(TYPE string)";
	}

    @Override
    public void checkSemantics() {
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringType;
    }

    @Override
    public Name getName() {
        return new Name("string");
    }

    @Override
    public boolean canBeCastTo(Type otherType) {
        return otherType instanceof StringType;
    }

    @Override
    public CodeType getByteCodeType() {
        return bytecode.type.StringType.TYPE;
    }
}

