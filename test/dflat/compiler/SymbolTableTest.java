package dflat.compiler;

import dflat.exceptions.SymbolAlreadyDeclaredException;
import dflat.syntaxtree.type.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by IntelliJ IDEA.
 * User: andreas
 * Date: Apr 26, 2010
 * Time: 9:44:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class SymbolTableTest {
    private SymbolTable underTest;


    @Before
    public void setUp() {
        underTest = new SymbolTable();
    }


    @Test
    public void testCanInsertAndLookupWithinDefaultScope() {
        /*
            Example:

            {
                var bool Test
            }
         */


        underTest.insert(new Name("Test"), new BooleanType());


        assertEquals(new BooleanType(), underTest.lookup(new Name("Test")));
    }


    @Test
    public void testCanInsertAndLookupInNestedScopes() {
        /*
            Example:

            {
                var int outside;

                {
                    var bool inside;

                    <do lookup for both variables>

                }

            }

         */

        underTest.insert(new Name("outside"), new IntegerType());

        underTest.enter_scope();

        underTest.insert(new Name("inside"), new BooleanType());

        assertEquals(new BooleanType(), underTest.lookup(new Name("inside")));
        assertEquals(new IntegerType(), underTest.lookup(new Name("outside")));
    }


    @Test
    public void testWillNotReturnDeclarationOutsideOfCurrentScope() throws Exception {
        Name outside = new Name("outside");
        underTest.insert(outside, new ClassType(outside));

        underTest.enter_scope();
        underTest.insert(new Name("inside"), new VoidType());
        underTest.exit_scope();


        assertNull(underTest.lookup(new Name("inside")));



    }


    @Test
    public void testLocalScopeShadowsOuterScope() throws Exception {
        underTest.insert(new Name("outside"), new IntegerType());
        underTest.enter_scope();

        underTest.insert(new Name("outside"), new BooleanType());

        assertEquals(new BooleanType(), underTest.lookup(new Name("outside")));

    }


    @Test
    public void testDoubleLocalScopeShadowsOuterScopes() throws Exception {

        underTest.insert(new Name("outside"), new IntegerType());
        underTest.enter_scope();

        underTest.insert(new Name("outside"), new BooleanType());
        underTest.enter_scope();
        underTest.insert(new Name("outside"), new StringType());

        assertEquals(new StringType(), underTest.lookup(new Name("outside")));


    }

    @Test
    public void testDoubleLocalScopeWithExitFromInnerScope() throws Exception {
        underTest.insert(new Name("outside"), new IntegerType());
        underTest.enter_scope();

        underTest.insert(new Name("outside"), new BooleanType());
        underTest.enter_scope();
        underTest.insert(new Name("outside"), new StringType());
        underTest.exit_scope();

        assertEquals(new BooleanType(), underTest.lookup(new Name("outside")));


    }


    @Test(expected = SymbolAlreadyDeclaredException.class)
    public void testDeclarationOfMultipleNamesInSameScopeFails() throws Exception {
        underTest.insert(new Name("outside"), new IntegerType());
        underTest.insert(new Name("outside"), new BooleanType());

    }


    @Test
    public void testHasStringByDefault() throws Exception {
        assertEquals(new StringType(), underTest.lookup(new Name("string")));
    }

    @Test
    public void testHasIntegerByDefault() throws Exception {
        assertEquals(new IntegerType(), underTest.lookup(new Name("int")));
    }

    @Test
    public void testHasBooleanByDefault() throws Exception {
        assertEquals(new BooleanType(), underTest.lookup(new Name("bool")));
    }

    @Test
    public void testHasFloatByDefault() throws Exception {
        assertEquals(new FloatType(), underTest.lookup(new Name("float")));
    }

    @Test
    public void testHasVoidByDefault() throws Exception {
        assertEquals(new VoidType(), underTest.lookup(new Name("void")));
    }
}
