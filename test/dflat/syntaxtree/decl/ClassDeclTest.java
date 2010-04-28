package dflat.syntaxtree.decl;

import dflat.compiler.SymbolTable;
import dflat.syntaxtree.Node;
import dflat.syntaxtree.type.IntegerType;
import dflat.syntaxtree.type.Name;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClassDeclTest {

    @Test
    public void testClassNameGetsAddedToSymbolTable() throws Exception {
        ClassDecl underTest = new ClassDecl(new Name("Test"), new ArrayList<VarDecl>());
        underTest.checkSemantics();
        assertNotNull(Node.getSymbolTable().lookup(new Name("Test")));
    }


    @Test
    public void testMembersGetsAddedToSymbolTable() throws Exception {
        List<VarDecl> memberList = new ArrayList<VarDecl>();

        VarDecl d1 = new VarDecl(new IntegerType(), new Name("Foo"));
        VarDecl d2 = new VarDecl(new IntegerType(), new Name("Bar"));

        memberList.add(d1);
        memberList.add(d2);

        ClassDecl underTest = new ClassDecl(new Name("Test"), memberList);

        underTest.checkSemantics();
        SymbolTable st = Node.getSymbolTable();

        
        assertEquals(new IntegerType(), st.lookup(new Name("Test.Foo")));
        assertNotNull(st.lookup(new Name("Test.Bar")));
    }
}
