package dflat.syntaxtree.op;

public class LTOp extends RelOp {

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + ">";
	}

}
