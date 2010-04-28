package dflat.compiler;

import java.io.*;

import bytecode.CodeFile;

import dflat.exceptions.SemanticsException;
import dflat.parser.Lexer;
import dflat.parser.parser;
import dflat.syntaxtree.Program;


public class Compiler {
    private String inFilename = null;
    private String astFilename = null;
    private String binFilename = null;
    public String syntaxError;
    public String semanticError;

    public Compiler(String inFilename, String astFilename, String binFilename){
        this.inFilename = inFilename;
        this.astFilename = astFilename;
        this.binFilename = binFilename;
    }
    public int compile() throws Exception {
        InputStream inputStream = null;
        inputStream = new FileInputStream(this.inFilename);
        Lexer lexer = new Lexer(inputStream);
        parser parser = new parser(lexer);
        Program program;
        try {
            program = (Program)parser.parse().value;
        } catch (Exception e) {
            this.syntaxError = e.getMessage();
            e.printStackTrace();
            return 1;
        }



        // Check semanics.


        try {
            program.checkSemantics();
        } catch (SemanticsException e) {

            this.semanticError = e.getMessage();
            return 2; //semantic semanticError
        }

        //writeAST(program);
        //generateCode(program);
        return 0;

    }
    private void writeAST(Program program) throws Exception {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.astFilename));
        bufferedWriter.write(program.printAst(0));
        bufferedWriter.close();
    }
    private void generateCode(Program program) throws Exception {
        CodeFile codeFile = new CodeFile();
        program.generateCode(codeFile);
        byte[] bytecode = codeFile.getBytecode();
        DataOutputStream stream = new DataOutputStream(new FileOutputStream (this.binFilename));
        stream.write(bytecode);
        stream.close();
    }
    public static void main(String[] args) {
        Compiler compiler = new Compiler(args[0], args[1], args[2]);
        int result;
        try {
            result = compiler.compile();
            if(result == 1){
                System.out.println(compiler.syntaxError);
            } else if(result == 2){
                System.out.println(compiler.semanticError);
            }
            System.exit(result);
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            // If unknown semanticError.
            System.exit(3);
        }
    }
    public static String indent(int indent){
        String result = "";
        for(int i=0;i<indent; i++){
            result+=" ";
        }
        return result;
    }
}
