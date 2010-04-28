package dflat.syntaxtree.expression.op;

public class LTOp extends RelOp {

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + ">";
	}

    @Override
    public void checkSemantics() {
    }

}
