package dflat.syntaxtree.expression;

import bytecode.CodeProcedure;
import bytecode.instructions.NOT;
import dflat.exceptions.IncompatibleTypeException;
import dflat.syntaxtree.type.BooleanType;
import dflat.syntaxtree.type.Type;

public class NegatedExpression extends Expression {

	private Expression expression;

	public NegatedExpression(Expression exp) {
		this.expression = exp;
	}
	
	public String printAst(int indent){
		return indentTabs(indent) + "(NEGATED_EXP " + expression.printAst(0) + ")";
	}

    @Override
    public void checkSemantics() {
        expression.checkSemantics();
        if(!expressionIsBoolean()) {
            throw new IncompatibleTypeException(this);
        }
    }

    private boolean expressionIsBoolean() {
        return expression.getType().equals(new BooleanType());
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }

    @Override
    public void generateCode(CodeProcedure codeProcedure) {
        expression.generateCode(codeProcedure);
        codeProcedure.addInstruction(new NOT());
    }
}
