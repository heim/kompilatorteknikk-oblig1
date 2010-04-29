package dflat.syntaxtree.statement;

import dflat.exceptions.IncompatibleTypeException;
import dflat.syntaxtree.expression.Expression;
import dflat.syntaxtree.type.BooleanType;
import dflat.syntaxtree.type.Type;
import dflat.syntaxtree.type.VoidType;

import java.util.List;

public class IfStatement extends Statement {

	private List<Statement> statements;
	protected Expression expression;

	public IfStatement(Expression exp, List<Statement> statements) {
		this.expression = exp;
		this.statements = statements;
	}
	
	public String printAst(int indent) {
		String retVal = indentTabs(indent) + "(IF_STMT \n" + expression.printAst(indent+1) + "\n" + indentTabs(indent) + "(\n";
		for(Statement s : statements) {
			retVal += s.printAst(indent + 1) + "\n";
		}
		retVal += indentTabs(indent) + ")\n";
		return retVal;
	}

    @Override
    public void checkSemantics() {
        expression.checkSemantics();
        if(!expression.getType().equals(new BooleanType()))
            throw new IncompatibleTypeException(expression);

        for (Statement statement : statements) {
            statement.checkSemantics();
        }

        
    }

    @Override
    public Type getType() {
        return new VoidType();
    }
}
