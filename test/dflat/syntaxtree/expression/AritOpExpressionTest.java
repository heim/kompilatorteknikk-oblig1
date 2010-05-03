package dflat.syntaxtree.expression;

import dflat.exceptions.IncompatibleTypeException;
import dflat.syntaxtree.expression.op.ExponentOp;
import dflat.syntaxtree.expression.op.MinusOp;
import dflat.syntaxtree.expression.op.PlusOp;
import dflat.syntaxtree.type.FloatType;
import dflat.syntaxtree.type.IntegerType;
import dflat.syntaxtree.type.StringType;
import dflat.syntaxtree.type.Type;
import org.junit.Test;

import static org.junit.Assert.*;

public class AritOpExpressionTest {


    @Test(expected = IncompatibleTypeException.class)
    public void testShoudFailIfEitherExpression1IsOtherThanIntOrFloat() {
        AritOpExpression underTest = new AritOpExpression(mockExpression(new StringType()), new MinusOp(), mockExpression(new IntegerType()));
        underTest.checkSemantics();
    }

    @Test
    public void testEvaluatesAsIntegerIfBothExpsIsIntegers() throws Exception {
        AritOpExpression underTest = new AritOpExpression(mockExpression(new IntegerType()), new PlusOp(), mockExpression(new IntegerType()));
        underTest.checkSemantics();

        assertTrue(underTest.getType() instanceof IntegerType);
    }


    @Test
    public void testEvaluatesAsFloatIfOneOpIsFloat() throws Exception {
        AritOpExpression underTest = new AritOpExpression(mockExpression(new FloatType()), new PlusOp(), mockExpression(new IntegerType()));
        underTest.checkSemantics();

        assertTrue(underTest.getType() instanceof FloatType);
    }

    @Test
    public void testExponentOpReturnsFloat() throws Exception {
        AritOpExpression underTest = new AritOpExpression(mockExpression(new IntegerType()), new ExponentOp(), mockExpression(new IntegerType()));
        underTest.checkSemantics();

        assertTrue(underTest.getType() instanceof FloatType);

    }

    private Expression mockExpression(final Type type) {
        return new Expression() {
            @Override
            public Type getType() {
                return type;
            }

            @Override
            public String printAst(int indent) {
                return "";
            }

            @Override
            public void checkSemantics() {
            }
        };
    }


}
