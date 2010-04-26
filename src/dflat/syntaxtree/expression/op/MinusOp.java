package dflat.syntaxtree.expression.op;

public class MinusOp extends AritOp {

	public String printAst(int indent) {
		return indentTabs(indent) + "-";
	}

}
