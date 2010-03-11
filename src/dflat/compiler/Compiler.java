package dflat.compiler;

import java.io.*;
import dflat.syntaxtree.*;
import dflat.parser.*;

public class Compiler {
	private String inFilename = null;
	private String outFilename = null;
	public Compiler(String inFilename, String outFilename){
		this.inFilename = inFilename;
		this.outFilename = outFilename;
	}
	public void compile() throws Exception {
		InputStream inputStream = null;
		inputStream = new FileInputStream(this.inFilename);
		Lexer lexer = new Lexer(inputStream);
		parser parser = new parser(lexer);
		Program program = (Program)parser.parse().value;
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.outFilename));
       String syntaxTree = program.printAst(0);
                bufferedWriter.write(syntaxTree);
                bufferedWriter.close();
                
                System.out.println(syntaxTree);
	}
	public static void main(String[] args) {
		Compiler compiler = new Compiler(args[0], args[1]);
		try {
			compiler.compile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
