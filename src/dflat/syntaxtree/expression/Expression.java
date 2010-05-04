package dflat.syntaxtree.expression;

import bytecode.CodeProcedure;
import dflat.syntaxtree.Node;
import dflat.syntaxtree.type.Type;

public abstract class Expression extends Node {

    public abstract Type getType();

    public abstract void generateCode(CodeProcedure codeProcedure);
}
