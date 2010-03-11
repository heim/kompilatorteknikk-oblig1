package dflat.syntaxtree;

import java.util.List;

public class FuncDecl extends Decl {

	private String name;
	private Type returnType;
	private List<Decl> declList;
	private List<Param> paramList;

	public FuncDecl(String name, List<Param> paramList, Type returnType, List<Decl> declList) {
		this.name = name;
		this.returnType = returnType;
		this.declList = declList;
		this.paramList = paramList;
	}
	
	public String printAst(int indent) {
		
		
		String retVal = indentTabs(indent) + "(FUNC_DECL " + returnType.printAst(0) +" (NAME " + name + ")\n";
		for(Param p : paramList) {
			retVal += p.printAst(indent + 1) + "\n";
		}
		
		for(Decl d : declList) {
			retVal += d.printAst(indent + 1) + "\n";
		}
		
		return retVal + indentTabs(indent) +  ")";
	}
}
