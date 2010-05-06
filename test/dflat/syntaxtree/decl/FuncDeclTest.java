package dflat.syntaxtree.decl;

import bytecode.CodeProcedure;
import dflat.compiler.SymbolTable;
import dflat.exceptions.FunctionMustHaveReturnStatementException;
import dflat.exceptions.IncompatibleReturnTypeException;
import dflat.exceptions.MainFunctionDeclarationException;
import dflat.exceptions.SemanticsException;
import dflat.syntaxtree.Node;
import dflat.syntaxtree.expression.Expression;
import dflat.syntaxtree.expression.literal.IntLiteral;
import dflat.syntaxtree.expression.literal.NullLiteral;
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
        FuncDecl underTest = makeFunctionWithReturnTypeAndReturnStatement(new VoidType());

        underTest.checkSemantics();
    }




    @Test(expected = SemanticsException.class)
    public void testFailsWhenReturnTypeIsUnDeclared() {
        FuncDecl underTest = makeFunctionWithReturnTypeAndReturnStatement(new ClassType(new Name("Test")));

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

        List<FormalParam> formalParamList;
        Type p1Type = new IntegerType();
        Type p2Type = new StringType();
        formalParamList = makeFormalParamList(p1Type, p2Type);

        Name functionName = new Name("foo1");
        FuncDecl fd = new FuncDecl(functionName, formalParamList, new VoidType(), new ArrayList<Decl>(), makeReturnStatementWithType(new VoidType()));

        fd.checkSemantics();

        List<FormalParam> tl = new ArrayList<FormalParam>();
        tl.add(new FormalParam(false, p1Type, new Name("foo")));
        tl.add(new FormalParam(false, p2Type, new Name("foo2")));

        FunctionName fn = new FunctionName(functionName, tl);

        assertEquals(new VoidType(), Node.getSymbolTable().lookup(fn));
    }

    private List<FormalParam> makeFormalParamList(Type param1Type, Type param2Type) {
        List<FormalParam> retList = new ArrayList<FormalParam>();
        Name p1Name = new Name("foo");
        Name p2Name = new Name("foo2");
        Type p1Type = param1Type;
        Type p2Type = param2Type;

        FormalParam p1 = new FormalParam(false, p1Type, p1Name);
        FormalParam p2 = new FormalParam(false, p2Type, p2Name);

        retList.add(p1);
        retList.add(p2);
        return retList;
    }


    @Test(expected = FunctionMustHaveReturnStatementException.class)
    public void testShouldFailIfNoReturnStatement() throws Exception {
        FuncDecl underTest = makeFunctionWithReturnType(new IntegerType());
        underTest.checkSemantics();
    }

    @Test
    public void testVoidFunctionsDoesNotNeedReturnStatements() throws Exception {
        FuncDecl underTest = makeFunctionWithReturnType(new VoidType());
        underTest.checkSemantics();
    }

    @Test
    public void testCanReturnNullLiteralInsteadOfValue() throws Exception {
        FuncDecl underTest = makeFunctionWithReturnTypeAndNullReturnStatement();
        underTest.checkSemantics();
    }


    @Test(expected = MainFunctionDeclarationException.class)
    public void testMainFunctonMustNotBeDeclaredWithParameters() throws Exception {
        FuncDecl underTest = new FuncDecl(name("Main"), makeFormalParamList(new IntegerType(), new StringType()), new VoidType(), new ArrayList<Decl>(), new ArrayList<Statement>());
        underTest.checkSemantics();
    }

    @Test(expected = MainFunctionDeclarationException.class)
    public void testMainFunctionMustNotHaveReturnTypeOtherThanVoidType() throws Exception {
        FuncDecl underTest = new FuncDecl(name("Main"), new ArrayList<FormalParam>(), new StringType(), new ArrayList<Decl>(), new ArrayList<Statement>());
        underTest.checkSemantics();
    }

    @Test
    public void testCorrectDeclarationOfMainFunction() throws Exception {
        FuncDecl underTest = new FuncDecl(name("Main"), new ArrayList<FormalParam>(), new VoidType(), new ArrayList<Decl>(), new ArrayList<Statement>());
        underTest.checkSemantics();

    }

    private Name name(String name) {
        return new Name(name);
    }

    private FuncDecl makeFunctionWithReturnTypeAndNullReturnStatement() {
        List<Statement> sl = new ArrayList<Statement>();
        sl.add(new ReturnStatement(new Expression() {
            @Override
            public Type getType() {
                return new NullLiteral().getType();
            }

            @Override
            public void generateCode(CodeProcedure codeProcedure) {
            }

            @Override
            public String printAst(int indent) {
                return "";
            }

            @Override
            public void checkSemantics() {
            }
        }));

        return new FuncDecl(
                new Name("function"),
                new ArrayList<FormalParam>(),
                new IntegerType(),
                new ArrayList<Decl>(),
                sl
        );
    }

    private FuncDecl makeFunctionWithReturnType(Type type) {
        return new FuncDecl(
                        new Name("function"),
                        new ArrayList<FormalParam>(),
                        type,
                        new ArrayList<Decl>(),
                        new ArrayList<Statement>());

    }

    private FuncDecl makeFunctionWithReturnTypeAndReturnStatement(Type returnType) {
        return new FuncDecl(
                new Name("function"),
                new ArrayList<FormalParam>(),
                returnType,
                new ArrayList<Decl>(),
                makeReturnStatementWithType(returnType)
        );
    }

    private List<Statement> makeReturnStatementWithType(final Type type) {
        List<Statement> sl = new ArrayList<Statement>();
        sl.add(new ReturnStatement(new Expression() {
            @Override
            public Type getType() {
                return type;
            }
            @Override
            public void generateCode(CodeProcedure codeProcedure) {
            }
            @Override
            public String printAst(int indent) {
                return "";
            }
            @Override
            public void checkSemantics() {
            }
        }));
        return sl;
    }


    @After
    public void tearDown() {
        Node n = new Node() {
            @Override
            public String printAst(int indent) {
                return "";
            }

            @Override
            public void checkSemantics() {
                this.symbolTable = new SymbolTable();
            }
        };
        n.checkSemantics();
    }


}
