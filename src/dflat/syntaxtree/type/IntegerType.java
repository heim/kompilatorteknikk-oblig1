package dflat.syntaxtree.type;

public class IntegerType extends Type {

	@Override
	public String printAst(int indent) {
		
		return indentTabs(indent) + "(TYPE int)";
	}

    @Override
    public void checkSemantics() {
    }


    @Override
    public boolean equals(Object obj) {
        //i alle henseender innenfor kompilatoren vil en int være likeverdig en float.
        return obj instanceof Type && canBeCastTo((Type)obj);
    }


    @Override
    public Name getName() {
        return new Name("int");
    }

    @Override
    public boolean canBeCastTo(Type otherType) {
         //return otherType instanceof IntegerType;
        return  otherType instanceof IntegerType || otherType instanceof FloatType;
    }

}
