package de.embuer.emotes;

import com.google.inject.Inject;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import de.embuer.emotes.Listeners.ChatListener;
import de.embuer.emotes.Listeners.LeaveListener;
import de.embuer.emotes.Listeners.PackListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Plugin(
        id = "emotes",
        name = "Emotes",
        version = "1.0-SNAPSHOT",
        description = "Adds Emotes to Minecraft",
        authors = {"Embuer"}
)
public class Main {
    public static ProxyServer server;
    private final CommandManager commandManager;

    @Inject
    public Main(ProxyServer server, CommandManager commandManager) {
        Main.server = server;
        this.commandManager = commandManager;
    }
    public void createLoadEmotesCommand() {
        LiteralCommandNode<CommandSource> loademotes = LiteralArgumentBuilder
                .<CommandSource>literal("loademotes")
                .requires(commandSource -> commandSource instanceof Player)
                .executes(context -> {
                    Player p = (Player) context.getSource();
                    p.sendResourcePack("https://dl.dropboxusercontent.com/s/ia2rbz1rxcos3r4/Emotes.zip?dl=0");
                    return 1;
                })
                .build();

        BrigadierCommand loademotescommand = new BrigadierCommand(loademotes);
        commandManager.register(loademotescommand);
    }

    public void createEmotesCommand() {
        LiteralCommandNode<CommandSource> emotes = LiteralArgumentBuilder
                .<CommandSource>literal("emotes")
                .requires(commandSource -> commandSource instanceof Player)
                .executes(context -> {
                    Component message = Component.text("Available Emotes are: 4Head BRUH D: PauseChamp PepeHands pepeLaugh PepoG PogChamp Sadge tf WeirdChamp YEP", NamedTextColor.WHITE);
                    context.getSource().sendMessage(message);
                    return 1;
                })
                .build();

        BrigadierCommand emotescommand = new BrigadierCommand(emotes);
        commandManager.register(emotescommand);
    }

    @Subscribe
    public void onInitialize(ProxyInitializeEvent event) {
        server.getEventManager().register(this, new ChatListener());
        server.getEventManager().register(this, new PackListener());
        server.getEventManager().register(this, new LeaveListener());
        createEmotesCommand();
        createLoadEmotesCommand();
    }
}