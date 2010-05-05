package dflat.syntaxtree.type;

import dflat.syntaxtree.param.FormalParam;
import dflat.syntaxtree.param.Param;

import java.util.ArrayList;
import java.util.List;

public class FunctionName extends Name{
    private List<? extends Param> signature;

    public FunctionName(Name functionName, List<? extends Param> signature) {
        super(functionName.toString());
        this.signature = signature;
    }


    @Override
    public boolean equals(Object other) {
        return other instanceof FunctionName && this.getName().toString().equals(((FunctionName)other).getName().toString()) && signatureEquals((FunctionName)other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private boolean signatureEquals(FunctionName other) {
        return signature.equals(other.signature);
    }

    public static FunctionName functionNameFactory(Name name, List<? extends Param> paramList) {
        return new FunctionName(name, paramList);
    }

    @Override
    public String toString() {
        return name.toString();
    }

    public String toErrorString() {
        String ret =  "Function name: " + name.toString() +"(";
        for (Param param : signature) {
            if(param == null) {
                System.out.println("WARNING! Function name: " + name.toString() + " has null type as signature.");

            }else{
                ret += param.toString() + ", ";
            }
        }
        return ")" + ret;

    }
}
