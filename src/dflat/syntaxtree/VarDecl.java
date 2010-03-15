package dflat.syntaxtree;

public class VarDecl extends Decl {

	private Type type;
	private Name name;

	public VarDecl(Type type, Name name) {
		this.type = type;
		this.name = name;
	}

	public String printAst(int indent) {
		return indentTabs(indent) + "(VAR_DECL " + type.printAst(0) + " (NAME " + name.printAst(0) + "))";
	}
}
