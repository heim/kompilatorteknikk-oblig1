package dflat.syntaxtree.type;

import dflat.syntaxtree.Node;

public abstract class Type extends Node {

    public abstract Name getName();

    public abstract boolean canBeCastTo(Type otherType);
}
