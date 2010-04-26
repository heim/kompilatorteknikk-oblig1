package dflat.syntaxtree.type;

public class StringType extends Type {

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(TYPE string)";
	}


    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringType;
    }
}
