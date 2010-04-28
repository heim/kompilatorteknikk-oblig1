package dflat.syntaxtree;

import bytecode.CodeFile;
import dflat.exceptions.SemanticsException;
import dflat.syntaxtree.decl.Decl;

import java.util.List;

public class Program  {

    private List<Decl> declList;

    public Program(List<Decl> declList) {
        this.declList = declList;
    }

    public String printAst(int indent){
        String retval = "(PROGRAM\n";
        for(Decl d : declList) {
            System.out.println("P");
            retval += d.printAst(indent + 1) +  "\n\n";
        }
        retval += indentTabs(indent) + ")\n";
        return retval;
    }


    private String indentTabs(int indent) {
        String indentTabs = "";
        for(int i = 0; i < indent; i++) {
            indentTabs += "\t";
        }
        return indentTabs;
    }


    public void generateCode(CodeFile codeFile) {

    }

    public void checkSemantics() throws SemanticsException {

        for(Decl d : declList) {
            d.checkSemantics();
        }

    }
}
