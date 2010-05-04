package dflat.syntaxtree.statement;

import bytecode.CodeProcedure;
import bytecode.instructions.CALL;
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
    private Name formalName;

    public CallStatement(Name name, List<ActualParam> actualParamList) {
        this.formalName = name;
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

        for (ActualParam actualParam : actualParamList) {
            actualParam.checkSemantics();
        }
        //make function name here, so we are sure param list has checked its semantics
        this.name = FunctionName.functionNameFactory(formalName, actualParamList);
        if(symbolTable.lookup(name) == null) {
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

    @Override
    public void generateCode(CodeProcedure procedure) {

        for (ActualParam actualParam : actualParamList) {
            actualParam.generateCode(procedure);
        }
        int funcNum = procedure.procedureNumber(name.getName());
        procedure.addInstruction(new CALL(funcNum));
    }
}
