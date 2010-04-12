package dflat.syntaxtree.op;

public class MinusOp extends AritOp {

	public String printAst(int indent) {
		return indentTabs(indent) + "-";
	}

}
