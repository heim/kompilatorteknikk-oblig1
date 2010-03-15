package dflat.syntaxtree;

import java.util.List;

public class CallStatement extends Expression {

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
}
