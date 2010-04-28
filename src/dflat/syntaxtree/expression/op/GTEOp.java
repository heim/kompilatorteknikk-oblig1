package dflat.syntaxtree.expression.op;

public class GTEOp extends RelOp {

	@Override
	public String printAst(int indent) {
		 return indentTabs(indent) + ">=";
	}

    @Override
    public void checkSemantics() {
    }

}
