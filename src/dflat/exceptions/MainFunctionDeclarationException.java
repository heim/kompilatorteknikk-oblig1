package dflat.exceptions;

import dflat.syntaxtree.Node;

public class MainFunctionDeclarationException extends SemanticsException {
    public MainFunctionDeclarationException(Node offendingNode) {
        super(offendingNode);
    }

    @Override
    public String getMessage() {
        return "Main method declared with parameters and/or return type, or not declared at all.";
    }
}
