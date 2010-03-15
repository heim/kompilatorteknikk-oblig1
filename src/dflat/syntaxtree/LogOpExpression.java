package dflat.syntaxtree;

public class LogOpExpression extends OpExpression {

	private Expression exp1;
	private Expression exp2;
	private LogOp op;

	public LogOpExpression(Expression exp1, LogOp op, Expression exp2) {
		this.exp1 = exp1;
		this.op = op;
		this.exp2 = exp2;
		
	}
	
	@Override
	public String printAst(int indent) {
		String retVal = indentTabs(indent) + "(LOG OP " + op.printAst(0) + "\n" + exp1.printAst(indent + 1) + "\n" + exp2.printAst(indent + 1) + "\n" + indentTabs(indent) + ")";
		return retVal;
	}

}
