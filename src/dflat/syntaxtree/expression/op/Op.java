package dflat.syntaxtree.expression.op;

import bytecode.CodeProcedure;
import dflat.syntaxtree.Node;

public abstract class Op extends Node {
    public abstract void generateCode(CodeProcedure codeProcedure);
}
