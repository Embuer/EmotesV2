package de.embuer.emotes.listener;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.proxy.Player;
import de.embuer.emotes.HGLaborEmotes;
import de.embuer.emotes.player.LaborPlayer;
import de.embuer.emotes.player.PlayerList;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.function.Consumer;

public class ChatListener {

    @Subscribe(order = PostOrder.FIRST)
    public void onPlayerChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        if (PlayerList.exists(player) && PlayerList.get(player).hasPack()) {
            event.setResult(PlayerChatEvent.ChatResult.denied());
            LaborPlayer laborPlayer = PlayerList.get(player);
            for (Player it : HGLaborEmotes.server.getAllPlayers()) {
                Component replace = Component.text(event.getMessage());
                String message = event.getMessage();
                for (String emote : HGLaborEmotes.getConfig().getEmotes().keySet()) {
                    String[] arr = message.split(" ");
                     if (message.equals(emote)) {
                        replace = (Component.text(HGLaborEmotes.getConfig().getEmotes().get(emote)).append(translate(""))).hoverEvent(HoverEvent.showText(Component.text(emote)));
                    }else {
                         for (String ss : arr) {
                             if (ss.equals(emote)) {
                                 TextComponent tc = (Component.text(HGLaborEmotes.getConfig().getEmotes().get(emote)).append(translate(""))).hoverEvent(HoverEvent.showText(Component.text(emote)));
                                 replace = replace.replaceText(emote, tc);
                             }
                         }
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
    public
    net.kyori.adventure.text.TextComponent translate(String s) {
        return LegacyComponentSerializer.legacySection().deserialize(s);
    }
}