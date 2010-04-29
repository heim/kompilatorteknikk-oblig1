package dflat.syntaxtree.type;

import dflat.exceptions.TypeNotDeclaredException;

public class ClassType extends Type {

	private Name name;

	public ClassType(Name name) {
		this.name = name;
	}

	public String printAst(int indent) {
		return indentTabs(indent) + "(TYPE " + name.printAst(0) + ")";
	}

    @Override
    public void checkSemantics() {
        if(symbolTable.lookup(name) == null) {
            throw new TypeNotDeclaredException(this);
        }
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof ClassType && (this.name.equals(((ClassType)obj).name));
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public boolean canBeCastTo(Type otherType) {
        return false;
    }
}
