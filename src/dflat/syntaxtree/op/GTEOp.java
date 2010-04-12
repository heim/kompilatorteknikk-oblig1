package dflat.syntaxtree.op;

public class GTEOp extends RelOp {

	@Override
	public String printAst(int indent) {
		 return indentTabs(indent) + ">=";
	}

}
