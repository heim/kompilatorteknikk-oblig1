package dflat.syntaxtree;

public class IntegerType extends Type {

	@Override
	public String printAst(int indent) {
		
		return indentTabs(indent) + "(TYPE int)";
	}


}
