package dflat.syntaxtree.expression;

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

    public String printAst(int indent) {
        return indentTabs(indent) + "( . " + expression.printAst(0) + " " + name.printAst(0) + ")"
                ;	}
}
