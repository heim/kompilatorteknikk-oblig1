package dflat.exceptions;

import dflat.syntaxtree.Node;

public class IncompatibleTypeException extends SemanticsException {
    public IncompatibleTypeException(Node offendingNode) {
        super(offendingNode);
    }

    @Override
    public String getMessage() {
        return "Incompatible Type"+ offendingNode.toString();
    }
}
