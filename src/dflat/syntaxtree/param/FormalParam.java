package dflat.syntaxtree.param;

import dflat.exceptions.SemanticsException;
import dflat.syntaxtree.Node;
import dflat.syntaxtree.type.Name;
import dflat.syntaxtree.type.Type;

public class FormalParam extends Param {

	
	private boolean ref;
	private Type type ;
	private Name name;
	public FormalParam(boolean ref, Type type, Name name) {
		this.ref = ref;
		this.type = type;
		this.name = name;
	}
	@Override
	public String printAst(int indent) {
		String refString = "";
		if(ref) refString = "ref "; 
		return indentTabs(indent) + "(PARAM_DECL " +  refString + type.printAst(0) + name.printAst(0);
	}

    @Override
    public void checkSemantics() {
        if(symbolTable.lookup(type.getName()) == null) {
            throw new SemanticsException(this);

        }
        symbolTable.insert(name, type);
    }

    public Type getType() {
        return type;
    }

    public Name getName() {
        return name;
    }

    @Override
    public boolean getIsRef() {
        return ref;
    }


}
