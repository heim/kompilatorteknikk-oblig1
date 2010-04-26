package dflat.syntaxtree.expression;

import dflat.syntaxtree.type.Name;

public class ObjectVariableExpression extends VariableExpression {

	private Expression expression;
	

	public ObjectVariableExpression(Expression expression, Name name) {
		super(name);
		this.expression = expression;
	}
	
	public String printAst(int indent) {
		return indentTabs(indent) + "( . " + expression.printAst(0) + " " + name.printAst(0) + ")"
;	}
}
