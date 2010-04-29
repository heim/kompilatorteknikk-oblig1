package dflat.syntaxtree.statement;

import dflat.syntaxtree.expression.Expression;
import dflat.syntaxtree.type.Type;
import dflat.syntaxtree.type.VoidType;

public class ReturnStatement extends Statement {

	private Expression expression;

	public ReturnStatement(Expression e) {
		if(e == null)
            this.expression = new Expression() {
                @Override
                public Type getType() {
                    return new VoidType();
                }

                @Override
                public String printAst(int indent) {
                    return "";
                }

                @Override
                public void checkSemantics() {
                }
            };
        else
            this.expression = e;
	}
	
	public String printAst(int indent) {
		if (expression.getType().equals(new VoidType())) {
			return indentTabs(indent) + "(RETURN_STMT)\n";
		} else {
			return indentTabs(indent) + "(RETURN_STMT \n" + expression.printAst(indent + 1) + "\n" + indentTabs(indent) + ")\n";
		}	
	}

    @Override
    public void checkSemantics() {
        expression.checkSemantics();
    }

    @Override
    public Type getType() {
       return expression.getType();
    }
}
