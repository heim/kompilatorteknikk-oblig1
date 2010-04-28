package dflat.exceptions;

import dflat.syntaxtree.Node;
import dflat.syntaxtree.type.ClassType;

public class SemanticsException extends RuntimeException {
    private Node offendingNode;

    public SemanticsException(Node offendingNode) {
        this.offendingNode = offendingNode;
    }
}
