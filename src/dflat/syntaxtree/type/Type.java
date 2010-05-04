package dflat.syntaxtree.type;

import bytecode.type.CodeType;
import dflat.syntaxtree.Node;

public abstract class Type extends Node {

    public abstract Name getName();

    public abstract boolean canBeCastTo(Type otherType);


    public abstract CodeType getByteCodeType();
}
