package dflat.exceptions;

import dflat.syntaxtree.Node;

public class FunctionMustHaveReturnStatementException extends SemanticsException
{
    public FunctionMustHaveReturnStatementException(Node offendingNode) {
        super(offendingNode);
    }

    @Override
    public String getMessage() {
        return "Function must have a return statement." + offendingNode.toString(); 
    }
}
