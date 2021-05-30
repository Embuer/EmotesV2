package de.embuer.emotes.player;

import com.velocitypowered.api.proxy.Player;

public class LaborPlayer {

    protected boolean hasPack;
    protected final Player player;

    public LaborPlayer(Player player) {
        this.player = player;
        this.hasPack = false;
    }

    public boolean hasPack() {
        return hasPack;
    }

    public void validatePackActivation() {
        this.hasPack = true;
    }

    public Player getPlayer() {
        return player;
    }
}
