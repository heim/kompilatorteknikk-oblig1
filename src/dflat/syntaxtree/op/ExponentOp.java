package dflat.syntaxtree.op;

public class ExponentOp extends AritOp {

	public String printAst(int indent) {

		return indentTabs(indent) + "**";
	}

}
