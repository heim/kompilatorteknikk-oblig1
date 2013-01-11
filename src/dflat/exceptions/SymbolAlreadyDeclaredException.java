package dflat.exceptions;

import dflat.syntaxtree.Node;

public class SymbolAlreadyDeclaredException extends SemanticsException {

    public SymbolAlreadyDeclaredException(Node name) {
        super(name);
    }

    @Override
    public String getMessage() {
        return "Symbol already declared. " + (offendingNode != null ? offendingNode.toString() : "");
    }
}