package dflat.syntaxtree.statement;

import bytecode.CodeProcedure;
import dflat.syntaxtree.expression.Expression;
import dflat.syntaxtree.type.Type;
import dflat.syntaxtree.type.VoidType;

import java.util.List;

public class IfElseStatement extends IfStatement {

	private List<Statement> elseStatements;

	public IfElseStatement(Expression exp, List<Statement> ifStatements, List<Statement> elseStatements) {
		super(exp, ifStatements);
		this.elseStatements = elseStatements;
	}
	
	public String printAst(int indent) {
		String retVal = super.printAst(indent);
		
		retVal += "\n" + indentTabs(indent) + "(ELSE_STMT \n" + expression.printAst(indent+1) + "\n" + indentTabs(indent) + "(\n";
		for(Statement s : elseStatements) {
			retVal += s.printAst(indent + 2) + "\n";
		}
		retVal += ")\n";
		return retVal;
		
	}

    @Override
    public void checkSemantics() {
        super.checkSemantics();
        for (Statement elseStatement : elseStatements) {
            elseStatement.checkSemantics();
        }
    }

    @Override
    public Type getType() {
        return new VoidType();
    }

    @Override
    public void generateCode(CodeProcedure procedure) {
       super.generateCode(procedure);

        for (Statement elseStatement : elseStatements) {
            elseStatement.generateCode(procedure);
        }
    }
}
