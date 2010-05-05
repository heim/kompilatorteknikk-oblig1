package dflat.syntaxtree;

import bytecode.CodeFile;
import bytecode.CodeProcedure;
import dflat.exceptions.MainFunctionDeclarationException;
import dflat.exceptions.SemanticsException;
import dflat.syntaxtree.decl.ClassDecl;
import dflat.syntaxtree.decl.Decl;
import dflat.syntaxtree.decl.FuncDecl;
import dflat.syntaxtree.expression.Expression;
import dflat.syntaxtree.param.FormalParam;
import dflat.syntaxtree.statement.ReturnStatement;
import dflat.syntaxtree.statement.Statement;
import dflat.syntaxtree.type.*;

import java.util.ArrayList;
import java.util.List;

public class Program  {

    private List<Decl> declList;

    public Program(List<Decl> declList) {
        this.declList = declList;



    }

    private void addLibraryFunctions() {

      /*
        func read int() Leser en int fra standard inn.
        func read float() Leser en float fra standard inn.
        func read char() Leser ett tegn fra standard inn og returnerer ASCII- verdien som en int. Returnerer -1 ved EOF.
        func read string() Leser en string fra standard inn opp til første whites- pace.
        func read line() Leser en tekstlinje fra standard inn.
        func print int( int i ) Skriver en int til standard ut.
        func print float( float f ) Skriver en float til standard ut.
        func print str( string s ) Skriver en streng til standard ut.
        func print line( string s ) Skriver en streng til standard ut fulgt av et linjeskift.
    */

        addReadIntToDeclList();
        addReadFloatToDeclList();
        addReadCharToDeclList();
        addReadLineToDeclList();

        addPrintIntToDeclList();
        addPrintFloatToDeclList();
        addPrintStrToDeclList();
        addPrintLineToDeclList();



    }

    private void addPrintLineToDeclList() {
        makeFunction(new Name("print_line"),
                                  new VoidType(), makeFormalParam(new StringType(), new Name("i_am_so_tired_in_the_face")));
    }

    private void addPrintStrToDeclList() {
        makeFunction(new Name("print_str"),
                                  new VoidType(),
                                  makeFormalParam(new StringType(),
                                  new Name("print_my_float")));
    }

    private void addPrintIntToDeclList() {
        makeFunction(new Name("print_int"), new VoidType(), makeFormalParam(new IntegerType(), new Name("print_my_int")));
    }

    private void addReadLineToDeclList() {
        makeFunction(new Name("read_line"), new StringType(), null);
    }

    private void addReadCharToDeclList() {
        makeFunction(new Name("read_char"), new IntegerType(), null);
    }

    private void addReadFloatToDeclList() {
        makeFunction(new Name("read_float"), new FloatType(), null);
    }

    private void addReadIntToDeclList() {
        makeFunction(new Name("read_int"), new IntegerType(), null);
    }

    private void addPrintFloatToDeclList() {
        //print_float(float)
        makeFunction(new Name("print_float"),
                                           new VoidType(),
                                           makeFormalParam(new FloatType(), new Name("to_print")));
        
    }

    private FuncDecl makeFunction(Name name, final Type returnType, FormalParam formalParam) {
        List<FormalParam> paramList = new ArrayList<FormalParam>();
        List<Statement> smList = new ArrayList<Statement>();


        ReturnStatement rs = new ReturnStatement(new Expression() {
            @Override
            public Type getType() {
                return returnType;
            }

            @Override
            public void generateCode(CodeProcedure codeProcedure) {
            }

            @Override
            public String printAst(int indent) {
                return null;
            }

            @Override
            public void checkSemantics() {
            }

        });
        smList.add(rs);

        if(formalParam != null)
            paramList.add(formalParam);

        FuncDecl func = new FuncDecl(name,
                paramList,
                returnType,
                new ArrayList<Decl>(),
                smList
        );
        func.checkSemantics();
        return func;
    }

    private FormalParam makeFormalParam(Type type, Name name) {
        return new FormalParam(false, type, name);    
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
        addLibraryFunctions(codeFile);

        for (Decl decl : declList) {
            decl.generateCode(codeFile);
        }

        codeFile.setMain("Main");
    }

    private void addLibraryFunctions(CodeFile codeFile) {
        addPrintFloat(codeFile);
        addPrintInt(codeFile);
        addPrintLine(codeFile);
        addPrintStr(codeFile);

        addReadFloat(codeFile);
        addReadInt(codeFile);
        addReadChar(codeFile);
        addReadString(codeFile);
    }

    private void addReadFloat(CodeFile codeFile) {
        codeFile.addProcedure("read_float");
        CodeProcedure read_float = new CodeProcedure("read_float", bytecode.type.FloatType.TYPE, codeFile);
        codeFile.updateProcedure(read_float);
    }


    private void addReadInt(CodeFile codeFile) {
        codeFile.addProcedure("read_int");
        CodeProcedure read_int = new CodeProcedure("read_int", bytecode.type.IntType.TYPE, codeFile);
        codeFile.updateProcedure(read_int);
    }


    private void addReadChar(CodeFile codeFile) {
        codeFile.addProcedure("read_char");
        CodeProcedure read_char = new CodeProcedure("read_char", bytecode.type.IntType.TYPE, codeFile);
        codeFile.updateProcedure(read_char);
    }


    private void addReadString(CodeFile codeFile) {
        codeFile.addProcedure("read_string");
        CodeProcedure read_string = new CodeProcedure("read_string", bytecode.type.StringType.TYPE, codeFile);
        codeFile.updateProcedure(read_string);
    }




    private void addPrintFloat(CodeFile codeFile) {
        codeFile.addProcedure("print_float");
        CodeProcedure print_float = new CodeProcedure("print_float", bytecode.type.VoidType.TYPE, codeFile);
        print_float.addParameter("f", bytecode.type.FloatType.TYPE);
        codeFile.updateProcedure(print_float);
    }

    private void addPrintInt(CodeFile codeFile) {
        codeFile.addProcedure("print_int");
        CodeProcedure print_int = new CodeProcedure("print_int", bytecode.type.VoidType.TYPE, codeFile);
        print_int.addParameter("i", bytecode.type.IntType.TYPE);
        codeFile.updateProcedure(print_int);
    }

    private void addPrintLine(CodeFile codeFile) {
        codeFile.addProcedure("print_line");
        CodeProcedure print_line = new CodeProcedure("print_line", bytecode.type.VoidType.TYPE, codeFile);
        print_line.addParameter("l", bytecode.type.StringType.TYPE);
        codeFile.updateProcedure(print_line);
    }

     private void addPrintStr(CodeFile codeFile) {
        codeFile.addProcedure("print_str");
        CodeProcedure print_str = new CodeProcedure("print_str", bytecode.type.VoidType.TYPE, codeFile);
        print_str.addParameter("s", bytecode.type.StringType.TYPE);
        codeFile.updateProcedure(print_str);
    }

    public void checkSemantics() throws SemanticsException {
        addLibraryFunctions();


        for(Decl d : declList) {
            d.checkSemantics();
        }

        if(Node.getSymbolTable().lookup(new Name("Main")) == null)
            throw new MainFunctionDeclarationException(null);

    }
}
