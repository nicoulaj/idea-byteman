/*
 * Copyright (c) 2011-2012 Julien Nicoulaud <julien.nicoulaud@gmail.com>
 *
 * This file is part of idea-byteman.
 *
 * idea-byteman is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * idea-byteman is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with idea-byteman.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.nicoulaj.idea.byteman.lang.lexer;

import com.intellij.psi.tree.IElementType;
import com.intellij.lexer.FlexLexer;
import static net.nicoulaj.idea.byteman.lang.BytemanTypes.*;
import static com.intellij.psi.TokenType.*;

/**
 * Lexer for Byteman.
 * <p/>
 * Derived from Byteman project's <code>ECAToken.flex</code> JFlex file.
 *
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
@SuppressWarnings({"ALL"})
%%

%class BytemanFlexLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}
%state STRING
//%state IN_QUOTEDIDENT
%state IN_RULE_NAME
%state IN_CLASS_REF
%state IN_METHOD_REF

%{
    /**
     * Build a new instance of {@link BytemanFlexLexer}.
     */
    public BytemanFlexLexer() {
      this((java.io.Reader)null);
    }
%}

LineTerminator = \r | \n | \r\n
WhiteSpace = {LineTerminator} | [ \t\f]
Identifier = ([A-Za-z_]) ([A-Za-z0-9$_])*
PosInteger = 0 | [1-9][0-9]*
Sign = [+-]
Exp = [Ee]
Dot = "."
DotTrailing = {Dot} {PosInteger}?
ExpTrailing = {Exp} {Sign}? {PosInteger}
FloatTrailing = {ExpTrailing} | {DotTrailing} {ExpTrailing}?
PosFloat = {PosInteger} {FloatTrailing}
Integer = {Sign}? {PosInteger}
Float = {Sign}? {PosFloat}
Comment= "#" [^\r\n]*

%%

<YYINITIAL> {
  "BIND" | "bind"	                                                  { return KEYWORD_BIND; }
  "IF" | "if"	                                                      { return KEYWORD_IF; }
  "DO" | "do"	                                                      { return KEYWORD_DO; }
  "RULE" {WhiteSpace}                                               { yybegin(IN_RULE_NAME); return KEYWORD_RULE; }
  "CLASS" {WhiteSpace}	| "INTERFACE"	{WhiteSpace}                  { yybegin(IN_CLASS_REF); return KEYWORD_CLASS; }
  "METHOD" {WhiteSpace}	                                            { yybegin(IN_METHOD_REF); return KEYWORD_METHOD; }
  "HELPER" {WhiteSpace}	                                            { yybegin(IN_CLASS_REF); return KEYWORD_HELPER; }
  "LINE"		                                                        { return KEYWORD_LINE; }
  "ENDRULE"	                                                        { return KEYWORD_ENDRULE; }
  "NOTHING" | "nothing"		                                          { return KEYWORD_NOTHING; }
  "TRUE" | "true" | "FALSE" | "false"	                              { return BOOLEAN_LITERAL; }
  "RETURN" | "return" | "EXIT"                                      { return KEYWORD_RETURN; }
  "THROW" | "throw"	                                                { return KEYWORD_THROW; }
  "NEW" | "new"	                                                    { return KEYWORD_NEW; }
  "AFTER"	                                                          { return KEYWORD_AFTER; }
  "ALL"	                                                            { return KEYWORD_ALL; }
  "AT"	                                                            { return KEYWORD_AT; }
  "ENTRY"	                                                          { return KEYWORD_ENTRY; }
  "INVOKE" | "CALL"                                                 { return KEYWORD_INVOKE; }
  "READ"	                                                          { return KEYWORD_READ; }
  "WRITE"	                                                          { return KEYWORD_SYNCHRONIZE; }
  "SYNCHRONIZE"	                                                    { return KEYWORD_WRITE; }
  "("		                                                            { return LPAREN; }
  ")"		                                                            { return RPAREN; }
  "["		                                                            { return LSQUARE; }
  "]"		                                                            { return RSQUARE; }
  ";"		                                                            { return SEMI; }
  ","		                                                            { return COMMA; }
  "."		                                                            { return DOT; }
  "=" | "<--"	                                                      { return ASSIGN; }
  "||" | "OR" | "or"	                                              { return OR; }
  "&&" | "AND" | "and"	                                            { return AND; }
  "!" | "NOT" | "not"	                                              { return NOT; }
  "<" | "LT" | "lt"	                                                { return LT; }
  "<=" | "LE" | "le"	                                              { return LE; }
  "==" | "EQ" | "eq"	                                              { return EQ; }
  "!=" | "NE" | "ne"	                                              { return NE; }
  ">=" | "GE" | "ge"	                                              { return GE; }
  ">" | "GT" | "gt"                                                 { return GT; }
  ">>>"                                                             { return URSH; }
  ">>"                                                              { return RSH; }
  "<<"                                                              { return LSH; }
  "|"			                                                          { return BOR; }
  "&"			                                                          { return BAND; }
  "^"			                                                          { return BXOR; }
  "~"			                                                          { return TWIDDLE; }
  "*" | "TIMES" | "times"	                                          { return MUL; }
  "/" | "DIVIDE" | "divide"	                                        { return DIV; }
  "+" | "PLUS" | "plus"	                                            { return PLUS; }
  "-" | "MINUS" | "minus"	                                          { return MINUS; }
  "%" | "MOD" | "mod"	                                              { return MOD; }
  "?"			                                                          { return TERN_IF; }
  ":"			                                                          { return COLON; }
  "$!" | "$^" | "$#" | "$*" | "$@" | "$"{Integer} | "$"{Identifier} { return DOLLAR; }
  "NULL" | "null"                                                   { return NULL_LITERAL; }
  {Identifier}		                                                  { return IDENTIFIER; }
  {Integer}	                                                        { return INTEGER_LITERAL; }
  {Float}		                                                        { return FLOAT_LITERAL; }
  \"			                                                          { yybegin(STRING); return STRING_LITERAL; }
//  \'			                                                          { yybegin(IN_QUOTEDIDENT); return QUOTEDIDENT; }
  {Comment}                                                         { return COMMENT; }
  {WhiteSpace} | {LineTerminator}                                   { return WHITE_SPACE; }
  [^]			                                                          { return ERROR_ELEMENT; }
}

<STRING> {
  \"			                                                          { yybegin(YYINITIAL); return STRING_LITERAL; }
  [^\n\r\"\\]+                                                      { return STRING_LITERAL; }
  \\t	| \\n | \\r | \\\" | \\		                                    { return STRING_LITERAL; }
  \n			                                                          { return ERROR_ELEMENT; }
  [^]			                                                          { return ERROR_ELEMENT; }
}

//<IN_QUOTEDIDENT> {
//  [^\n\r']+		                                                      { return QUOTEDIDENT; }
//  '			                                                            { yybegin(YYINITIAL); return IDENTIFIER; }
//  {LineTerminator}                                                  { return ERROR_ELEMENT; }
//  [^]			                                                          { return ERROR_ELEMENT; }
//}

<IN_RULE_NAME> {
  [^\n\r]+		                                                      { return RULE_NAME; }
  {LineTerminator}                                                  { yybegin(YYINITIAL); return WHITE_SPACE; }
  [^]			                                                          { return ERROR_ELEMENT; }
}

<IN_CLASS_REF> {
  "^"		                                                            { return OVERRIDE; }
  [^\n\r]+		                                                      { return CLASS_REF; }
  {LineTerminator}                                                  { yybegin(YYINITIAL); return WHITE_SPACE; }
  [^]			                                                          { return ERROR_ELEMENT; }
}

<IN_METHOD_REF> {
  [^\n\r]+		                                                      { return METHOD_REF; }
  {LineTerminator}                                                  { yybegin(YYINITIAL); return WHITE_SPACE; }
  [^]			                                                          { return ERROR_ELEMENT; }
}
