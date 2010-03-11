package dflat.parser;
import java_cup.runtime.*;
%%

%class Lexer
%unicode
%cup
%line
%column
%public
%{
  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}
LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
Name 	   = [:jletter:] [:jletterdigit:]*  
 
%%
<YYINITIAL>{
	{WhiteSpace}        { }
	"var"				{ return symbol(sym.VAR);}
	"class"				{ return symbol(sym.CLASS);}
	"func"				{ return symbol(sym.FUNC); }
	"ret"				{ return symbol(sym.RET); }
	"ref"				{ return symbol(sym.REF); }
	"int"				{ return symbol(sym.INTEGER);}
	"string"			{ return symbol(sym.STRING);}
	"bool"				{ return symbol(sym.BOOLEAN);}
	"float"				{ return symbol(sym.FLOAT);}
	{Name}				{ return symbol(sym.NAME, yytext());}	
	";"					{ return symbol(sym.SEMI);}
	"{"				    { return symbol(sym.LBRACK);}
	"}"				    { return symbol(sym.RBRACK);}
	"("				    { return symbol(sym.LPARENT);}
	")"					{ return symbol(sym.RPARENT);}
}

.     		                { throw new Error("Illegal character '" + yytext() + "' at line " + yyline + ", column " + yycolumn + "."); }
