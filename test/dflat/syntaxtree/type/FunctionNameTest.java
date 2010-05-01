package dflat.syntaxtree.type;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class FunctionNameTest {

    @Test
    public void testEqualNameEmptySignatureReturnsTrue() {
        FunctionName one = new FunctionName(new Name("foo"), new LinkedList<Type>());
        FunctionName two = new FunctionName(new Name("foo"), new LinkedList<Type>());
        assertTrue(one.equals(two));
    }

    @Test
    public void testEqualNameAndEqualSignatureReturnsTrue() throws Exception {

        List<Type> sig1 = new ArrayList<Type>();
        List<Type> sig2 = new ArrayList<Type>();

        sig1.add(new IntegerType());
        sig1.add(new StringType());

        sig2.add(new IntegerType());
        sig2.add(new StringType());



        FunctionName one = new FunctionName(new Name("foo"), sig1);
        FunctionName two = new FunctionName(new Name("foo"), sig2);
        assertTrue(one.equals(two));
    }

        @Test
    public void testDifferentNameAndEqualSignatureReturnsTrue() throws Exception {

        List<Type> sig1 = new ArrayList<Type>();
        List<Type> sig2 = new ArrayList<Type>();

        sig1.add(new IntegerType());
        sig1.add(new StringType());

        sig2.add(new IntegerType());
        sig2.add(new StringType());



        FunctionName one = new FunctionName(new Name("fo0"), sig1);
        FunctionName two = new FunctionName(new Name("foo"), sig2);
        assertFalse(one.equals(two));
    }



    @Test
    public void testEqualNameAndDifferentSignatureReturnsFalse() throws Exception {

        List<Type> sig1 = new ArrayList<Type>();
        List<Type> sig2 = new ArrayList<Type>();

        sig1.add(new StringType());
        sig1.add(new IntegerType());

        sig2.add(new IntegerType());
        sig2.add(new StringType());



        FunctionName one = new FunctionName(new Name("foo"), sig1);
        FunctionName two = new FunctionName(new Name("foo"), sig2);
        assertFalse(one.equals(two));
    }
}
