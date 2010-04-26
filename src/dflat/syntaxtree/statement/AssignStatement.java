package dflat.syntaxtree.statement;

import dflat.syntaxtree.expression.Expression;
import dflat.syntaxtree.expression.VariableExpression;

public class AssignStatement extends Statement {

	private VariableExpression var;
	private Expression expression;

	public AssignStatement(VariableExpression var, Expression exp) {
		this.var = var;
		this.expression = exp;


	}



	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(ASSIGN_STMT\n" + var.printAst(indent+ 1) + "\n" + expression.printAst(indent + 1) + "\n)\n";
		
	}
	
	
}
