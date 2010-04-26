package dflat.syntaxtree.decl;

import dflat.syntaxtree.type.Name;
import dflat.syntaxtree.Node;
import dflat.syntaxtree.type.Type;

public abstract class Decl extends Node {


    public Decl() {
        //TODO: add to symbol table.


    }

    public abstract Type getType();
    public abstract Name getName();


    public abstract void buildSymbolTable();
}
