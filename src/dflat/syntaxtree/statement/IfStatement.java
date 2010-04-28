package dflat.syntaxtree.statement;

import dflat.syntaxtree.expression.Expression;
import dflat.syntaxtree.type.Type;
import dflat.syntaxtree.type.VoidType;

import java.util.List;

public class IfStatement extends Statement {

	private List<Statement> ifStatements;
	protected Expression expression;

	public IfStatement(Expression exp, List<Statement> statements) {
		this.expression = exp;
		this.ifStatements = statements;
	}
	
	public String printAst(int indent) {
		String retVal = indentTabs(indent) + "(IF_STMT \n" + expression.printAst(indent+1) + "\n" + indentTabs(indent) + "(\n";
		for(Statement s : ifStatements) {
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
