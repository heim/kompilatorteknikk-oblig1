package dflat.syntaxtree.type;

import dflat.syntaxtree.param.FormalParam;
import dflat.syntaxtree.param.Param;

import java.util.ArrayList;
import java.util.List;

public class FunctionName extends Name{
    private List<Type> signature;

    public FunctionName(Name functionName, List<Type> signature) {
        super(functionName.toString());
        this.signature = signature;
    }


    @Override
    public boolean equals(Object other) {

        return other instanceof FunctionName && super.equals(other) && signatureEquals((FunctionName)other);
    }

    private boolean signatureEquals(FunctionName other) {
        return signature.equals(other.signature);
    }

    public static FunctionName functionNameFactory(Name name, List<? extends Param> paramList) {
        List<Type> signature = new ArrayList<Type>();
        for (Param param : paramList) {
            signature.add(param.getType());
        }
        return new FunctionName(name, signature);
    }
}
