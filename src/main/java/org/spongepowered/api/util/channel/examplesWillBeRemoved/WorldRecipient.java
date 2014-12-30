package org.spongepowered.api.util.channel.examplesWillBeRemoved;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.message.Message;
import org.spongepowered.api.text.message.Messages;
import org.spongepowered.api.util.channel.Recipient;
import org.spongepowered.api.util.channel.Sender;
import org.spongepowered.api.world.World;

/**
 * The default Minecraft receiver. This simply broadcasts messages to
 * the entire world.
 */
public class WorldRecipient implements Recipient {

    /**
     * The world that this receiver sends to.
     */
    private final World world;

    /**
     * Constructs a new WorldReciever given the world to send to.
     *
     * @param world The world
     */
    public WorldRecipient(World world) {
        this.world = world;
    }

    @Override
    public void send(Sender sender, Message message) {

        for (Entity entity: world.getEntities()) {
            Player player = (Player) entity;
            player.sendMessage(
                    Messages.builder()
                            .append(Messages.of("<"))
                            .append(sender.getDisplayName())
                            .append(Messages.of("> "))
                            .append(message)
                            .build()
            );
        }

    }

}
