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
  StringBuffer string  = new StringBuffer();
  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}
InputCharacter = [^\r\n]
LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
Comment		   = "//" {InputCharacter}* {LineTerminator}
Name 	   = [:jletter:] [:jletterdigit:]*
DecIntLiteral	=  0 | [1-9][0-9]*
FloatLiteral    = 0.0 | [0-9][0-9]*\.[0-9][0-9]*

%state	STRING
 
%%
<YYINITIAL>{
	{WhiteSpace}        {}
	{Comment}			{}
	"var"				{ return symbol(sym.VAR);}
	"class"				{ return symbol(sym.CLASS);}
	"func"				{ return symbol(sym.FUNC); }
	"ret"				{ return symbol(sym.RET); }
	"ref"				{ return symbol(sym.REF); }
	"int"				{ return symbol(sym.INTEGER);}
	"string"			{ return symbol(sym.STRING);}
	"bool"				{ return symbol(sym.BOOLEAN);}
	"float"				{ return symbol(sym.FLOAT);}
	"true"				{ return symbol(sym.TRUE);}
	"false"				{ return symbol(sym.FALSE);}
	"null"				{ return symbol(sym.NULL);}
	"return"			{ return symbol(sym.RETURN);}
	"while"				{ return symbol(sym.WHILE);}
	"do"				{ return symbol(sym.DO);}
	"if"				{ return symbol(sym.IF);}
	"then"				{ return symbol(sym.THEN);}
	"else"				{ return symbol(sym.ELSE);}
	"new"				{ return symbol(sym.NEW);}
	{DecIntLiteral}		{ return symbol(sym.DEC_INT_LITERAL, yytext());}
	{FloatLiteral}		{ return symbol(sym.FLOAT_LITERAL, yytext());}
	\"                  { string.setLength(0); yybegin(STRING); }
	{Name}				{ return symbol(sym.NAME, yytext());}	
	";"					{ return symbol(sym.SEMI);}
	"{"				    { return symbol(sym.LBRACK);}
	"}"				    { return symbol(sym.RBRACK);}
	"("				    { return symbol(sym.LPARENT);}
	")"					{ return symbol(sym.RPARENT);}
	"!"					{ return symbol(sym.EXCLAM);}
	\.					{ return symbol(sym.PERIOD);}
	,					{ return symbol(sym.COMMA);}
	"&&"				{ return symbol(sym.AND);}
	"||"				{ return symbol(sym.OR);}
	"+"					{ return symbol(sym.PLUS);}
	"-"					{ return symbol(sym.MINUS);}
	"*"					{ return symbol(sym.MULTIPLY);}
	"/"				    { return symbol(sym.DIVIDE);}
	"**"				{ return symbol(sym.EXPONENT);}
	"<"					{ return symbol(sym.LT);}
	">"					{ return symbol(sym.GT);}
	"<="				{ return symbol(sym.LTE);}
	">="				{ return symbol(sym.GTE);}
	"="					{ return symbol(sym.EQUAL);}
	"!="				{ return symbol(sym.NOT_EQUAL);}
	":="				{ return symbol(sym.ASSIGN);}
}

<STRING> {
  \"                             { yybegin(YYINITIAL); 
                                   return symbol(sym.STRING_LITERAL, 
                                   string.toString()); }
  [^\n\r\"\\]+                   { string.append( yytext() ); }
  \\t                            { string.append('\t'); }
  \\n                            { string.append('\n'); }

  \\r                            { string.append('\r'); }
  \\\"                           { string.append('\"'); }
  \\                             { string.append('\\'); }
}

.     		                { throw new Error("Illegal character '" + yytext() + "' at line " + yyline + ", column " + yycolumn + "."); }
