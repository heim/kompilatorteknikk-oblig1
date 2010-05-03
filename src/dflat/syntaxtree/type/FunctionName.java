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
        List<Type> signature = new ArrayList<Type>();
        for (Param param : paramList) {
            signature.add(param.getType());
        }
        return new FunctionName(name, signature);
    }

    @Override
    public String toString() {
        String ret =  "Function name: " + name.toString() +"\n";
        for (Type type : signature) {
            if(type == null) {
                System.out.println("WARNING! Function name: " + name.toString() + " has null type as signature.");

            }else{
                ret += type.getName() + "\n";
            }
        }
        return ret;

    }
}
