package dflat.syntaxtree.expression.op;

public class DivideOp extends AritOp {

	public String printAst(int indent) {
		return indentTabs(indent) + "/";
	}

    @Override
    public void checkSemantics() {
    }

}
