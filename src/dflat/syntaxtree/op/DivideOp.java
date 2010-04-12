package dflat.syntaxtree.op;

public class DivideOp extends AritOp {

	public String printAst(int indent) {
		return indentTabs(indent) + "/";
	}

}
