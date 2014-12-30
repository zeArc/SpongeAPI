package org.spongepowered.api.util.channel.examplesWillBeRemoved;

import org.spongepowered.api.text.message.Message;
import org.spongepowered.api.util.channel.Recipient;
import org.spongepowered.api.util.channel.Sender;

import java.util.Collection;

/**
 * An example for how a private conversation would look.
 */
public class PrivateConversation implements Recipient {

    /**
     * The participants of this conversation
     */
    private final Collection<Recipient> participants;

    public PrivateConversation(Collection<Recipient> participants) {
        this.participants = participants;
    }

    @Override
    public void send(Sender sender, Message message) {
        for (Recipient participant : participants) {
            participant.send(sender, message);
        }
    }

}
