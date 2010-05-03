package dflat.syntaxtree.decl;

import dflat.syntaxtree.type.Name;
import dflat.syntaxtree.Node;
import dflat.syntaxtree.type.Type;

public abstract class Decl extends Node {




    public abstract Type getType();
    public abstract Name getName();


    
}
