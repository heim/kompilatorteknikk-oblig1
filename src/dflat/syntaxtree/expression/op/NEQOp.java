package dflat.syntaxtree.expression.op;

public class NEQOp extends RelOp {

	public String printAst(int indent) {
		return indentTabs(indent) + "!=";
	}

    @Override
    public void checkSemantics() {
    }

}
