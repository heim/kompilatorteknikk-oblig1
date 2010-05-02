package dflat.syntaxtree.expression.literal;

import dflat.syntaxtree.type.Name;
import dflat.syntaxtree.type.Type;
import dflat.syntaxtree.type.VoidType;



public class NullLiteral extends Literal {

	@Override
	public String printAst(int indent) {
		return indentTabs(indent) + "(NULL)";
	}

    @Override
    public void checkSemantics() {
    }

    @Override
    public Type getType() {
        //TODO ?
        return new NullType();

    }


    class NullType extends Type {

        @Override
        public Name getName() {
            return new Name("nulltype");
        }

        @Override
        public boolean canBeCastTo(Type otherType) {
            return !(otherType instanceof VoidType);
        }

        @Override
        public String printAst(int indent) {
            return "";
        }

        @Override
        public void checkSemantics() {
        }
    }
}
