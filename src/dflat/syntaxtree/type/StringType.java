package dflat.syntaxtree.type;

public class StringType extends Type {

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(TYPE string)";
	}

}
