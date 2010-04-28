package dflat.syntaxtree.statement;

import dflat.syntaxtree.expression.Expression;
import dflat.syntaxtree.type.Type;
import dflat.syntaxtree.type.VoidType;

import java.util.List;

public class WhileStatement extends Statement {

	private Expression expression;
	private List<Statement> statementList;

	public WhileStatement(Expression exp, List<Statement> statements) {
		this.expression = exp;
		this.statementList = statements;
	}
	
	@Override
	public String printAst(int indent) {
		String retVal = indentTabs(indent) + "(WHILE_STMT \n" + expression.printAst(indent+1) + "\n" + indentTabs(indent) + "(\n";
		for(Statement s : statementList) {
			retVal += s.printAst(indent + 1) + "\n";
		}
		retVal += indentTabs(indent) + ")\n";
		return retVal;
	}

    @Override
    public void checkSemantics() {
    }

    @Override
    public Type getType() {
        return new VoidType();
    }
}
