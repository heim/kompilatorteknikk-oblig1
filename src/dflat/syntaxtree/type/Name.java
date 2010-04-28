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

    @Override
    public void checkSemantics() {
    }

    public int compareTo(Name o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Name) && this.name.equals(((Name)other).name);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
