package dflat.syntaxtree.type;

import bytecode.type.CodeType;

public class BooleanType extends Type {

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(TYPE boolean)";
	}

    @Override
    public void checkSemantics() {
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof BooleanType;
    }

    @Override
    public Name getName() {
        return new Name("bool");
    }

    @Override
    public boolean canBeCastTo(Type otherType) {
        return otherType instanceof BooleanType;
    }

    @Override
    public CodeType getByteCodeType() {
        return bytecode.type.BoolType.TYPE;
    }

}
