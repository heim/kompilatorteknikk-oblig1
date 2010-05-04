package dflat.syntaxtree.type;

import bytecode.type.CodeType;

/**
 * Created by IntelliJ IDEA.
 * User: andreas
 * Date: Apr 25, 2010
 * Time: 4:36:08 PM
 */
public class VoidType extends Type {
    
    @Override
    public String printAst(int indent) {
        return indentTabs(indent) + "(TYPE void)";  
    }

    @Override
    public void checkSemantics() {
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof VoidType;
    }

    @Override
    public Name getName() {
        return new Name("void");
    }

    @Override
    public boolean canBeCastTo(Type otherType) {
        return otherType instanceof VoidType;
    }

    @Override
    public CodeType getByteCodeType() {
        return bytecode.type.VoidType.TYPE;
    }
}
