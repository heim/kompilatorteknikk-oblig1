package dflat.exceptions;

import dflat.syntaxtree.Node;
import dflat.syntaxtree.expression.VariableExpression;

public class SymbolNotDeclaredException extends SemanticsException {

    public SymbolNotDeclaredException(Node offendingNode) {
        super(offendingNode);
    }

    @Override
    public String getMessage() {
        return "Symbol not declared. " + (offendingNode != null ? offendingNode.toString() : "");
    }
}
