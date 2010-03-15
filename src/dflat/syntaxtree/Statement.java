package dflat.syntaxtree;

public class Statement extends Node {

	@Override
	public String printAst(int indent) {
		
		return indentTabs(indent) + "this is a statement";
	}

}
