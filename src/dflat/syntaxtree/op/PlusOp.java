package dflat.syntaxtree.op;

public class PlusOp extends AritOp {

	public String printAst(int indent) {
		return indentTabs(indent) + "+";
	}

}
