package dflat.syntaxtree.param;

import dflat.syntaxtree.Node;
import dflat.syntaxtree.type.Type;

public abstract class Param extends Node {
    public abstract Type getType();
    public abstract boolean getIsRef();

    @Override
    public boolean equals(Object obj) {
        if(isParam(obj)) {
            Param other = (Param) obj;

            if(hasSameType(other) && hasSameRefValue(other)) {
                return true;
            }

        }
        return false;
    }

    private boolean hasSameRefValue(Param other) {
        return this.getIsRef() == other.getIsRef();
    }

    private boolean hasSameType(Param other) {
        
        return this.getType().equals(other.getType());
    }

    private boolean isParam(Object obj) {
        return obj instanceof Param;
    }
}
