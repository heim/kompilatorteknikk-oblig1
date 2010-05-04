package dflat.syntaxtree.decl;

import bytecode.CodeFile;
import bytecode.CodeStruct;
import bytecode.type.RefType;
import dflat.syntaxtree.type.ClassType;
import dflat.syntaxtree.type.Name;
import dflat.syntaxtree.type.Type;

import java.util.ArrayList;
import java.util.List;

public class ClassDecl extends Decl {

    private Name name;
    private List<VarDecl> varDecl;
    private ClassType classType;

    public ClassDecl(Name name, List<VarDecl> varDecl){
        //TODO: Sjekk om en tom klasse gir tom liste eller null-liste
        this.name = name;
        this.varDecl = varDecl;
        this.classType = new ClassType(name, varDecl);
    }

    @Override
    public String printAst(int indent) {
        String retval = indentTabs(indent) + "(CLASS "+ name.printAst(0) +"\n";
        for(VarDecl d :varDecl) {
            retval +=  d.printAst(indent + 1) + "\n";
        }
        return retval + indentTabs(indent) +  ")";
    }

    @Override
    public Type getType() {
        return classType;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public void checkSemantics() {
        buildSymbolTable();
    }

    @Override
    public void generateCode(CodeFile codeFile) {
        codeFile.addStruct(name.toString());

        CodeStruct struct = new CodeStruct(name.toString());
        for (VarDecl decl : varDecl) {
            String declName =   decl.getName().toString();
            if(isClassType(decl)) {
                struct.addVariable(declName, getRefTypeForName(codeFile,decl));
            } else {
                struct.addVariable(declName, decl.getType().getByteCodeType());
            }
        }

        codeFile.updateStruct(struct);

        
    }

    private RefType getRefTypeForName(CodeFile codeFile, VarDecl decl) {
        ClassType classType = (ClassType)decl.getType();
        String className = classType.getName().toString();
        int classRef = codeFile.structNumber(className);
        return new RefType(classRef);
    }

    private boolean isClassType(VarDecl decl) {
        return decl.getType() instanceof ClassType;
    }


    private void buildSymbolTable() {
        symbolTable.insert(getName(), getType());

    }
}
