package dflat.syntaxtree.param;

import bytecode.CodeProcedure;


public abstract class ActualParam extends Param {


    public abstract void generateCode(CodeProcedure procedure);
}
