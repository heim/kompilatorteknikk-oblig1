package dflat.syntaxtree.op;

public class NEQOp extends RelOp {

	public String printAst(int indent) {
		return indentTabs(indent) + "!=";
	}

}
