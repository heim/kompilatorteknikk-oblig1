package dflat.syntaxtree.statement;

import dflat.exceptions.SymbolNotDeclaredException;
import dflat.syntaxtree.param.ActualParam;
import dflat.syntaxtree.param.FormalParam;
import dflat.syntaxtree.param.Param;
import dflat.syntaxtree.param.PassByReferenceParam;
import dflat.syntaxtree.type.ClassType;
import dflat.syntaxtree.type.FunctionName;
import dflat.syntaxtree.type.Name;
import dflat.syntaxtree.type.Type;

import java.util.List;

public class CallStatement extends Statement {

	private FunctionName name;
	private List<ActualParam> actualParamList;

	public CallStatement(Name name, List<ActualParam> actualParamList) {
		this.name = FunctionName.functionNameFactory(name, actualParamList);
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
        
        if(symbolTable.lookup(name) == null) {
            System.out.println("symbolTable.toString() = " + symbolTable.toString());
            for (ActualParam actualParam : actualParamList) {
                System.out.println("actualParam = " + actualParam.getType());
            }

            System.out.println("name.toString()");
            System.out.println(name.toString());
            
            throw new SymbolNotDeclaredException(this);
        }




    }

    @Override
    public Type getType() {
        return symbolTable.lookup(name);
    }

    @Override
    public String toString() {
        return "CallStatement. Calling " + name.toString();
    }
}
