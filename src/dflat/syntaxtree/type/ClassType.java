package dflat.syntaxtree.type;

import dflat.exceptions.TypeNotDeclaredException;
import dflat.syntaxtree.decl.VarDecl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassType extends Type {

	private Name name;
    private Map<Name, Type> memberMap;

    public ClassType(Name name) {
        this.name = name;
    }

    public ClassType(Name name, List<VarDecl> memberList) {
		this.name = name;
        memberMap = new HashMap<Name, Type>();
        for (VarDecl varDecl : memberList) {
            memberMap.put(varDecl.getName(), varDecl.getType());
        }
	}

	public String printAst(int indent) {
		return indentTabs(indent) + "(TYPE " + name.printAst(0) + ")";
	}

    public Type hasMember(Name name) {
       return memberMap.get(name);
    }

    @Override
    public void checkSemantics() {
        if(symbolTable.lookup(name) == null) {
            throw new TypeNotDeclaredException(this);
        } else {
            //TODO: fixme. hacketyhack. potensielt skikkelig ræva.
            this.memberMap = ((ClassType)symbolTable.lookup(name)).memberMap;
        }
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof ClassType && (this.name.equals(((ClassType)obj).name));
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public boolean canBeCastTo(Type otherType) {
        return otherType instanceof ClassType && (otherType.getName().equals(getName()));
    }
}
