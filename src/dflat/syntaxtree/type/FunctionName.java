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
        if(signature == null && other.signature == null)
            return true;

        if(signature == null || other.signature == null) {
            return false;
        }

        if(signature.size() != other.signature.size())
            return false;
        
        for (int i = 0; i < signature.size(); i++) {
            Type myType = signature.get(i);
            Type otherType = other.signature.get(i);
            if(myType == otherType)
                return true;
            if(myType == null || otherType == null)
                return false;
            if(myType.equals(otherType))
                return true;


        }


        return true;
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
        System.out.println("ret = " + ret);
        for (Type type : signature) {
            System.out.println("type = " + type);
            ret += type.getName() + "\n";
        }
        return ret;

    }
}
