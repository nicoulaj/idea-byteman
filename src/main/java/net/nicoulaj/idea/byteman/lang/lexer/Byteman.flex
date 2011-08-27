/*
* JBoss, Home of Professional Open Source
* Copyright 2008-10 Red Hat and individual contributors
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* This is free software; you can redistribute it and/or modify it
* under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation; either version 2.1 of
* the License, or (at your option) any later version.
*
* This software is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this software; if not, write to the Free
* Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
* 02110-1301 USA, or see the FSF site: http://www.fsf.org.
*
* @authors Andrew Dinn
* @authors Julien Nicoulaud
*/

package net.nicoulaj.idea.byteman.lang.lexer;

import com.intellij.psi.tree.IElementType;
import com.intellij.lexer.FlexLexer;
import net.nicoulaj.idea.byteman.lang.BytemanTokenTypes;

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
%state QUOTEDIDENT
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
WhiteSpace = [ \t\f]
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
  "BIND" | "bind"	                                                  { return BytemanTokenTypes.BIND_KEYWORD; }
  "IF" | "if"	                                                      { return BytemanTokenTypes.IF_KEYWORD; }
  "DO" | "do"	                                                      { return BytemanTokenTypes.DO_KEYWORD; }
  "RULE" {WhiteSpace}                                               { yybegin(IN_RULE_NAME); return BytemanTokenTypes.RULE_KEYWORD; }
  "CLASS" {WhiteSpace}	| "INTERFACE"	{WhiteSpace}                  { yybegin(IN_CLASS_REF); return BytemanTokenTypes.CLASS_KEYWORD; }
  "METHOD" {WhiteSpace}	                                            { yybegin(IN_METHOD_REF); return BytemanTokenTypes.METHOD_KEYWORD; }
  "HELPER" {WhiteSpace}	                                            { yybegin(IN_CLASS_REF); return BytemanTokenTypes.HELPER_KEYWORD; }
  "LINE"		                                                        { return BytemanTokenTypes.LINE_KEYWORD; }
  "ENDRULE"	                                                        { return BytemanTokenTypes.ENDRULE_KEYWORD; }
  "NOTHING" | "nothing"		                                          { return BytemanTokenTypes.NOTHING_KEYWORD; }
  "TRUE" | "true" 	                                                { return BytemanTokenTypes.TRUE_KEYWORD; }
  "FALSE" | "false"	                                                { return BytemanTokenTypes.FALSE_KEYWORD; }
  "RETURN" | "return"		                                            { return BytemanTokenTypes.RETURN_KEYWORD; }
  "THROW" | "throw"	                                                { return BytemanTokenTypes.THROW_KEYWORD; }
  "NEW" | "new"	                                                    { return BytemanTokenTypes.NEW_KEYWORD; }
  "("		                                                            { return BytemanTokenTypes.LPAREN; }
  ")"		                                                            { return BytemanTokenTypes.RPAREN; }
  "["		                                                            { return BytemanTokenTypes.LSQUARE; }
  "]"		                                                            { return BytemanTokenTypes.RSQUARE; }
  "{"		                                                            { return BytemanTokenTypes.LBRACE; }
  "}"		                                                            { return BytemanTokenTypes.RBRACE; }
  ";"		                                                            { return BytemanTokenTypes.SEMI; }
  ","		                                                            { return BytemanTokenTypes.COMMA; }
  "."		                                                            { return BytemanTokenTypes.DOT; }
  "=" | "<--"	                                                      { return BytemanTokenTypes.ASSIGN; }
  "||" | "OR" | "or"	                                              { return BytemanTokenTypes.OR; }
  "&&" | "AND" | "and"	                                            { return BytemanTokenTypes.AND; }
  "!" | "NOT" | "not"	                                              { return BytemanTokenTypes.NOT; }
  "<" | "LT" | "lt"	                                                { return BytemanTokenTypes.LT; }
  "<=" | "LE" | "le"	                                              { return BytemanTokenTypes.LE; }
  "==" | "EQ" | "eq"	                                              { return BytemanTokenTypes.EQ; }
  "!=" | "NE" | "ne"	                                              { return BytemanTokenTypes.NE; }
  ">=" | "GE" | "ge"	                                              { return BytemanTokenTypes.GE; }
  ">" | "GT" | "gt"                                                 { return BytemanTokenTypes.GT; }
  "|"			                                                          { return BytemanTokenTypes.BOR; }
  "&"			                                                          { return BytemanTokenTypes.BAND; }
  "^"			                                                          { return BytemanTokenTypes.BXOR; }
  "~"			                                                          { return BytemanTokenTypes.TWIDDLE; }
  "*" | "TIMES" | "times"	                                          { return BytemanTokenTypes.MUL; }
  "/" | "DIVIDE" | "divide"	                                        { return BytemanTokenTypes.DIV; }
  "+" | "PLUS" | "plus"	                                            { return BytemanTokenTypes.PLUS; }
  "-" | "MINUS" | "minus"	                                          { return BytemanTokenTypes.MINUS; }
  "%" | "MOD" | "mod"	                                              { return BytemanTokenTypes.MOD; }
  "?"			                                                          { return BytemanTokenTypes.TERN_IF; }
  ":"			                                                          { return BytemanTokenTypes.COLON; }
  "$!" | "$^" | "$#" | "$*" | "$@" | "$"{Integer} | "$"{Identifier} { return BytemanTokenTypes.DOLLAR; }
  "NULL" | "null"                                                   { return BytemanTokenTypes.NULL_LITERAL; }
  {Identifier}		                                                  { return BytemanTokenTypes.IDENTIFIER; }
  {Integer}	                                                        { return BytemanTokenTypes.INTEGER_LITERAL; }
  {Float}		                                                        { return BytemanTokenTypes.FLOAT_LITERAL; }
  \"			                                                          { yybegin(STRING); return BytemanTokenTypes.STRING_LITERAL; }
  \'			                                                          { yybegin(QUOTEDIDENT); return BytemanTokenTypes.QUOTEDIDENT; }
  {Comment}                                                         { return BytemanTokenTypes.COMMENT; }
  {WhiteSpace} | {LineTerminator}                                   { return BytemanTokenTypes.WHITE_SPACE; }
  [^]			                                                          { return BytemanTokenTypes.ERROR_ELEMENT; }
}

<STRING> {
  \"			                                                          { yybegin(YYINITIAL); return BytemanTokenTypes.STRING_LITERAL; }
  [^\n\r\"\\]+                                                      { return BytemanTokenTypes.STRING_LITERAL; }
  \\t	| \\n | \\r | \\\" | \\		                                    { return BytemanTokenTypes.STRING_LITERAL; }
  \n			                                                          { return BytemanTokenTypes.ERROR_ELEMENT; }
  [^]			                                                          { return BytemanTokenTypes.ERROR_ELEMENT; }
}

<QUOTEDIDENT> {
  [^\n\r']+		                                                      { return BytemanTokenTypes.QUOTEDIDENT; }
  '			                                                            { yybegin(YYINITIAL); return BytemanTokenTypes.IDENTIFIER; }
  {LineTerminator}                                                  { return BytemanTokenTypes.ERROR_ELEMENT; }
  [^]			                                                          { return BytemanTokenTypes.ERROR_ELEMENT; }
}

<IN_RULE_NAME> {
  [^\n\r]+		                                                      { return BytemanTokenTypes.RULE_NAME; }
  {LineTerminator}                                                  { yybegin(YYINITIAL); return BytemanTokenTypes.WHITE_SPACE; }
  [^]			                                                          { return BytemanTokenTypes.ERROR_ELEMENT; }
}

<IN_CLASS_REF> {
  "^"		                                                            { return BytemanTokenTypes.OVERRIDE_KEYWORD; }
  [^\n\r]+		                                                      { return BytemanTokenTypes.CLASS_REF; }
  {LineTerminator}                                                  { yybegin(YYINITIAL); return BytemanTokenTypes.WHITE_SPACE; }
  [^]			                                                          { return BytemanTokenTypes.ERROR_ELEMENT; }
}

<IN_METHOD_REF> {
  [^\n\r]+		                                                      { return BytemanTokenTypes.METHOD_REF; }
  {LineTerminator}                                                  { yybegin(YYINITIAL); return BytemanTokenTypes.WHITE_SPACE; }
  [^]			                                                          { return BytemanTokenTypes.ERROR_ELEMENT; }
}
