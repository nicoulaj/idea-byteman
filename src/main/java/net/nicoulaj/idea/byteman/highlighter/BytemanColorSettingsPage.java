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
package net.nicoulaj.idea.byteman.highlighter;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.intellij.openapi.util.io.FileUtil;
import net.nicoulaj.idea.byteman.BytemanBundle;
import net.nicoulaj.idea.byteman.BytemanIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Color settings page for the Byteman editor.
 *
 * @author <a href="mailto:julien.nicoulaud@gmail.com">Julien Nicoulaud</a>
 * @since 0.1
 */
public class BytemanColorSettingsPage implements ColorSettingsPage {

    /**
     * The {@link Logger}.
     */
    private final static Logger LOGGER = Logger.getInstance(BytemanColorSettingsPage.class.getName());

    /**
     * An empty {@link ColorDescriptor} array.
     *
     * @see #getColorDescriptors()
     */
    protected static final ColorDescriptor[] EMPTY_COLOR_DESCRIPTOR_ARRAY = new ColorDescriptor[]{};

    /**
     * The path to the sample Byteman document shown in the colors settings dialog.
     */
    @NonNls
    protected static final String SAMPLE_BYTEMAN_FILE_PATH = "/net/nicoulaj/idea/byteman/sample.btm";

    /**
     * The sample Byteman document shown in the colors settings dialog.
     *
     * @see #loadSampleBytemanFile()
     */
    protected static final String SAMPLE_BYTEMAN_FILE = loadSampleBytemanFile();

    /**
     * The set of {@link AttributesDescriptor} defining the configurable options in the dialog.
     *
     * @see #BytemanColorSettingsPage()
     */
    protected final List<AttributesDescriptor> attributeDescriptors = new LinkedList<AttributesDescriptor>();

    /**
     * Build a new instance of {@link BytemanColorSettingsPage}.
     */
    public BytemanColorSettingsPage() {

        // Populate attribute descriptors.
        attributeDescriptors.add(new AttributesDescriptor(
        BytemanBundle.message("byteman.editor.colorsettingspage.keyword"),
        BytemanHighlighterColors.KEYWORD_ATTR_KEY)
        );
        attributeDescriptors.add(new AttributesDescriptor(
        BytemanBundle.message("byteman.editor.colorsettingspage.bracket"),
        BytemanHighlighterColors.BRACKETS_ATTR_KEY)
        );
        attributeDescriptors.add(new AttributesDescriptor(
        BytemanBundle.message("byteman.editor.colorsettingspage.expression-separator"),
        BytemanHighlighterColors.EXPRESSION_SEPARATOR_ATTR_KEY)
        );
        attributeDescriptors.add(new AttributesDescriptor(
        BytemanBundle.message("byteman.editor.colorsettingspage.binding-separator"),
        BytemanHighlighterColors.BINDING_SEPARATOR_ATTR_KEY)
        );
        attributeDescriptors.add(new AttributesDescriptor(
        BytemanBundle.message("byteman.editor.colorsettingspage.identifier-punctuator"),
        BytemanHighlighterColors.IDENTIFIER_PUNCTUATOR_ATTR_KEY)
        );
        attributeDescriptors.add(new AttributesDescriptor(
        BytemanBundle.message("byteman.editor.colorsettingspage.assign-operator"),
        BytemanHighlighterColors.ASSIGN_OPERATOR_ATTR_KEY)
        );
        attributeDescriptors.add(new AttributesDescriptor(
        BytemanBundle.message("byteman.editor.colorsettingspage.logical-operator"),
        BytemanHighlighterColors.LOGICAL_OPERATOR_ATTR_KEY)
        );
        attributeDescriptors.add(new AttributesDescriptor(
        BytemanBundle.message("byteman.editor.colorsettingspage.comparison-operator"),
        BytemanHighlighterColors.COMPARISON_OPERATOR_ATTR_KEY)
        );
        attributeDescriptors.add(new AttributesDescriptor(
        BytemanBundle.message("byteman.editor.colorsettingspage.bytewise-operator"),
        BytemanHighlighterColors.BYTEWISE_OPERATOR_ATTR_KEY)
        );
        attributeDescriptors.add(new AttributesDescriptor(
        BytemanBundle.message("byteman.editor.colorsettingspage.arithmetic-operator"),
        BytemanHighlighterColors.ARITHMETIC_OPERATOR_ATTR_KEY)
        );
        attributeDescriptors.add(new AttributesDescriptor(
        BytemanBundle.message("byteman.editor.colorsettingspage.ternary-condition"),
        BytemanHighlighterColors.TERNARY_CONDITION_ATTR_KEY)
        );
        attributeDescriptors.add(new AttributesDescriptor(
        BytemanBundle.message("byteman.editor.colorsettingspage.dollar-prefixed-identifier"),
        BytemanHighlighterColors.DOLLAR_PREFIXED_IDENTIFIER_ATTR_KEY)
        );
        attributeDescriptors.add(new AttributesDescriptor(
        BytemanBundle.message("byteman.editor.colorsettingspage.identifier"),
        BytemanHighlighterColors.IDENTIFIER_ATTR_KEY)
        );
        attributeDescriptors.add(new AttributesDescriptor(
        BytemanBundle.message("byteman.editor.colorsettingspage.number"),
        BytemanHighlighterColors.NUMBER_ATTR_KEY)
        );
        attributeDescriptors.add(new AttributesDescriptor(
        BytemanBundle.message("byteman.editor.colorsettingspage.string"),
        BytemanHighlighterColors.STRING_ATTR_KEY)
        );
        attributeDescriptors.add(new AttributesDescriptor(
        BytemanBundle.message("byteman.editor.colorsettingspage.white-space"),
        BytemanHighlighterColors.WHITE_SPACE_ATTR_KEY)
        );
        attributeDescriptors.add(new AttributesDescriptor(
        BytemanBundle.message("byteman.editor.colorsettingspage.comment"),
        BytemanHighlighterColors.COMMENT_ATTR_KEY)
        );
        attributeDescriptors.add(new AttributesDescriptor(
        BytemanBundle.message("byteman.editor.colorsettingspage.error"),
        BytemanHighlighterColors.BAD_CHARACTER)
        );
    }

    /**
     * Get the mapping from special tag names surrounding the regions to be highlighted in the preview text to text attribute keys used to highlight the regions.
     *
     * @return {@code null} as the demo text does not contain any additional highlighting tags.
     */
    @Nullable
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    /**
     * Get the set of {@link AttributesDescriptor} defining the configurable options in the dialog.
     *
     * @return {@link #attributeDescriptors} as an array.
     */
    @NotNull
    public AttributesDescriptor[] getAttributeDescriptors() {
        return attributeDescriptors.toArray(new AttributesDescriptor[attributeDescriptors.size()]);
    }

    /**
     * Get the list of descriptors specifying the {@link com.intellij.openapi.editor.colors.ColorKey} instances for which colors are specified in the settings page.
     *
     * @return {@link #EMPTY_COLOR_DESCRIPTOR_ARRAY}
     */
    @NotNull
    public ColorDescriptor[] getColorDescriptors() {
        return EMPTY_COLOR_DESCRIPTOR_ARRAY;
    }

    /**
     * Get the text shown in the preview pane.
     *
     * @return {@link #SAMPLE_BYTEMAN_FILE}
     * @see #SAMPLE_BYTEMAN_FILE_PATH
     * @see #loadSampleBytemanFile()
     */
    @NonNls
    @NotNull
    public String getDemoText() {
        return SAMPLE_BYTEMAN_FILE;
    }

    /**
     * Get the title of the page, shown as text in the dialog tab.
     *
     * @return the name as defined by {@link BytemanBundle}
     */
    @NotNull
    public String getDisplayName() {
        return BytemanBundle.message("byteman.filetype.name");
    }

    /**
     * Get the syntax highlighter which is used to highlight the text shown in the preview pane of the page.
     *
     * @return an instance of {@link BytemanSyntaxHighlighter}
     */
    @NotNull
    public SyntaxHighlighter getHighlighter() {
        return new BytemanSyntaxHighlighter();
    }

    /**
     * Get the icon for the page, shown in the dialog tab.
     *
     * @return {@link BytemanIcons#BYTEMAN_ICON}
     */
    @Nullable
    public Icon getIcon() {
        return BytemanIcons.BYTEMAN_ICON;
    }

    /**
     * Load the sample text to be displayed in the preview pane.
     *
     * @return the text loaded from {@link #SAMPLE_BYTEMAN_FILE_PATH}
     * @see #getDemoText()
     * @see #SAMPLE_BYTEMAN_FILE_PATH
     * @see #SAMPLE_BYTEMAN_FILE
     */
    protected static String loadSampleBytemanFile() {
        try {
            return FileUtil.loadTextAndClose(new InputStreamReader(BytemanColorSettingsPage.class.getResourceAsStream(SAMPLE_BYTEMAN_FILE_PATH)));
        }
        catch (Exception e) {
            LOGGER.error("Failed loading sample Byteman file", e);
        }
        return BytemanBundle.message("byteman.editor.colorsettingspage.sample-loading-error");
    }
}
