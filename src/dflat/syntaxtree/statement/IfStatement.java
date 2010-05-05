package dflat.syntaxtree.statement;

import bytecode.CodeProcedure;
import bytecode.instructions.JMPFALSE;
import bytecode.instructions.NOP;
import dflat.exceptions.IncompatibleTypeException;
import dflat.syntaxtree.expression.Expression;
import dflat.syntaxtree.type.BooleanType;
import dflat.syntaxtree.type.Type;
import dflat.syntaxtree.type.VoidType;

import java.util.List;

public class IfStatement extends Statement {

	protected List<Statement> ifStatements;
	protected Expression expression;

	public IfStatement(Expression exp, List<Statement> ifStatements) {
		this.expression = exp;
		this.ifStatements = ifStatements;
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
        expression.checkSemantics();
        if(!expressionIsBoolean())
            throw new IncompatibleTypeException(expression);

        for (Statement statement : ifStatements) {
            statement.checkSemantics();
        }

        
    }

    private boolean expressionIsBoolean() {
        return expression.getType().equals(new BooleanType());
    }

    @Override
    public Type getType() {
        return new VoidType();
    }

    @Override
    public void generateCode(CodeProcedure procedure) {
        expression.generateCode(procedure);
        int ifJump = procedure.addInstruction(new JMPFALSE(0));
        for (Statement statement : ifStatements) {
            statement.generateCode(procedure);
        }

        int jumpTo = procedure.addInstruction(new NOP());
        procedure.replaceInstruction(ifJump, new JMPFALSE(jumpTo));
        
    }


}
