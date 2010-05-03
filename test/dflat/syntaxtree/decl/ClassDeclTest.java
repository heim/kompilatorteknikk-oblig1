package dflat.syntaxtree.decl;

import dflat.compiler.SymbolTable;
import dflat.syntaxtree.Node;
import dflat.syntaxtree.type.ClassType;
import dflat.syntaxtree.type.IntegerType;
import dflat.syntaxtree.type.Name;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClassDeclTest {

    @Test
    public void testClassNameGetsAddedToSymbolTable() throws Exception {
        ClassDecl underTest = new ClassDecl(new Name("Test1"), new ArrayList<VarDecl>());
        underTest.checkSemantics();
        assertNotNull(Node.getSymbolTable().lookup(new Name("Test1")));


    }


    @Test
    public void testMembersGetsAddedToSymbolTable() throws Exception {
        List<VarDecl> memberList = new ArrayList<VarDecl>();

        VarDecl d1 = new VarDecl(new IntegerType(), new Name("Foo"));
        VarDecl d2 = new VarDecl(new IntegerType(), new Name("Bar"));

        memberList.add(d1);
        memberList.add(d2);

        ClassDecl underTest = new ClassDecl(new Name("Test2"), memberList);

        underTest.checkSemantics();
        SymbolTable st = Node.getSymbolTable();

        
        //assertEquals(new IntegerType(), st.lookup(new Name("Test2.Foo")));
        assertNotNull(((ClassType)st.lookup(new Name("Test2"))).hasMember(new Name("Bar")));
    }


    @After
    public void tearDown() {
        Node n = new Node() {
            @Override
            public String printAst(int indent) {
                return null;
            }

            @Override
            public void checkSemantics() {
                this.symbolTable = new SymbolTable();
            }
        };
        n.checkSemantics();
    }
}
