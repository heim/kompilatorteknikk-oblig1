package dflat.exceptions;

import dflat.syntaxtree.Node;

public class IncompatibleReturnTypeException extends SemanticsException {
    public IncompatibleReturnTypeException(Node offendingNode) {
        super(offendingNode);
    }

    @Override
    public String getMessage() {
        return "Incompatible return type" + offendingNode.toString();
    }
}
