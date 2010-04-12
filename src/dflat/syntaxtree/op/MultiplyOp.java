package dflat.syntaxtree.op;

public class MultiplyOp extends AritOp {

	public String printAst(int indent) {
		return indentTabs(indent) + "*";
	}

}
