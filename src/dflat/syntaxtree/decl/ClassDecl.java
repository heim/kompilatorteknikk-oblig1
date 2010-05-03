package dflat.syntaxtree.decl;

import dflat.syntaxtree.type.ClassType;
import dflat.syntaxtree.type.Name;
import dflat.syntaxtree.type.Type;

import java.util.ArrayList;
import java.util.List;

public class ClassDecl extends Decl {

	private Name name;
	private List<VarDecl> varDecl;
    private ClassType classType;

    public ClassDecl(Name name, List<VarDecl> varDecl){
		//TODO: Sjekk om en tom klasse gir tom liste eller null-liste
        
        this.name = name;
		this.varDecl = varDecl;
        this.classType = new ClassType(name, varDecl);
	}



    @Override
	public String printAst(int indent) {
		String retval = indentTabs(indent) + "(CLASS "+ name.printAst(0) +"\n";
		for(VarDecl d :varDecl) {
			retval +=  d.printAst(indent + 1) + "\n";
		}
		return retval + indentTabs(indent) +  ")";
	}

    @Override
    public Type getType() {
        return classType;
    }

    @Override
    public Name getName() {
        return name; 
    }

    @Override
    public void checkSemantics() {
        buildSymbolTable();
    }

    private void buildSymbolTable() {
        symbolTable.insert(getName(), getType());
    
    }


    private Name mergeName(Name memberName) {
        return new Name(this.name.toString() + "." + memberName.toString());
    }
}
