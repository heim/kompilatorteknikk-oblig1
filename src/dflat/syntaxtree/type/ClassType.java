package dflat.syntaxtree.type;

public class ClassType extends Type {

	private Name name;

	public ClassType(Name name) {
		this.name = name;
	}

	public String printAst(int indent) {
		return indentTabs(indent) + "(TYPE " + name.printAst(0) + ")";
	}


    @Override
    public boolean equals(Object obj) {
        return obj instanceof ClassType && (this.name.equals(((ClassType)obj).name));
    }
}
