package de.embuer.emotes.Listeners;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.proxy.Player;
import de.embuer.emotes.Main;
import net.kyori.adventure.text.Component;

import java.util.UUID;

public class ChatListener {

    @Subscribe(order = PostOrder.FIRST)
    public void onPlayerChat(PlayerChatEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        if (PackListener.players.contains(uuid)) {
            for (Player p: Main.server.getAllPlayers()) {
                Component replace = Component.text(e.getMessage());
                String message = e.getMessage();
                String[] emoteStrings = new String[] {"4Head",  "BRUH",   "D:",     "PauseChamp", "PepeHands", "pepeLaugh", "PepoG",  "PogChamp", "Sadge",  "tf",     "WeirdChamp", "YEP"};
                String[] replacements = new String[] {"\uE001", "\uE002", "\uE003", "\uE004",     "\uE005",    "\uE006",    "\uE007", "\uE008",   "\uE009", "\uE010", "\uE011",     "\uE012"};
                for (int x = 0; x < emoteStrings.length; x++) {
                    if (message.contains(emoteStrings[x])) {
                        replace = Component.text(e.getMessage().replaceAll(emoteStrings[x], replacements[x]));
                        System.out.println(e.getMessage().replaceAll(emoteStrings[x], replacements[x]));
                    }
                }
                if (PackListener.players.contains(p.getUniqueId())) {
                    p.sendMessage(replace);
                } else {
                    p.sendMessage(Component.text(e.getMessage()));
                }
            }
        }
    }
}