package dflat.syntaxtree.expression.op;


public class GTOp extends RelOp {

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + ">";
	}

    @Override
    public void checkSemantics() {
    }

}
