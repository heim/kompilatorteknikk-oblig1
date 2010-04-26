package dflat.syntaxtree.expression.literal;

public class NullLiteral extends Literal {

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(NULL)";
	}

}
