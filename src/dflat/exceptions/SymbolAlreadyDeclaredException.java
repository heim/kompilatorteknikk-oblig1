package dflat.exceptions;

import dflat.syntaxtree.type.Name;

public class SymbolAlreadyDeclaredException extends RuntimeException {

    public SymbolAlreadyDeclaredException(Name name) {
        super(name.toString());
    }

}