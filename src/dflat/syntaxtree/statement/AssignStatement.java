package dflat.syntaxtree.statement;

import bytecode.CodeProcedure;
import dflat.exceptions.IncompatibleReturnTypeException;
import dflat.syntaxtree.expression.Expression;
import dflat.syntaxtree.expression.VariableExpression;
import dflat.syntaxtree.type.Type;
import dflat.syntaxtree.type.VoidType;

public class AssignStatement extends Statement {

	private VariableExpression var;
	private Expression expression;

	public AssignStatement(VariableExpression var, Expression exp) {
		this.var = var;
		this.expression = exp;
	}



	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(ASSIGN_STMT\n" + var.printAst(indent+ 1) + "\n" + expression.printAst(indent + 1) + "\n)\n";
		
	}

    @Override
    public void checkSemantics() {
        var.checkSemantics();
        expression.checkSemantics();

        varDeclAndExpMustHaveCompatibleReturnTypes();



    }

    private void varDeclAndExpMustHaveCompatibleReturnTypes() {
        Type expressionType = expression.getType();
        Type varType = var.getType();
        if(!expressionType.canBeCastTo(varType)) {
            System.out.println("expressionType = " + expressionType.getName());
            throw new IncompatibleReturnTypeException(expression);

        }
    }


    @Override
    public Type getType() {
        return new VoidType();
    }

    @Override
    public void generateCode(CodeProcedure proc) {
        expression.generateCode(proc); //last value is on the stack
        var.generateCodeForStore(proc);
    }
}
