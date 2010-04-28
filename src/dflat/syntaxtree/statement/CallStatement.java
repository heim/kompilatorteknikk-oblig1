package dflat.syntaxtree.statement;

import dflat.syntaxtree.param.ActualParam;
import dflat.syntaxtree.type.Name;
import dflat.syntaxtree.type.Type;

import java.util.List;

public class CallStatement extends Statement {

	private Name name;
	private List<ActualParam> actualParamList;

	public CallStatement(Name name, List<ActualParam> actualParamList) {
		this.name = name;
		this.actualParamList = actualParamList;
	}
	
	public String printAst(int indent) {
		String retVal = indentTabs(indent) + "(CALL_STMT " + name.printAst(0) + "\n";
		
		for(ActualParam p : actualParamList) {
			retVal += p.printAst(indent + 1) + "\n"; 
		}
		
		return retVal;
	}

    @Override
    public void checkSemantics() {
    }

    @Override
    public Type getType() {
        return symbolTable.lookup(name);
    }
}
