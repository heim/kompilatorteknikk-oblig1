package dflat.syntaxtree;

public class Name extends Node {

	private String name;

	public Name(String name) {
		this.name = name;
	}
	
	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(NAME " + name + ")";
	}

}
