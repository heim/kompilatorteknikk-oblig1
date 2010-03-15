package dflat.syntaxtree;

public class AndOp extends LogOp {
	public String printAst(int indent) {
		return indentTabs(indent) + "&&";
	}

}
