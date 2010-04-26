package dflat.syntaxtree.type;

import dflat.syntaxtree.Node;

public class Name extends Node implements Comparable<Name> {

	private String name;

	public Name(String name) {
		this.name = name;
	}
	
	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(NAME " + name + ")";
	}

    public int compareTo(Name o) {
        return this.name.compareTo(o.name);
    }

}
