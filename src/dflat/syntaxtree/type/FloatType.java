package dflat.syntaxtree.type;

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
}
