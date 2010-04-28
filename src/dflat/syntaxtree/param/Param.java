package dflat.syntaxtree.param;

import dflat.exceptions.SemanticsException;
import dflat.syntaxtree.Node;
import dflat.syntaxtree.type.Type;

public class Param extends Node {

	
	private boolean ref;
	private Type type ;
	private String name;
	public Param(boolean ref, Type type, String name) {
		this.ref = ref;
		this.type = type;
		this.name = name;
	}
	@Override
	public String printAst(int indent) {
		String refString = "";
		if(ref) refString = "ref "; 
		return indentTabs(indent) + "(PARAM_DECL " +  refString + type.printAst(0) + "(NAME " + name + "))";
	}

    @Override
    public void checkSemantics() {
        if(symbolTable.lookup(type.getName()) == null) {
            throw new SemanticsException(this);

        }
    }

}
