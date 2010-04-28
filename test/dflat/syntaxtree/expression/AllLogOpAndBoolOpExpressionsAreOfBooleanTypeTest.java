package dflat.syntaxtree.expression;

import dflat.syntaxtree.type.BooleanType;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AllLogOpAndBoolOpExpressionsAreOfBooleanTypeTest {
    @Test
    public void testAndOpExpressionIsofBooleanType() throws Exception {
        assertEquals(new BooleanType(), (new AndOpExpression(null, null)).getType());
    }


    @Test
    public void testOrOpExpressionIsOfBooleanType() throws Exception {
        assertEquals(new BooleanType(), (new OrOpExpression(null, null)).getType());
    }

    @Test
    public void testLogOpExpression() throws Exception {
        assertEquals(new BooleanType(), (new RelOpExpression(null, null, null)).getType());
    }

    @Test
    public void testNegatedExpression() throws Exception {
        assertEquals(new BooleanType(), (new NegatedExpression(null)).getType());
    }

}



