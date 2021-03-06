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
%state IN_STRING_LITERAL
%state IN_QUOTED_IDENTIFIER
%state IN_RULE_NAME_WHITESPACE
%state IN_RULE_NAME
%state IN_CLASS_REF_WHITESPACE
%state IN_CLASS_REF
%state IN_METHOD_REF_WHITESPACE
%state IN_METHOD_REF

%{
    /**
     * Build a new instance of {@link BytemanFlexLexer}.
     */
    public BytemanFlexLexer() {
      this((java.io.Reader)null);
    }
%}

EOL = \r\n | \r | \n
WhiteSpace = {EOL} | [ \t\f]
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
  "BIND" | "bind"                                                    { return KEYWORD_BIND; }
  "IF" | "if"                                                        { return KEYWORD_IF; }
  "DO" | "do"                                                        { return KEYWORD_DO; }
  "RULE"                                                             { yybegin(IN_RULE_NAME_WHITESPACE); return KEYWORD_RULE; }
  "CLASS"                                                            { yybegin(IN_CLASS_REF_WHITESPACE); return KEYWORD_CLASS; }
  "INTERFACE"                                                        { yybegin(IN_CLASS_REF_WHITESPACE); return KEYWORD_INTERFACE; }
  "METHOD"                                                           { yybegin(IN_METHOD_REF_WHITESPACE); return KEYWORD_METHOD; }
  "HELPER"                                                           { yybegin(IN_CLASS_REF_WHITESPACE); return KEYWORD_HELPER; }
  "LINE"                                                             { return KEYWORD_LINE; }
  "ENDRULE"                                                          { return KEYWORD_ENDRULE; }
  "NOTHING" | "nothing"                                              { return KEYWORD_NOTHING; }
  "TRUE" | "true" | "FALSE" | "false"                                { return BOOLEAN_LITERAL; }
  "RETURN" | "return" | "EXIT"                                       { return KEYWORD_RETURN; }
  "THROW" | "throw"                                                  { return KEYWORD_THROW; }
  "NEW" | "new"                                                      { return KEYWORD_NEW; }
  "AFTER"                                                            { return KEYWORD_AFTER; }
  "ALL"                                                              { return KEYWORD_ALL; }
  "AT"                                                               { return KEYWORD_AT; }
  "ENTRY"                                                            { return KEYWORD_ENTRY; }
  "INVOKE" | "CALL"                                                  { return KEYWORD_INVOKE; }
  "READ"                                                             { return KEYWORD_READ; }
  "WRITE"                                                            { return KEYWORD_SYNCHRONIZE; }
  "SYNCHRONIZE"                                                      { return KEYWORD_WRITE; }
  "("                                                                { return LPAREN; }
  ")"                                                                { return RPAREN; }
  "["                                                                { return LSQUARE; }
  "]"                                                                { return RSQUARE; }
  ";"                                                                { return SEMI; }
  ","                                                                { return COMMA; }
  "."                                                                { return DOT; }
  "=" | "<--"                                                        { return ASSIGN; }
  "||" | "OR" | "or"                                                 { return OR; }
  "&&" | "AND" | "and"                                               { return AND; }
  "!" | "NOT" | "not"                                                { return NOT; }
  "<" | "LT" | "lt"                                                  { return LT; }
  "<=" | "LE" | "le"                                                 { return LE; }
  "==" | "EQ" | "eq"                                                 { return EQ; }
  "!=" | "NE" | "ne"                                                 { return NE; }
  ">=" | "GE" | "ge"                                                 { return GE; }
  ">" | "GT" | "gt"                                                  { return GT; }
  ">>>"                                                              { return URSH; }
  ">>"                                                               { return RSH; }
  "<<"                                                               { return LSH; }
  "|"                                                                { return BOR; }
  "&"                                                                { return BAND; }
  "^"                                                                { return BXOR; }
  "~"                                                                { return TWIDDLE; }
  "*" | "TIMES" | "times"                                            { return MUL; }
  "/" | "DIVIDE" | "divide"                                          { return DIV; }
  "+" | "PLUS" | "plus"                                              { return PLUS; }
  "-" | "MINUS" | "minus"                                            { return MINUS; }
  "%" | "MOD" | "mod"                                                { return MOD; }
  "?"                                                                { return TERN_IF; }
  ":"                                                                { return COLON; }
  "$!" | "$^" | "$#" | "$*" | "$@" | "$"{Integer} | "$"{Identifier}  { return DOLLAR; }
  "NULL" | "null"                                                    { return KEYWORD_NULL; }
  {Identifier}                                                       { return IDENTIFIER; }
  {Integer}                                                          { return INTEGER_LITERAL; }
  {Float}                                                            { return FLOAT_LITERAL; }
  \"                                                                 { yybegin(IN_STRING_LITERAL); return STRING_LITERAL; }
  \'                                                                 { yybegin(IN_QUOTED_IDENTIFIER); return QUOTED_IDENTIFIER; }
  {Comment}                                                          { return COMMENT; }
  {WhiteSpace}                                                       { return WHITE_SPACE; }
  [^]                                                                { return ERROR_ELEMENT; }
}

<IN_STRING_LITERAL> {
  \"                                                                 { yybegin(YYINITIAL); return STRING_LITERAL; }
  (\\\"|[^\n\r\"]|\\[\n\r\ \t\b])*                                   { return STRING_LITERAL; }
  [^]                                                                { return ERROR_ELEMENT; }
}

<IN_QUOTED_IDENTIFIER> {
  \'                                                                 { yybegin(YYINITIAL); return QUOTED_IDENTIFIER; }
  [^\n\r\']+                                                         { return QUOTED_IDENTIFIER; }
  [^]                                                                { return ERROR_ELEMENT; }
}

<IN_RULE_NAME_WHITESPACE> {
  {WhiteSpace}+                                                      { yybegin(IN_RULE_NAME); return WHITE_SPACE; }
  [^]                                                                { return ERROR_ELEMENT; }
}

<IN_RULE_NAME> {
  [^\n\r]+                                                           { return RULE_NAME; }
  {EOL}                                                              { yybegin(YYINITIAL); return WHITE_SPACE; }
  [^]                                                                { return ERROR_ELEMENT; }
}

<IN_CLASS_REF_WHITESPACE> {
  {WhiteSpace}+                                                      { yybegin(IN_CLASS_REF); return WHITE_SPACE; }
  [^]                                                                { return ERROR_ELEMENT; }
}

<IN_CLASS_REF> {
  "^"                                                                { return OVERRIDE; }
  [^\n\r]+                                                           { return CLASS_REF; }
  {EOL}                                                              { yybegin(YYINITIAL); return WHITE_SPACE; }
  [^]                                                                { return ERROR_ELEMENT; }
}

<IN_METHOD_REF_WHITESPACE> {
  {WhiteSpace}+                                                      { yybegin(IN_METHOD_REF); return WHITE_SPACE; }
  [^]                                                                { return ERROR_ELEMENT; }
}

<IN_METHOD_REF> {
  [^\n\r]+                                                           { return METHOD_REF; }
  {EOL}                                                              { yybegin(YYINITIAL); return WHITE_SPACE; }
  [^]                                                                { return ERROR_ELEMENT; }
}
