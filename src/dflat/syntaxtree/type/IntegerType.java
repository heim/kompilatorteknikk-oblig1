package dflat.syntaxtree.type;

public class IntegerType extends Type {

	@Override
	public String printAst(int indent) {
		
		return indentTabs(indent) + "(TYPE int)";
	}


    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntegerType;
    }


    
}
