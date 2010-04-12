package dflat.syntaxtree;

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

}
