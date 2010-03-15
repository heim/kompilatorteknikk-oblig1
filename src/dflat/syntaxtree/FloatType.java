package dflat.syntaxtree;

public class FloatType extends Type {

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(TYPE float)";
	}

}
