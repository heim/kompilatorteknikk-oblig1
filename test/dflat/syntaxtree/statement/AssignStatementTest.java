package dflat.syntaxtree.statement;

import bytecode.CodeProcedure;
import dflat.compiler.SymbolTable;
import dflat.exceptions.IncompatibleReturnTypeException;
import dflat.syntaxtree.Node;
import dflat.syntaxtree.decl.Decl;
import dflat.syntaxtree.decl.FuncDecl;
import dflat.syntaxtree.decl.VarDecl;
import dflat.syntaxtree.expression.Expression;
import dflat.syntaxtree.expression.VariableExpression;
import dflat.syntaxtree.param.ActualParam;
import dflat.syntaxtree.param.FormalParam;
import dflat.syntaxtree.type.*;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AssignStatementTest {


    @Test(expected = IncompatibleReturnTypeException.class)
    public void testSemanticsCheckFailsWhenAssigningAFunctionWithReturnTypeIsVoid() throws Exception {
        FuncDecl func = new FuncDecl(
                new Name("test"),
                new ArrayList<FormalParam>(),
                new VoidType(),
                new ArrayList<Decl>(),
                makStatmentListWithReturnStatement(new VoidType())
        );

        func.checkSemantics();
        VarDecl vd = new VarDecl(new IntegerType(), new Name("var1"));
        vd.checkSemantics();

        VariableExpression var = new VariableExpression(new Name("var1"));
        var.checkSemantics();
        CallStatement callStatement = new CallStatement(new Name("test"), new ArrayList<ActualParam>());
        callStatement.checkSemantics();

        AssignStatement as = new AssignStatement(var, callStatement);

        as.checkSemantics();
    }

    private List<Statement> makStatmentListWithReturnStatement(final Type type) {
        List<Statement> sl =  new ArrayList<Statement>();
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
                return null;
            }

            @Override
            public void checkSemantics() {
            }
        }));
        return sl;
    }


    @Test(expected = IncompatibleReturnTypeException.class)
    public void testFailsWhenExpressionsHaveUnequalTypes() throws Exception {
        //declare both vars

        VarDecl vd1 = new VarDecl(new IntegerType(), new Name("var1"));
        VarDecl vd2 = new VarDecl(new StringType(), new Name("var2"));

        vd1.checkSemantics();
        vd2.checkSemantics();

        VariableExpression var1 = new VariableExpression(new Name("var1"));
        VariableExpression var2= new VariableExpression(new Name("var2"));

        AssignStatement as =  new AssignStatement(var1, var2);
        as.checkSemantics();

    }

    @Test
    public void testIntegerCanBeCastToFloat() throws Exception {
        VarDecl vd1 = new VarDecl(new FloatType(), new Name("var1"));
        VarDecl vd2 = new VarDecl(new IntegerType(), new Name("var2"));

        vd1.checkSemantics();
        vd2.checkSemantics();

        VariableExpression var1 = new VariableExpression(new Name("var1"));
        VariableExpression var2= new VariableExpression(new Name("var2"));

        AssignStatement as =  new AssignStatement(var1, var2);
        as.checkSemantics();
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
    }}
