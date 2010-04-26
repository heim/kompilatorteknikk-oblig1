package dflat.syntaxtree.expression.op;

public class EQOp extends RelOp {

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "=";
	}

}
