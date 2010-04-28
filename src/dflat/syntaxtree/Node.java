package dflat.syntaxtree;

import dflat.compiler.SymbolTable;
import dflat.exceptions.SemanticsException;

public abstract class Node {
	protected static SymbolTable symbolTable = new SymbolTable();

    public Node() {
    }

    public static SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public abstract String printAst(int indent);
    
	protected String indentTabs(int indent) {
		String indentTabs = "";
		for(int i = 0; i < indent; i++) {
			indentTabs += "\t";
		}
		return indentTabs;
	}


    public abstract void checkSemantics();
}
