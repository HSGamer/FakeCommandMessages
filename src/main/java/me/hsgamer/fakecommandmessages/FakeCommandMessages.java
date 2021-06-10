package me.hsgamer.fakecommandmessages;

import me.hsgamer.hscore.bukkit.baseplugin.BasePlugin;
import me.hsgamer.hscore.bukkit.config.BukkitConfig;
import me.hsgamer.hscore.common.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class FakeCommandMessages extends BasePlugin {
    private final BukkitConfig config = new BukkitConfig(this, "config.yml");
    private final List<MessageNode> messageNodes = new LinkedList<>();

    @Override
    public void load() {
        config.setup();
        loadMessageNodes();
    }

    @Override
    public void enable() {
        registerListener(new CommandListener(this));
    }

    @Override
    public void disable() {
        messageNodes.clear();
    }

    private void loadMessageNodes() {
        config.getNormalizedValues(false).forEach((s, o) -> {
            if (!(o instanceof Map)) return;
            // noinspection unchecked
            Map<String, Object> map = (Map<String, Object>) o;
            if (!map.containsKey("commands")) return;

            MessageNode node = new MessageNode();
            node.addCommands(CollectionUtils.createStringListFromObject(map.get("commands"), false));

            if (map.containsKey("messages")) {
                node.addMessages(CollectionUtils.createStringListFromObject(map.get("messages"), false));
            }

            messageNodes.add(node);
        });
    }

    public List<MessageNode> getMessageNodes() {
        return messageNodes;
    }
}
