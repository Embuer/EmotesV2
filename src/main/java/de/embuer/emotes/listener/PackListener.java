package de.embuer.emotes.listener;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerResourcePackStatusEvent;
import de.embuer.emotes.player.LaborPlayer;
import de.embuer.emotes.player.PlayerList;

public class  PackListener {

    @Subscribe(order = PostOrder.FIRST)
    public void onPlayerChat(PlayerResourcePackStatusEvent event) {
        if(event.getStatus() == PlayerResourcePackStatusEvent.Status.SUCCESSFUL) {
            LaborPlayer laborPlayer = PlayerList.get(event.getPlayer());
            laborPlayer.validatePackActivation();
        }
    }
}
