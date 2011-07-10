/*
 * Copyright (c) 2011 Julien Nicoulaud <julien.nicoulaud@gmail.com>
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
package net.nicoulaj.idea.byteman.lang;

/**
 * TODO Add Javadoc comment.
 *
 * @author Julien Nicoulaud <julien.nicoulaud@gmail.com>
 * @since 0.1
 */
public interface BytemanElementTypes extends BytemanTokenTypeSets {
    BytemanElementType ECA = new BytemanElementType("ECA");
    BytemanElementType CA = new BytemanElementType("CA");
    BytemanElementType RULE_STATEMENT = new BytemanElementType("RULE_STATEMENT");
    BytemanElementType RULE_NAME_ELEMENT = new BytemanElementType("RULE_NAME_ELEMENT");
    BytemanElementType CLASS_STATEMENT = new BytemanElementType("CLASS_STATEMENT");
    BytemanElementType CLASS_REF_ELEMENT = new BytemanElementType("CLASS_NAME_ELEMENT");
    BytemanElementType METHOD_STATEMENT = new BytemanElementType("METHOD_STATEMENT");
    BytemanElementType METHOD_REF_ELEMENT = new BytemanElementType("METHOD_NAME_ELEMENT");
    BytemanElementType HELPER_STATEMENT = new BytemanElementType("HELPER_STATEMENT");
    BytemanElementType EXPRESSION_STATEMENT = new BytemanElementType("EXPRESSION_STATEMENT");
}
