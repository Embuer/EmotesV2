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
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import de.embuer.emotes.config.EmotesConfig;
import de.embuer.emotes.listener.ChatListener;
import de.embuer.emotes.listener.JoinQuitListener;
import de.embuer.emotes.listener.PackListener;
import de.embuer.emotes.player.PlayerList;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.checkerframework.checker.units.qual.K;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

@Plugin(
        id = "emotes",
        name = "Emotes",
        version = "1.0-SNAPSHOT",
        description = "Adds Twitch Emotes to Minecraft",
        authors = {"Embuer"}
)
public class HGLaborEmotes {

    public static ProxyServer server;
    private static EmotesConfig config;
    private final CommandManager commandManager;

    @Inject
    public HGLaborEmotes(ProxyServer server, CommandManager commandManager, @DataDirectory Path dataDirectoryPath) {
        HGLaborEmotes.server = server;
        this.commandManager = commandManager;
        config = new EmotesConfig().load(dataDirectoryPath);
    }

    public static EmotesConfig getConfig() {
        return config;
    }

    public void createEmotesCommand() {
        LiteralCommandNode<CommandSource> emotes = LiteralArgumentBuilder
                .<CommandSource>literal("emotes")
                .requires(commandSource -> commandSource instanceof Player)
                .executes(context -> {
                    Player player = (Player) context.getSource();
                    if (PlayerList.exists(player)) {
                        if (PlayerList.exists(player) && PlayerList.get(player).hasPack()) {
                            ArrayList<String> feedback = new ArrayList<>();
                            Iterator<String> itr = config.getEmotes().keySet().iterator();
                            while (itr.hasNext()) {
                                String key = itr.next();
                                String value = config.getEmotes().get(key);

                                feedback.add(key + " " + value);
                            }
                            Component message = Component.text("Available Emotes are: " + feedback, NamedTextColor.WHITE);
                            context.getSource().sendMessage(message);
                        } else {
                            player.sendResourcePack(HGLaborEmotes.getConfig().getPacklink().get("packlink"));
                        }
                    }

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
        server.getEventManager().register(this, new JoinQuitListener());
        createEmotesCommand();
    }
}