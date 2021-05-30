package de.embuer.emotes.listener;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.proxy.Player;
import de.embuer.emotes.HGLaborEmotes;
import de.embuer.emotes.player.LaborPlayer;
import de.embuer.emotes.player.PlayerList;
import net.kyori.adventure.text.Component;

public class ChatListener {

    @Subscribe(order = PostOrder.FIRST)
    public void onPlayerChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        if (PlayerList.exists(player) && PlayerList.get(player).hasPack()) {
            LaborPlayer laborPlayer = PlayerList.get(player);
            for (Player it : HGLaborEmotes.server.getAllPlayers()) {
                Component replace = Component.text(event.getMessage());
                String message = event.getMessage();
                for (String emote : HGLaborEmotes.getConfig().getEmotes().keySet()) {
                    if (message.contains(" " + emote + " ") || message.contains(" " + emote) || message.equals(emote)) {
                        replace = Component.text(event.getMessage().replaceAll(emote, HGLaborEmotes.getConfig().getEmotes().get(emote)));
                    }
                }
                if (PlayerList.exists(it) && PlayerList.get(it).hasPack()) {
                    it.sendMessage(replace);
                } else {
                    it.sendMessage(Component.text(event.getMessage()));
                }
            }
        }
    }
}