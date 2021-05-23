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
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

@Plugin(
        id = "emotes",
        name = "Emotes",
        version = "1.0-SNAPSHOT",
        description = "Adds Emotes to Minecraft",
        authors = {"Embuer"}
)
public class Main {
    private final ProxyServer server;
    private final CommandManager commandManager;

    @Inject
    public Main(ProxyServer server, CommandManager commandManager) {
        this.server = server;
        this.commandManager = commandManager;
    }
    public void createBrigadierCommand() {
        LiteralCommandNode<CommandSource> helloNode1 = LiteralArgumentBuilder
                .<CommandSource>literal("loademotes")
                .requires(commandSource -> commandSource instanceof Player)
                .executes(context -> {
                    Player p = (Player) context.getSource();
                    p.sendResourcePack("https://dl.dropboxusercontent.com/s/ia2rbz1rxcos3r4/Emotes.zip?dl=0");
                    return 1;
                })
                .build();

        BrigadierCommand command1 = new BrigadierCommand(helloNode1);
        commandManager.register(command1);
    }

    public void createBrigadierCommand1() {
        LiteralCommandNode<CommandSource> helloNode = LiteralArgumentBuilder
                .<CommandSource>literal("emotes")
                .requires(commandSource -> commandSource instanceof Player)
                .executes(context -> {
                    Component message = Component.text("Available Emotes are: 4Head BRUH D: PauseChamp PepeHands pepeLaugh PepoG PogChamp Sadge tf WeirdChamp YEP", NamedTextColor.WHITE);
                    context.getSource().sendMessage(message);
                    return 1;
                })
                .build();

        BrigadierCommand command = new BrigadierCommand(helloNode);
        commandManager.register(command);
    }

    @Subscribe
    public void onInitialize(ProxyInitializeEvent event) {
        server.getEventManager().register(this, new ChatListener());
        createBrigadierCommand();
        createBrigadierCommand1();
    }
}