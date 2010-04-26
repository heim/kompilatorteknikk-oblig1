package dflat.syntaxtree.decl;

import dflat.syntaxtree.type.ClassType;
import dflat.syntaxtree.type.Name;
import dflat.syntaxtree.type.Type;
import dflat.syntaxtree.decl.VarDecl;
import dflat.syntaxtree.decl.Decl;

import java.util.List;

public class ClassDecl extends Decl {

	private Name name;
	private List<VarDecl> varDecl;
    private ClassType classType;

    public ClassDecl(Name name, List<VarDecl> varDecl){
		this.name = name;
		this.varDecl = varDecl;
        this.classType = new ClassType(name);
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
    public void buildSymbolTable() {
        symbolTable.enter_scope();
        symbolTable.insert(getName(), getType());

        for(Decl d : varDecl) {
            d.buildSymbolTable();
        }

        symbolTable.exit_scope();

    }


}
