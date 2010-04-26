package dflat.syntaxtree.expression.op;

public class ExponentOp extends AritOp {

	public String printAst(int indent) {

		return indentTabs(indent) + "**";
	}

}
