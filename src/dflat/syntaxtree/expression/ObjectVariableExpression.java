package dflat.syntaxtree.expression;

import bytecode.CodeProcedure;
import bytecode.instructions.GETFIELD;
import bytecode.instructions.LOADLOCAL;
import bytecode.instructions.PUTFIELD;
import dflat.exceptions.IncompatibleReturnTypeException;
import dflat.exceptions.SymbolNotDeclaredException;
import dflat.syntaxtree.type.ClassType;
import dflat.syntaxtree.type.Name;
import dflat.syntaxtree.type.Type;

public class ObjectVariableExpression extends VariableExpression {

    private Expression expression;



    public ObjectVariableExpression(Expression expression, Name name) {
        super(name);
        this.expression = expression;
    }

    @Override
    public void checkSemantics() {
        expression.checkSemantics();
        checkReturnTypeIsClassType();

        this.type = getDeclaredTypeFromClass();


    }

    private Type getDeclaredTypeFromClass() {
        ClassType classType = (ClassType)symbolTable.lookup(expression.getType().getName());
        if(classType != null) {
            Type memberType = classType.hasMember(name);
            if(memberType != null)
                return memberType;

        }
        throw new SymbolNotDeclaredException(this);

    }

    private void checkReturnTypeIsClassType() {
        if(!(expression.getType() instanceof ClassType)){
            throw new IncompatibleReturnTypeException(expression);
        }
    }

    @Override
    public Type getType() {
        return super.getType();
    }

    @Override
    public void generateCode(CodeProcedure codeProcedure) {
        expression.generateCode(codeProcedure);
        int structNumber = codeProcedure.structNumber(expression.getType().getName().toString());
        int fieldNumber = codeProcedure.fieldNumber(expression.getType().getName().toString(), name.toString());
        codeProcedure.addInstruction(new GETFIELD(fieldNumber, structNumber));
    }

    @Override
    public void generateCodeForStore(CodeProcedure codeProcedure) {
        expression.generateCode(codeProcedure);
        int structNumber = codeProcedure.structNumber(expression.getType().getName().toString());
        int fieldNumber = codeProcedure.fieldNumber(expression.getType().getName().toString(), name.toString());
        codeProcedure.addInstruction(new PUTFIELD(fieldNumber, structNumber));
    }

    public String printAst(int indent) {
        return indentTabs(indent) + "( . " + expression.printAst(0) + " " + name.printAst(0) + ")"
                ;	}
}
