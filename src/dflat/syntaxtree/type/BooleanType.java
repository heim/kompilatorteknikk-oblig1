package dflat.syntaxtree.type;

public class BooleanType extends Type {

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(TYPE boolean)";
	}

}
