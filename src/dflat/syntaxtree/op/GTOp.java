package dflat.syntaxtree.op;


public class GTOp extends RelOp {

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + ">";
	}

}
