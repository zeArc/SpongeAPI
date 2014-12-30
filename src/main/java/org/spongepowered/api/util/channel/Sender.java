package org.spongepowered.api.util.channel;

import org.spongepowered.api.text.message.Message;

/**
 * Something that sends messages.
 */
public interface Sender {

    /**
     * Gets the name of this sender, possibly with formatting.
     *
     * @return The name
     */
    Message.Text getDisplayName();

    /**
     * Gets the name of this sender as a plaintext string.
     *
     * @return The name as a string
     */
    String getName();

    /**
     * Sends a message as this sender.
     *
     * @param message The message to send
     */
    void say(Message message);

}
