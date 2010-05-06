package dflat.exceptions;

import dflat.syntaxtree.Node;
import dflat.syntaxtree.type.ClassType;

public class SemanticsException extends RuntimeException {
    protected Node offendingNode;

    public SemanticsException(Node offendingNode) {
        this.offendingNode = offendingNode;
    }

    @Override
    public String toString() {
        return "Uspesifisert semantikk-feil."+ offendingNode.toString();
    }
}
