package dflat.syntaxtree.decl;

import dflat.compiler.SymbolTable;
import dflat.exceptions.IncompatibleReturnTypeException;
import dflat.exceptions.SemanticsException;
import dflat.syntaxtree.Node;
import dflat.syntaxtree.expression.literal.IntLiteral;
import dflat.syntaxtree.param.FormalParam;
import dflat.syntaxtree.statement.ReturnStatement;
import dflat.syntaxtree.statement.Statement;
import dflat.syntaxtree.type.*;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

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
                new ArrayList<FormalParam>(),
                new StringType(),
                new ArrayList<Decl>(),
         sl);

        f.checkSemantics();

    }

    @Test
    public void testShouldAddParamsToSymbolTable() throws Exception {

        List<FormalParam> formalParamList = new ArrayList<FormalParam>();
        Name p1Name = new Name("param1");
        Name p2Name = new Name("param2");
        Type p1Type = new IntegerType();
        Type p2Type = new StringType();

        FormalParam p1 = new FormalParam(true, p1Type, p1Name);
        FormalParam p2 = new FormalParam(true, p2Type, p2Name);
        
        formalParamList.add(p1);
        formalParamList.add(p2);

        Name functionName = new Name("foo1");
        FuncDecl fd = new FuncDecl(functionName, formalParamList, new VoidType(), new ArrayList<Decl>(), new ArrayList<Statement>());

        fd.checkSemantics();

        List<Type> tl = new ArrayList<Type>();
        tl.add(p1Type);
        tl.add(p2Type);

        FunctionName fn = new FunctionName(functionName, tl);

        assertEquals(new VoidType(), Node.getSymbolTable().lookup(fn));
    }

    private FuncDecl makeFunctionWithReturnType(Type returnType) {
        return new FuncDecl(
                new Name("function"),
                new ArrayList<FormalParam>(),
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
