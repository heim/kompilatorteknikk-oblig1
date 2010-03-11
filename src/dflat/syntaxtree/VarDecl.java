package dflat.syntaxtree;

public class VarDecl extends Decl {

	private Type type;
	private String name;

	public VarDecl(Type type, String name) {
		this.type = type;
		this.name = name;
	}

	public String printAst(int indent) {
		return indentTabs(indent) + "(VAR_DECL " + type.printAst(0) + " (NAME " + name + "))";
	}
}
