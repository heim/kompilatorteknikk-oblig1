package dflat.exceptions;

import dflat.syntaxtree.Node;

public class SymbolAlreadyDeclaredException extends SemanticsException {

    public SymbolAlreadyDeclaredException(Node name) {
        super(name);
    }

}