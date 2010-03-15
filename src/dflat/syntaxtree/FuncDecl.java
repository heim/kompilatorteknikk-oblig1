package dflat.syntaxtree;

import java.util.List;

public class FuncDecl extends Decl {

	private String name;
	private Type returnType;
	private List<Decl> declList;
	private List<Param> paramList;
	private List<Statement> statementList;

	public FuncDecl(String name, List<Param> paramList, Type returnType, List<Decl> declList, List<Statement> statementList) {
		this.name = name;
		this.returnType = returnType;
		this.declList = declList;
		this.paramList = paramList;
		this.statementList = statementList;
	}
	
	public String printAst(int indent) {
		String returnTypeDesc = "";
		
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
}
