package dflat.syntaxtree.statement;

import dflat.exceptions.SymbolNotDeclaredException;
import dflat.syntaxtree.Node;
import dflat.syntaxtree.param.ActualParam;
import dflat.syntaxtree.type.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CallStatementTest  {

    @Test(expected = SymbolNotDeclaredException.class)
    public void testThatSyntaxCheckFailsWhenMethodDoesntExist() {
        CallStatement underTest = new CallStatement(new Name("foo"), new ArrayList<ActualParam>());
        underTest.checkSemantics();
    }


    @Test(expected = SymbolNotDeclaredException.class)
    public void testThatSyntaxCheckFaileWhenSignatureIsDifferent() throws Exception {
        List<ActualParam> paramList = new ArrayList<ActualParam>();
        ActualParam param = paramMock(new IntegerType());
        paramList.add(param);
        CallStatement underTest = new CallStatement(new Name("foo"), paramList);

        Node.getSymbolTable().insert(createFunctionName(new StringType()), new VoidType());

        underTest.checkSemantics();


    }



    private FunctionName createFunctionName(Type signature1) {
        List<Type> signatureList = new ArrayList<Type>();
        signatureList.add(signature1);

        return new FunctionName(new Name("foo"), signatureList);
    }


    private ActualParam paramMock(final Type type) {
        return new ActualParam() {
            @Override
            public String printAst(int indent) {
                return null;
            }

            @Override
            public void checkSemantics() {
            }


            public Type getType() {
                return type;
            }
        };
    }
}
