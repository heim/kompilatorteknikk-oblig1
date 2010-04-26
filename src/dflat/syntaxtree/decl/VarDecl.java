package dflat.syntaxtree.decl;

import dflat.syntaxtree.type.Name;
import dflat.syntaxtree.type.Type;

public class VarDecl extends Decl {

	private Type type;
	private Name name;

	public VarDecl(Type type, Name name) {
		this.type = type;
		this.name = name;
	}

	public String printAst(int indent) {
		return indentTabs(indent) + "(VAR_DECL " + type.printAst(0) +  name.printAst(0) + ")";
	}

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public Name getName() {
        return name;  
    }

    @Override
    public void buildSymbolTable() {
        symbolTable.insert(getName(), getType());
    }
}