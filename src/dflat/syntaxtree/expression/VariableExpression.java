package dflat.syntaxtree.expression;

import dflat.exceptions.SymbolNotDeclaredException;
import dflat.syntaxtree.type.Name;
import dflat.syntaxtree.type.Type;

public class VariableExpression extends Expression {

	protected Name name;
    private Type type;

    public VariableExpression(Name name) {
		this.name = name;
	}
	
	public String printAst(int indent) {
		return name.printAst(indent);
	}

    @Override
    public void checkSemantics() {
        Type t = symbolTable.lookup(name);
        this.type = t;
        if(t == null)
            throw new SymbolNotDeclaredException(this);

    }

    @Override
    public Type getType() {
        return type;
    }
}
