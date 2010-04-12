package dflat.syntaxtree;

import java.util.List;

public class ClassDecl extends Decl {

	private Name name;
	private List<VarDecl> varDecl;

	public ClassDecl(Name name, List<VarDecl> varDecl){
		this.name = name;
		this.varDecl = varDecl;
	}
	
	@Override
	public String printAst(int indent) {
		String retval = indentTabs(indent) + "(CLASS "+ name.printAst(0) +"\n";
		for(VarDecl d :varDecl) {
			retval +=  d.printAst(indent + 1) + "\n";
		}
		return retval + indentTabs(indent) +  ")";
	}

}
