package dflat.syntaxtree.statement;

import bytecode.CodeFile;
import bytecode.CodeProcedure;
import bytecode.instructions.Instruction;
import dflat.syntaxtree.expression.Expression;

import java.util.List;

public abstract class Statement extends Expression {

    public abstract void generateCode(CodeProcedure procedure);
}
