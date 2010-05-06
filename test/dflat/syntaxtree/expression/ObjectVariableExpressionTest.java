package dflat.syntaxtree.expression;

import bytecode.CodeProcedure;
import dflat.compiler.SymbolTable;
import dflat.exceptions.IncompatibleReturnTypeException;
import dflat.exceptions.SymbolNotDeclaredException;
import dflat.syntaxtree.Node;
import dflat.syntaxtree.decl.ClassDecl;
import dflat.syntaxtree.decl.VarDecl;
import dflat.syntaxtree.type.ClassType;
import dflat.syntaxtree.type.IntegerType;
import dflat.syntaxtree.type.Name;
import dflat.syntaxtree.type.Type;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class ObjectVariableExpressionTest {

    @Test(expected = IncompatibleReturnTypeException.class)
    public void testSemanticCheckFailsWhenExpressionDoesNotReturnClassType() {
        ObjectVariableExpression underTest = new ObjectVariableExpression(expressionMock(new IntegerType()), name("foo"));
        underTest.checkSemantics();
    }


    @Test(expected = SymbolNotDeclaredException.class)
    public void testSemanticCheckFailsIfClassDoesNotHaveVariableDeclaration() throws Exception {
        ClassType ct = makeClass("myClass", "myVar");
        ObjectVariableExpression underTest = new ObjectVariableExpression(expressionMock(ct), name("notMyVar"));
        underTest.checkSemantics();
    }


    @Test
    public void testSemCheckPassesIfClassHasVariableDeclaration() throws Exception {
        ClassType ct = makeClass("myClass2", "myVar");
        ObjectVariableExpression underTest = new ObjectVariableExpression(expressionMock(ct), name("myVar"));
        underTest.checkSemantics();

    }



    @Test
    public void testReturnsCorrectType() throws Exception {
        ClassType ct = makeClass("myClass2", "myVar",  new IntegerType());
        ObjectVariableExpression underTest = new ObjectVariableExpression(expressionMock(ct) , name("myVar"));
        underTest.checkSemantics();
        assertEquals(new IntegerType(), underTest.getType());
    }

    private ClassType makeClass(String name, String var) {
        return makeClass(name, var, new IntegerType());
    }

    private ClassType makeClass(String name, String var, Type type) {
        List<VarDecl> vdl = new ArrayList<VarDecl>();
        VarDecl vd = new VarDecl(type, name(var));
        vd.checkSemantics();
        vdl.add(vd);
        ClassDecl cd = new ClassDecl(name(name), vdl);
        cd.checkSemantics();
        return (ClassType)cd.getType();
    }

    private Expression expressionMock(final Type type) {
        return new Expression() {
            @Override
            public Type getType() {
                return type;
            }

            @Override
            public void generateCode(CodeProcedure codeProcedure) {
            }

            @Override
            public String printAst(int indent) {
                return null;
            }

            @Override
            public void checkSemantics() {
            }
        };
    }

    private Name name(String s) {
        return new Name(s);
    }


    @After //TODO: refactor og bruk spring injection på symbolTable
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
