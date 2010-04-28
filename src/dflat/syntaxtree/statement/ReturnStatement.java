package dflat.syntaxtree.statement;

import dflat.syntaxtree.expression.Expression;
import dflat.syntaxtree.type.Type;
import dflat.syntaxtree.type.VoidType;

public class ReturnStatement extends Statement {

	private Expression expression;

	public ReturnStatement(Expression e) {
		this.expression = e;
	}
	
	public String printAst(int indent) {
		if (expression == null) {
			return indentTabs(indent) + "(RETURN_STMT)\n";
		} else {
			return indentTabs(indent) + "(RETURN_STMT \n" + expression.printAst(indent + 1) + "\n" + indentTabs(indent) + ")\n";
		}	
	}

    @Override
    public void checkSemantics() {
        
    }

    @Override
    public Type getType() {
        if(expression == null)
            return new VoidType();
        else
            return expression.getType();
    }
}
