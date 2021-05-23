package de.embuer.emotes.Listeners;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChatEvent;

public class ChatListener {
    @Subscribe(order = PostOrder.FIRST)
    public void onPlayerChat(PlayerChatEvent e) {
        String[] emotes = new String[] {"4Head","BRUH","D:","PauseChamp","PepeHands","pepeLaugh","PepoG","PogChamp","Sadge","tf","WeirdChamp","YEP"};
        for (String emote : emotes) {
            if (e.getMessage().contains(emote)) {
                switch (emote) {
                    case "4Head":
                        e.setResult(PlayerChatEvent.ChatResult.message(e.getMessage().replace(emote, "\uE001")));
                        break;
                    case "BRUH":
                        e.setResult(PlayerChatEvent.ChatResult.message(e.getMessage().replace(emote, "\uE002")));
                        break;
                    case "D:":
                        e.setResult(PlayerChatEvent.ChatResult.message(e.getMessage().replace(emote, "\uE003")));
                        break;
                    case "PauseChamp":
                        e.setResult(PlayerChatEvent.ChatResult.message(e.getMessage().replace(emote, "\uE004")));
                        break;
                    case "PepeHands":
                        e.setResult(PlayerChatEvent.ChatResult.message(e.getMessage().replace(emote, "\uE005")));
                        break;
                    case "pepeLaugh":
                        e.setResult(PlayerChatEvent.ChatResult.message(e.getMessage().replace(emote, "\uE006")));
                        break;
                    case "PepoG":
                        e.setResult(PlayerChatEvent.ChatResult.message(e.getMessage().replace(emote, "\uE007")));
                        break;
                    case "PogChamp":
                        e.setResult(PlayerChatEvent.ChatResult.message(e.getMessage().replace(emote, "\uE008")));
                        break;
                    case "Sadge":
                        e.setResult(PlayerChatEvent.ChatResult.message(e.getMessage().replace(emote, "\uE009")));
                        break;
                    case "tf":
                        e.setResult(PlayerChatEvent.ChatResult.message(e.getMessage().replace(emote, "\uE010")));
                        break;
                    case "WeirdChamp":
                        e.setResult(PlayerChatEvent.ChatResult.message(e.getMessage().replace(emote, "\uE011")));
                        break;
                    case "YEP":
                        e.setResult(PlayerChatEvent.ChatResult.message(e.getMessage().replace(emote, "\uE012")));
                        break;
                }
            }
        }
    }
}
