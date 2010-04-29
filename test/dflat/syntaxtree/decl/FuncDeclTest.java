package dflat.syntaxtree.decl;

import dflat.compiler.SymbolTable;
import dflat.exceptions.IncompatibleReturnTypeException;
import dflat.exceptions.SemanticsException;
import dflat.syntaxtree.Node;
import dflat.syntaxtree.expression.literal.IntLiteral;
import dflat.syntaxtree.param.Param;
import dflat.syntaxtree.statement.ReturnStatement;
import dflat.syntaxtree.statement.Statement;
import dflat.syntaxtree.type.*;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FuncDeclTest {



    @Test
    public void testPassesCheckWhenReturnValueIsVoid() {
        FuncDecl underTest = makeFunctionWithReturnType(new VoidType());

        underTest.checkSemantics();
    }




    @Test(expected = SemanticsException.class)
    public void testFailsWhenReturnTypeIsUnDeclared() {
        FuncDecl underTest = makeFunctionWithReturnType(new ClassType(new Name("Test")));

        underTest.checkSemantics();

    }


    @Test(expected = IncompatibleReturnTypeException.class)
    public void testCannotReturnOtherThanDeclaredReturnType() throws Exception {
        ReturnStatement rs = new ReturnStatement(new IntLiteral("42"));
        List<Statement> sl = new ArrayList<Statement>();
        sl.add(rs);

        FuncDecl f = new FuncDecl(
                new Name("Function"),
                new ArrayList<Param>(),
                new StringType(),
                new ArrayList<Decl>(),
         sl);

        f.checkSemantics();

    }

    private FuncDecl makeFunctionWithReturnType(Type returnType) {
        return new FuncDecl(
                new Name("function"),
                new ArrayList<Param>(),
                returnType,
                new ArrayList<Decl>(),
                new ArrayList<Statement>()
        );
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
