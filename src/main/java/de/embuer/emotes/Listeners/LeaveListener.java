package de.embuer.emotes.Listeners;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;


import java.util.UUID;

public class LeaveListener {
    @Subscribe(order = PostOrder.FIRST)
    public void onPlayerChat(DisconnectEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        PackListener.players.remove(uuid);
    }
}
