package dflat.syntaxtree;

import dflat.compiler.SymbolTable;

public abstract class Node {
	protected SymbolTable symbolTable = new SymbolTable();

    public Node() {
        
    }

    public abstract String printAst(int indent);
	protected String indentTabs(int indent) {
		String indentTabs = "";
		for(int i = 0; i < indent; i++) {
			indentTabs += "\t";
		}
		return indentTabs;
	}
}
