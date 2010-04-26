package dflat.syntaxtree.type;

public class FloatType extends Type {

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(TYPE float)";
	}


    @Override
    public boolean equals(Object obj) {
        return obj instanceof FloatType;
    }
}
