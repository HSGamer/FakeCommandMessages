package me.hsgamer.fakecommandmessages;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {
    private final FakeCommandMessages instance;

    public CommandListener(FakeCommandMessages instance) {
        this.instance = instance;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage().split(" ", 2)[0];

        for (MessageNode node : instance.getMessageNodes()) {
            if (!node.matchesCommand(command)) continue;

            event.setCancelled(true);
            node.getMessages().forEach(player::sendMessage);
        }
    }
}
