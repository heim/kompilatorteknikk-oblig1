package dflat.syntaxtree.type;

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
        return false;
    }

}
