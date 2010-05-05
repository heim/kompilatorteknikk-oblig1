package dflat.syntaxtree.decl;

import bytecode.CodeFile;
import bytecode.CodeProcedure;
import bytecode.instructions.Instruction;
import bytecode.type.CodeType;
import bytecode.type.RefType;
import dflat.exceptions.FunctionMustHaveReturnStatementException;
import dflat.exceptions.IncompatibleReturnTypeException;
import dflat.exceptions.MainFunctionDeclarationException;
import dflat.exceptions.SemanticsException;
import dflat.syntaxtree.param.FormalParam;
import dflat.syntaxtree.statement.ReturnStatement;
import dflat.syntaxtree.statement.Statement;
import dflat.syntaxtree.type.*;

import java.util.List;

public class FuncDecl extends Decl {

    private FunctionName name;
    private Type returnType;
    private List<Decl> declList;
    private List<FormalParam> formalParamList;
    private List<Statement> statementList;

    public FuncDecl(Name name, List<FormalParam> formalParamList, Type returnType, List<Decl> declList, List<Statement> statementList) {
        this.name = FunctionName.functionNameFactory(name, formalParamList);
        this.returnType = returnType != null ? returnType : new VoidType();
        this.declList = declList;
        this.formalParamList = formalParamList;
        this.statementList = statementList;
    }


    public String printAst(int indent) {
        String returnTypeDesc = "(TYPE void)";

        if(returnType != null) {
            returnTypeDesc = returnType.printAst(0);
        }

        String retVal = indentTabs(indent) + "(FUNC_DECL " + returnTypeDesc +" (NAME " + name + ")\n";
        for(FormalParam p : formalParamList) {
            retVal += p.printAst(indent + 1) + "\n";
        }

        for(Decl d : declList) {
            retVal += d.printAst(indent + 1) + "\n";
        }

        for(Statement s : statementList) {
            retVal += s.printAst(indent + 1) + "\n";
        }

        return retVal + indentTabs(indent) +  ")";
    }

    @Override
    public Type getType() {
        return returnType;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Name getName() {
        return name;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void checkSemantics() {

        ifIsMainFunctionCheckParametersAndReturnType();


        checkReturnTypeSemantics();
        symbolTable.insert(getName(), getType());
        symbolTable.enter_scope();
        checkParameterSemantics();
        checkSemanticsForDeclarations();
        checkSemanticsForStatements();



        symbolTable.exit_scope();
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        codeFile.addProcedure(getName().toString());

        CodeType byteCodeReturnType;


        if(returnType instanceof ClassType) {
            byteCodeReturnType = new RefType(codeFile.structNumber(returnType.getName().toString()));

        }   else {
            byteCodeReturnType = returnType.getByteCodeType();
        }


        CodeProcedure proc = new CodeProcedure(getName().toString(), byteCodeReturnType, codeFile);




        for (FormalParam formalParam : formalParamList) {
            CodeType paramByteCodeType;

            if(formalParam.getType() instanceof ClassType) {
                paramByteCodeType = new RefType(codeFile.structNumber(formalParam.getType().getName().toString()));

            }   else {
                paramByteCodeType = formalParam.getType().getByteCodeType();
            }


            proc.addParameter(formalParam.getName().toString(), paramByteCodeType);
        }

        for (Decl decl : declList) {
            if(decl instanceof VarDecl) {
                if(decl.getType() instanceof ClassType) {
                    proc.addLocalVariable(decl.getName().toString(), new RefType(codeFile.structNumber(decl.getType().getName().toString())));
                }else {
                    proc.addLocalVariable(decl.getName().toString(), decl.getType().getByteCodeType());
                }
            }
            else if(decl instanceof ClassDecl) {
                decl.generateCode(codeFile);
            }
            else if(decl instanceof FuncDecl) {
                System.out.println("decl.getName() = " + decl.getName());
                decl.generateCode(codeFile);
            } else {
                throw new RuntimeException("ERROR. unknown declaration.");
            }


        }


        for (Statement statement : statementList) {
            statement.generateCode(proc);
        }
        codeFile.updateProcedure(proc);
    }

    private void ifIsMainFunctionCheckParametersAndReturnType() {
        if(name.getName().equals("Main")) {
            if(hasParameters() || hasOtherReturnTypeThanVoid()) {
                throw new MainFunctionDeclarationException(this);
            }
        }
    }

    private boolean hasOtherReturnTypeThanVoid() {
        return !returnType.equals(new VoidType());
    }

    private boolean hasParameters() {
        return !formalParamList.isEmpty();
    }

    private void checkReturnTypeSemantics() {
        returnType.checkSemantics();
    }

    private void checkParameterSemantics() throws SemanticsException {

        for (FormalParam formalParam : formalParamList) {
            formalParam.checkSemantics();
        }
    }

    private void checkSemanticsForStatements() throws SemanticsException {
        int returnStatementsCount = 0;
        for (Statement statement : statementList) {
            statement.checkSemantics();

            if(statement instanceof ReturnStatement){
                checkThatFunctionHasCompatibleReturnValueFor(statement);
                returnStatementsCount++;
            }

        }

        if(returnStatementsCount < 1 && !this.returnType.equals(new VoidType()))
            throw new FunctionMustHaveReturnStatementException(this);
    }

    private void checkThatFunctionHasCompatibleReturnValueFor(Statement statement) {
        ReturnStatement rs = (ReturnStatement) statement;
        if(!rs.getType().canBeCastTo(this.returnType))
            throw new IncompatibleReturnTypeException(rs);

    }

    private void checkSemanticsForDeclarations() {
        for(Decl d : declList) {
            d.checkSemantics();
        }
    }
}
