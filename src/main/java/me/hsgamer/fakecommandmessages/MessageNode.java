package me.hsgamer.fakecommandmessages;

import org.bukkit.ChatColor;

import java.util.*;

public class MessageNode {
    private final List<String> catchCommands = new ArrayList<>();
    private final List<String> messages = new ArrayList<>();

    public void addCommands(String... commands) {
        this.addCommands(Arrays.asList(commands));
    }

    public void addCommands(Collection<String> commands) {
        this.catchCommands.addAll(commands);
        this.catchCommands.replaceAll(s -> s.toUpperCase(Locale.ROOT));
    }

    public void addMessages(String... messages) {
        this.addMessages(Arrays.asList(messages));
    }

    public void addMessages(Collection<String> messages) {
        this.messages.addAll(messages);
        this.messages.replaceAll(s -> ChatColor.translateAlternateColorCodes('&', s));
    }

    public boolean matchesCommand(String command) {
        return catchCommands.contains(command.toUpperCase(Locale.ROOT));
    }

    public List<String> getMessages() {
        return messages;
    }

    public List<String> getCatchCommands() {
        return catchCommands;
    }
}
