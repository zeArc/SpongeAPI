package org.spongepowered.api.util.channel;

import org.spongepowered.api.text.message.Message;

/**
 * Something that receives messages.
 */
public interface Recipient {

    /**
     * Sends a message to this Receiver.
     *
     * @param sender The sender of the message
     * @param message The message to send
     */
    void send(Sender sender, Message message);

}
