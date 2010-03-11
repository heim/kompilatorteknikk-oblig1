package dflat.syntaxtree;

public abstract class Node {
	public abstract String printAst(int indent);
	protected String indentTabs(int indent) {
		String indentTabs = "";
		for(int i = 0; i < indent; i++) {
			indentTabs += "\t";
		}
		return indentTabs;
	}
}
