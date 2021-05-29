package de.embuer.emotes.Listeners;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerResourcePackStatusEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PackListener {
    public static List<UUID> players = new ArrayList<>();
    @Subscribe(order = PostOrder.FIRST)
    public void onPlayerChat(PlayerResourcePackStatusEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        if(e.getStatus() == PlayerResourcePackStatusEvent.Status.SUCCESSFUL) {
            players.add(uuid);
        }
    }
}
