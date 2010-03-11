package dflat.syntaxtree;

public class Type extends Node {

	private String name;

	public Type(String name) {
		this.name = name;
	}

	public String printAst(int indent) {
		return indentTabs(indent) + "(TYPE " + name + ")";
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	
}
