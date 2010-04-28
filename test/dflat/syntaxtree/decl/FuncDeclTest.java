package dflat.syntaxtree.decl;

import dflat.exceptions.SemanticsException;
import dflat.syntaxtree.param.Param;
import dflat.syntaxtree.statement.Statement;
import dflat.syntaxtree.type.ClassType;
import dflat.syntaxtree.type.Name;
import dflat.syntaxtree.type.Type;
import dflat.syntaxtree.type.VoidType;
import org.junit.Test;

import java.util.ArrayList;

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




    private FuncDecl makeFunctionWithReturnType(Type returnType) {
        return new FuncDecl(
                new Name("function"),
                new ArrayList<Param>(),
                returnType,
                new ArrayList<Decl>(),
                new ArrayList<Statement>()
        );
    }




}
