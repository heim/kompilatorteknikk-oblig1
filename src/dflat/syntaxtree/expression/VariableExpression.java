package dflat.syntaxtree.expression;

import bytecode.CodeProcedure;
import bytecode.instructions.LOADGLOBAL;
import bytecode.instructions.LOADLOCAL;
import bytecode.instructions.STOREGLOBAL;
import bytecode.instructions.STORELOCAL;
import dflat.exceptions.SymbolNotDeclaredException;
import dflat.syntaxtree.type.Name;
import dflat.syntaxtree.type.Type;

import javax.naming.OperationNotSupportedException;

public class VariableExpression extends Expression {

    protected Name name;
    protected Type type;

    public VariableExpression(Name name) {
        this.name = name;

    }

    public String printAst(int indent) {
        return name.printAst(indent);
    }


    @Override
    public void checkSemantics() {
        Type t = symbolTable.lookup(name);
        this.type = t;
        if(t == null)
            throw new SymbolNotDeclaredException(this);

    }

    public Name getName() {
        return name;
    }

    @Override
    public Type getType() {
        return type;
    }

    public void generateCodeForStore(CodeProcedure codeProcedure) {
        int localVarNumber = codeProcedure.variableNumber(getName().toString());
        //Må finne ut om var er global eller lokal
        if(localVarNumber == -1) {
            int globalVarNumber = codeProcedure.globalVariableNumber(getName().toString());
            if(globalVarNumber == -1) {
                throw new RuntimeException("no such variable. Name:" + getName().toString());
            }
            codeProcedure.addInstruction(new STOREGLOBAL(globalVarNumber));
        } else {
            codeProcedure.addInstruction(new STORELOCAL(localVarNumber));
        }
    }

    @Override
    public void generateCode(CodeProcedure codeProcedure) {
        int localVarNumber = codeProcedure.variableNumber(getName().toString());
        //Må finne ut om var er global eller lokal
        if(localVarNumber == -1) {
            int globalVarNumber = codeProcedure.globalVariableNumber(getName().toString());
            if(globalVarNumber == -1) {
                throw new RuntimeException("no such variable. Name:" + getName().toString());
            }
            codeProcedure.addInstruction(new LOADGLOBAL(globalVarNumber));
        } else {
            codeProcedure.addInstruction(new LOADLOCAL(localVarNumber));
        }
    }

    @Override
    public String toString() {
        return "VariableExpression - var " + name.toString();
    }
}
