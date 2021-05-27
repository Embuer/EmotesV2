package de.embuer.emotes.Listeners;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChatEvent;

public class ChatListener {
    @Subscribe(order = PostOrder.FIRST)
    public void onPlayerChat(PlayerChatEvent e) {
        String[] emoteStrings = new String[] {"4Head",  "BRUH",   "D:",     "PauseChamp", "PepeHands", "pepeLaugh", "PepoG",  "PogChamp", "Sadge",  "tf",     "WeirdChamp", "YEP"};
        String[] replacements = new String[] {"\uE001", "\uE002", "\uE003", "\uE004",     "\uE005",    "\uE006",    "\uE007", "\uE008",   "\uE009", "\uE010", "\uE011",     "\uE012"};
        for (int x=0; x<emoteStrings.length; x++) {
            e.setResult(PlayerChatEvent.ChatResult.message(e.getMessage().replaceAll(emoteStrings[x], replacements[x])));
        }
    }
}
