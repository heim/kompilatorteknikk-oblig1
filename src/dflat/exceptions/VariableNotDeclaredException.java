package dflat.exceptions;

import dflat.syntaxtree.Node;
import dflat.syntaxtree.expression.VariableExpression;

public class VariableNotDeclaredException extends SemanticsException {

    public VariableNotDeclaredException(Node offendingNode) {
        super(offendingNode);
    }
}
