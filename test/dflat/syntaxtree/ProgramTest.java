package dflat.syntaxtree;

import dflat.syntaxtree.decl.Decl;
import dflat.syntaxtree.type.FunctionName;
import dflat.syntaxtree.type.Name;
import dflat.syntaxtree.type.Type;
import org.junit.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;

public class ProgramTest {

    @Test
    public void testPrintFloatIsCorrectlyAddedToSymbolTable() {
        Program p = new Program(new ArrayList<Decl>());
        p.checkSemantics();

        System.out.println("SymbolTable.toString() " + Node.getSymbolTable().toString());


    }


}
