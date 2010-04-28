package dflat.syntaxtree.expression;

import dflat.exceptions.TypeNotDeclaredException;
import dflat.syntaxtree.type.Name;
import dflat.syntaxtree.type.Type;

public class VariableExpression extends Expression {

	protected Name name;

	public VariableExpression(Name name) {
		this.name = name;
	}
	
	public String printAst(int indent) {
		return name.printAst(indent);
	}

    @Override
    public void checkSemantics() {
        if(symbolTable.lookup(name) == null)
            throw new TypeNotDeclaredException(this);
    }

    @Override
    public Type getType() {
        return null;
    }
}
