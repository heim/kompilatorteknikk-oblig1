package dflat.syntaxtree;

public class ClassType extends Type {

	private Name name;

	public ClassType(Name name) {
		this.name = name;
	}

	public String printAst(int indent) {
		return indentTabs(indent) + "(TYPE " + name.printAst(0) + ")";
	}

	
	
}
