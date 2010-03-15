package dflat.syntaxtree;

import java.util.List;

public class ClassDecl extends Decl {

	private String name;
	private List<VarDecl> varDecl;

	public ClassDecl(String name, List<VarDecl> varDecl){
		this.name = name;
		this.varDecl = varDecl;
	}
	
	@Override
	public String printAst(int indent) {
		String retval = indentTabs(indent) + "(CLASS (NAME "+ name +")\n";
		for(VarDecl d :varDecl) {
			retval +=  d.printAst(indent + 1);
		}
		return retval + indentTabs(indent) +  ")";
	}

}
