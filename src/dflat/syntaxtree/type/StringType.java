package dflat.syntaxtree.type;

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
        return false;
    }
}
