package org.spongepowered.api.text;

import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;

/**
 * A utility class that adds some shorthands for constructing {@link Text} objects.
 *
 * <p>While some methods are simply proxies or are one-liners, chained together
 * they save a lot of character space, and can be statically imported.</p>
 */
public final class Txt {

    /**
     * Joins a sequence of text objects together.
     *
     * @param texts The texts object
     * @return A text object that joins the given text objects
     */
    public static Text join(Text... texts) {
        return Texts.builder().append(texts).build();
    }

    /**
     * Proxies to {@link Texts#of(String)}.
     *
     * @param content The content
     * @return the constructed text object
     */
    public static Text of(String content) {
        return Texts.of(content);
    }

    /**
     * Builds a Text object from a given array of objects.
     *
     * <p>For instance, you can use this like
     * <code>Txt.of(TextColors.DARK_AQUA, "Hi", TextColors.AQUA, "Bye")</code>
     * </p>
     *
     * @param objects The object array
     * @throws IllegalArgumentException If a passed-in argument is not of type TextColor, String or Text
     * @return The built text object
     */
    public static Text of(Object... objects) throws IllegalArgumentException {
        TextBuilder builder = Texts.builder();
        TextColor color = TextColors.NONE;
        for (Object obj: objects) {
            if (obj instanceof TextColor) {
                color = (TextColor) obj;
            } else if (obj instanceof String) {
                builder.append(Texts.builder((String) obj).color(color).build());
            } else if (obj instanceof Text) {
                builder.append((Text) obj);
            } else {
                throw new IllegalArgumentException("Unexpected type for object " + obj);
            }
        }
        return builder.build();
    }

    /**
     * Constructs a Text object colored black.
     *
     * @param content The content of the text
     * @return The constructed text object
     */
    public static Text black(String content) {
        return Texts.builder(content).color(TextColors.BLACK).build();
    }

    /**
     * Constructs a Text object colored dark blue.
     *
     * @param content The content of the text
     * @return The constructed text object
     */
    public static Text darkBlue(String content) {
        return Texts.builder(content).color(TextColors.DARK_BLUE).build();
    }

    /**
     * Constructs a Text object colored dark green.
     *
     * @param content The content of the text
     * @return The constructed text object
     */
    public static Text darkGreen(String content) {
        return Texts.builder(content).color(TextColors.DARK_GREEN).build();
    }

    /**
     * Constructs a Text object colored dark aqua.
     *
     * @param content The content of the text
     * @return The constructed text object
     */
    public static Text darkAqua(String content) {
        return Texts.builder(content).color(TextColors.DARK_AQUA).build();
    }

    /**
     * Constructs a Text object colored dark red.
     *
     * @param content The content of the text
     * @return The constructed text object
     */
    public static Text darkRed(String content) {
        return Texts.builder(content).color(TextColors.DARK_RED).build();
    }

    /**
     * Constructs a Text object colored dark purple.
     *
     * @param content The content of the text
     * @return The constructed text object
     */
    public static Text darkPurple(String content) {
        return Texts.builder(content).color(TextColors.DARK_PURPLE).build();
    }

    /**
     * Constructs a Text object colored gold.
     *
     * @param content The content of the text
     * @return The constructed text object
     */
    public static Text gold(String content) {
        return Texts.builder(content).color(TextColors.GOLD).build();
    }

    /**
     * Constructs a Text object colored gray.
     *
     * @param content The content of the text
     * @return The constructed text object
     */
    public static Text gray(String content) {
        return Texts.builder(content).color(TextColors.GRAY).build();
    }

    /**
     * Constructs a Text object colored dark gray.
     *
     * @param content The content of the text
     * @return The constructed text object
     */
    public static Text darkGray(String content) {
        return Texts.builder(content).color(TextColors.DARK_GRAY).build();
    }

    /**
     * Constructs a Text object colored blue.
     *
     * @param content The content of the text
     * @return The constructed text object
     */
    public static Text blue(String content) {
        return Texts.builder(content).color(TextColors.BLUE).build();
    }

    /**
     * Constructs a Text object colored green.
     *
     * @param content The content of the text
     * @return The constructed text object
     */
    public static Text green(String content) {
        return Texts.builder(content).color(TextColors.GREEN).build();
    }

    /**
     * Constructs a Text object colored aqua.
     *
     * @param content The content of the text
     * @return The constructed text object
     */
    public static Text aqua(String content) {
        return Texts.builder(content).color(TextColors.AQUA).build();
    }

    /**
     * Constructs a Text object colored red.
     *
     * @param content The content of the text
     * @return The constructed text object
     */
    public static Text red(String content) {
        return Texts.builder(content).color(TextColors.RED).build();
    }

    /**
     * Constructs a Text object colored light purple.
     *
     * @param content The content of the text
     * @return The constructed text object
     */
    public static Text lightPurple(String content) {
        return Texts.builder(content).color(TextColors.LIGHT_PURPLE).build();
    }

    /**
     * Constructs a Text object colored yellow.
     *
     * @param content The content of the text
     * @return The constructed text object
     */
    public static Text yellow(String content) {
        return Texts.builder(content).color(TextColors.YELLOW).build();
    }

    /**
     * Constructs a Text object colored white.
     *
     * @param content The content of the text
     * @return The constructed text object
     */
    public static Text white(String content) {
        return Texts.builder(content).color(TextColors.WHITE).build();
    }

}
