package dflat.syntaxtree.decl;

import dflat.exceptions.SemanticsException;
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
    public void checkSemantics() {

        symbolTable.insert(getName(), getType());
        symbolTable.enter_scope();
        checkReturnTypeSemantics();
        checkParameterSemantics();
        checkSemanticsForDeclarations();
        checkSemanticsForStatements();
        symbolTable.exit_scope();
    }

    private void checkReturnTypeSemantics() {
        returnType.checkSemantics();
    }

    private void checkParameterSemantics() throws SemanticsException {

        for (Param param : paramList) {
            param.checkSemantics();
        }
    }

    private void checkSemanticsForStatements() throws SemanticsException {
        for (Statement statement : statementList) {
            statement.checkSemantics();
        }
    }

    private void checkSemanticsForDeclarations() {
        for(Decl d : declList) {
            d.checkSemantics();
        }
    }
}
