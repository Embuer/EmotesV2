package de.embuer.emotes.listener;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.LoginEvent;
import de.embuer.emotes.player.PlayerList;

public class JoinQuitListener {

    @Subscribe(order = PostOrder.FIRST)
    public void onPlayerChat(DisconnectEvent event) {
        PlayerList.delete(event.getPlayer());
    }

    @Subscribe(order = PostOrder.FIRST)
    public void onJoin(LoginEvent event) {
        PlayerList.create(event.getPlayer());
    }
}
