package dflat.syntaxtree;

public class OrOp extends LogOp {
	public String printAst(int indent) {
		return indentTabs(indent) + "||";
	}

}
