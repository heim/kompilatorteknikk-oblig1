package dflat.syntaxtree.type;

import dflat.syntaxtree.param.FormalParam;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class FunctionNameTest {

    @Test
    public void testEqualNameEmptySignatureReturnsTrue() {
        FunctionName one = new FunctionName(new Name("foo"), new LinkedList<FormalParam>());
        FunctionName two = new FunctionName(new Name("foo"), new LinkedList<FormalParam>());
        assertTrue(one.equals(two));
    }

    @Test
    public void testEqualNameAndEqualSignatureReturnsTrue() throws Exception {

        List<FormalParam> sig1 = new ArrayList<FormalParam>();
        List<FormalParam> sig2 = new ArrayList<FormalParam>();

        sig1.add(new FormalParam(false, new IntegerType(), new Name("fooz")));
        sig1.add(new FormalParam(false, new StringType(), new Name("foozZ")));

        sig2.add(new FormalParam(false, new IntegerType(), new Name("fooz")));
        sig2.add(new FormalParam(false, new StringType(), new Name("foozZ")));

        FunctionName one = new FunctionName(new Name("foo"), sig1);
        FunctionName two = new FunctionName(new Name("foo"), sig2);
        assertTrue(one.equals(two));
    }

    @Test
    public void testDifferentNameAndEqualSignatureReturnsFalse() throws Exception {

       List<FormalParam> sig1 = new ArrayList<FormalParam>();
        List<FormalParam> sig2 = new ArrayList<FormalParam>();

        sig1.add(new FormalParam(false, new IntegerType(), new Name("fooz")));
        sig1.add(new FormalParam(false, new StringType(), new Name("foozZ")));

        sig2.add(new FormalParam(false, new IntegerType(), new Name("fooz")));
        sig2.add(new FormalParam(false, new StringType(), new Name("foozZ")));

        FunctionName one = new FunctionName(new Name("fooZ"), sig1);
        FunctionName two = new FunctionName(new Name("foo"), sig2);
        assertFalse(one.equals(two));
    }



    @Test
    public void testEqualNameAndDifferentSignatureReturnsFalse() throws Exception {

  List<FormalParam> sig1 = new ArrayList<FormalParam>();
        List<FormalParam> sig2 = new ArrayList<FormalParam>();

        sig1.add(new FormalParam(false, new IntegerType(), new Name("fooz")));
        sig1.add(new FormalParam(false, new StringType(), new Name("foozZ")));

        sig2.add(new FormalParam(false, new IntegerType(), new Name("fooz")));
        sig2.add(new FormalParam(false, new IntegerType(), new Name("foozZ")));

        FunctionName one = new FunctionName(new Name("foo"), sig1);
        FunctionName two = new FunctionName(new Name("foo"), sig2);
        assertFalse(one.equals(two));
    }
}
