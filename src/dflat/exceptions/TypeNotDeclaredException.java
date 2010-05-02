package dflat.exceptions;

import dflat.syntaxtree.Node;
import dflat.syntaxtree.type.ClassType;

public class TypeNotDeclaredException extends SemanticsException {

    public TypeNotDeclaredException(Node offendingNode) {
        super(offendingNode);
    }

    @Override
    public String getMessage() {
        return "Type not declared.";
    }
}
