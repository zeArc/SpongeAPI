/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered.org <http://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.api.text;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import org.spongepowered.api.text.action.ClickAction;
import org.spongepowered.api.text.action.HoverAction;
import org.spongepowered.api.text.action.ShiftClickAction;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextStyle;
import org.spongepowered.api.text.translation.Translation;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Represents an immutable instance of formatted text that can be displayed on
 * the client. Each instance consists of content and a list of children messages
 * appended after the content of this message. The content of the message is
 * available through one of the subinterfaces.
 * <p>
 * Messages are primarily used for sending formatted chat messages to players,
 * but also in other places like books or signs.
 * </p>
 * <p>
 * Message instances can be created using the {@link TextBuilder} available
 * through one of the {@link TextHelper#builder()} methods.
 * </p>
 *
 * @see TextHelper#builder()
 * @see Text.Literal
 * @see Translatable
 * @see Selector
 * @see Score
 */
public abstract class Text {

    private final TextColor color;
    private final TextStyle style;

    private final List<Text> children;

    private @Nullable ClickAction<?> clickAction;
    private @Nullable HoverAction<?> hoverAction;
    private @Nullable ShiftClickAction<?> shiftClickAction;

    /**
     * Creates a Text instance with color and style.
     *
     * @param color The text color
     * @param style The text style
     */
    public Text(TextColor color, TextStyle style) {
        this.color = color;
        this.style = style;
        this.children = ImmutableList.of();
    }

    /**
     * Creates a Text instance with color, style, and children.
     *
     * @param color The text color
     * @param style The text style
     * @param children The text's children
     */
    public Text(TextColor color, TextStyle style, List<Text> children) {
        this.color = color;
        this.style = style;
        this.children = children;
    }

    /**
     * Creates a text instance with color, style, children, and text actions.
     *
     * @param color The text color
     * @param style The text style
     * @param children The text's children
     * @param clickAction The text's click action
     * @param hoverAction The text's hover action
     * @param shiftClickAction The text's shift click action
     */
    public Text(TextColor color, TextStyle style, List<Text> children,
                @Nullable ClickAction<?> clickAction,
                @Nullable HoverAction<?> hoverAction,
                @Nullable ShiftClickAction<?> shiftClickAction) {
        this.color = color;
        this.style = style;
        this.children = children;
        this.clickAction = clickAction;
        this.hoverAction = hoverAction;
        this.shiftClickAction = shiftClickAction;
    }

    /**
     * Returns the color of this {@link Text}.
     *
     * @return The color of this message
     */
    public TextColor getColor() {
        return color;
    }

    /**
     * Returns the style of this {@link Text}. This will return a compound
     * {@link TextStyle} if multiple different styles have been set.
     *
     * @return The style of this message
     */
    public TextStyle getStyle() {
        return style;
    }

    /**
     * Returns the list of children appended after the content of this
     * {@link Text}.
     *
     * @return The list of children
     */
    public List<Text> getChildren() {
        return children;
    }

    /**
     * Returns an {@link Iterable} over this message and all of its children.
     * This is recursive, the children of the children will be also included.
     *
     * @return An iterable over this message and the children messages
     */
    public abstract Iterable<Text> withChildren();

    /**
     * Returns the {@link ClickAction} executed on the client when this
     * {@link Text} gets clicked.
     *
     * @return The click action of this message, or {@link Optional#absent()} if
     *         not set
     */
    public Optional<ClickAction<?>> getClickAction() {
        return Optional.<ClickAction<?>>fromNullable(clickAction);
    }

    /**
     * Returns the {@link HoverAction} executed on the client when this
     * {@link Text} gets hovered.
     *
     * @return The hover action of this message, or {@link Optional#absent()} if
     *         not set
     */
    public Optional<HoverAction<?>> getHoverAction() {
        return Optional.<HoverAction<?>>fromNullable(hoverAction);
    }

    /**
     * Returns the {@link ShiftClickAction} executed on the client when this
     * {@link Text} gets shift-clicked.
     *
     * @return The shift-click action of this message, or
     *         {@link Optional#absent()} if not set
     */
    public Optional<ShiftClickAction<?>> getShiftClickAction() {
        return Optional.<ShiftClickAction<?>>fromNullable(shiftClickAction);
    }

    /**
     * Returns a new {@link TextBuilder} with the content of this message.
     * This can be used to edit an immutable {@link Text} instance.
     *
     * @return A new message builder with the content of this message
     */
    public abstract TextBuilder builder();

    /**
     * Returns a representation of this {@link Text} using the legacy color
     * codes.
     *
     * @return This message converted to the old color codes
     * @deprecated Legacy formatting codes are being phased out of Minecraft
     */
    @Deprecated
    public abstract String toLegacy();

    /**
     * Returns a representation of this {@link Text} using the legacy color
     * codes.
     *
     * @param code The legacy char to use for the message
     * @return This message converted to the old color codes
     * @deprecated Legacy formatting codes are being phased out of Minecraft
     */
    @Deprecated
    public abstract String toLegacy(char code);

    /**
     * Represents a {@link Text} containing a plain text {@link String}.
     */
    public static abstract class Literal extends Text {

        private final String literal;

        public Literal(String literal, TextColor color, TextStyle style) {
            super(color, style);
            this.literal = literal;
        }

        public Literal(String literal, TextColor color, TextStyle style, List<Text> children) {
            super(color, style, children);
            this.literal = literal;
        }

        public Literal(String literal, TextColor color, TextStyle style, List<Text> children,
                       @Nullable ClickAction<?> clickAction,
                       @Nullable HoverAction<?> hoverAction,
                       @Nullable ShiftClickAction<?> shiftClickAction) {
            super(color, style, children, clickAction, hoverAction, shiftClickAction);
            this.literal = literal;
        }

        /**
         * Returns the literal content of this {@link Text}.
         *
         * @return The content of this message
         */
        public abstract String getLiteral();

        @Override
        public abstract TextBuilder.Literal builder();

    }

    /**
     * Represents a {@link Text} containing a {@link Translation} identifier
     * that gets translated into the current locale on the client.
     */
    public static abstract class Translatable extends Text {

        private final Translation translation;

        protected Translatable() {
            this.translation = translation;
        }

        public Translatable(Translation translation, TextColor color, TextStyle style) {
            super(color, style);
        }

        public Translatable(Translation translation, TextColor color, TextStyle style, List<Text> children) {
            super(color, style, children);
        }

        public Translatable(Translation translation, TextColor color, TextStyle style, List<Text> children,
                            @Nullable ClickAction<?> clickAction,
                            @Nullable HoverAction<?> hoverAction,
                            @Nullable ShiftClickAction<?> shiftClickAction) {
            super(color, style, children, clickAction, hoverAction, shiftClickAction);
        }

        /**
         * Returns the translation of this {@link Text}.
         *
         * @return The translation of this message
         */
        @Override
        Translation getTranslation();

        /**
         * Returns the list of {@link Translation} arguments used to format this
         * {@link Text}.
         *
         * @return The list of translation arguments
         */
        List<Object> getArguments();

        @Override
        TextBuilder.Translatable builder();

    }

    /**
     * Represents a {@link Text} containing a selector that will be replaced
     * by the names of the matching entities on the client.
     *
     * @see org.spongepowered.api.text.selector.Selector
     */
    interface Selector extends Text {

        /**
         * Returns the selector used in this {@link Text}.
         *
         * @return The selector of this message
         */
        @Override
        org.spongepowered.api.text.selector.Selector getContent();

        @Override
        TextBuilder.Selector builder();
    }

    /**
     * Represents a {@link Text} displaying the current player's score in an
     * objective.
     */
    interface Score extends Text {

        // TODO use Score

        /**
         * Returns the score displayed by this {@link Text}.
         *
         * @return The score in this message
         */
        @Override
        Object getContent();

        /**
         * Returns a value that is displayed instead of the real score.
         *
         * @return The value displayed instead of the real score, or
         *         {@link Optional#absent()} if the real score will be displayed
         *         instead
         */
        Optional<String> getOverride();

        @Override
        TextBuilder.Score builder();
    }

}
