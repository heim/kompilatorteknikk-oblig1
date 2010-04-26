package dflat.syntaxtree.decl;

import dflat.syntaxtree.decl.Decl;
import dflat.syntaxtree.param.Param;
import dflat.syntaxtree.statement.Statement;
import dflat.syntaxtree.type.Name;
import dflat.syntaxtree.type.Type;
import dflat.syntaxtree.type.VoidType;

import java.util.List;

public class FuncDecl extends Decl {

	private Name name;
	private Type returnType;
	private List<Decl> declList;
	private List<Param> paramList;
	private List<Statement> statementList;

	public FuncDecl(Name name, List<Param> paramList, Type returnType, List<Decl> declList, List<Statement> statementList) {
		this.name = name;
		this.returnType = returnType != null ? returnType : new VoidType();
		this.declList = declList;
		this.paramList = paramList;
		this.statementList = statementList;


	}
	
	public String printAst(int indent) {
		String returnTypeDesc = "(TYPE void)";
		
		if(returnType != null) {
			returnTypeDesc = returnType.printAst(0);
		}
		
		String retVal = indentTabs(indent) + "(FUNC_DECL " + returnTypeDesc +" (NAME " + name + ")\n";
		for(Param p : paramList) {
			retVal += p.printAst(indent + 1) + "\n";
		}
		
		for(Decl d : declList) {
			retVal += d.printAst(indent + 1) + "\n";
		}
		
		for(Statement s : statementList) {
			retVal += s.printAst(indent + 1) + "\n";
		}
		
		return retVal + indentTabs(indent) +  ")";
	}

    @Override
    public Type getType() {
        return returnType;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Name getName() {
        return name;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void buildSymbolTable() {

        symbolTable.insert(getName(), getType());
        symbolTable.enter_scope();
        for(Decl d : declList) {
            d.buildSymbolTable();
        }
        symbolTable.exit_scope();
    }
}
