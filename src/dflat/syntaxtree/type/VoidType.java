package dflat.syntaxtree.type;

/**
 * Created by IntelliJ IDEA.
 * User: andreas
 * Date: Apr 25, 2010
 * Time: 4:36:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class VoidType extends Type {
    
    @Override
    public String printAst(int indent) {
        return indentTabs(indent) + "void";  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof VoidType;
    }
}
