package dflat.exceptions;

import dflat.syntaxtree.Node;

public class MainFunctionDeclarationException extends SemanticsException {
    public MainFunctionDeclarationException(Node offendingNode) {
        super(offendingNode);
    }
}
