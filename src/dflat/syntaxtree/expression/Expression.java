package dflat.syntaxtree.expression;

import dflat.syntaxtree.Node;
import dflat.syntaxtree.type.Type;

public abstract class Expression extends Node {

    public abstract Type getType();
}
