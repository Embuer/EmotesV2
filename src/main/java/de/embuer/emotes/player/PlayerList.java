package de.embuer.emotes.player;

import com.velocitypowered.api.proxy.Player;

import java.util.HashMap;

public class PlayerList {

    private static final HashMap<Player, LaborPlayer> players = new HashMap<>();

    public static boolean exists(Player player) {
        return players.containsKey(player);
    }

    public static LaborPlayer get(Player player) {
        return players.get(player);
    }

    public static LaborPlayer create(Player player) {
        LaborPlayer laborPlayer = new LaborPlayer(player);
        players.put(player,laborPlayer);
        return laborPlayer;
    }

    public static void delete(Player player) {
        players.remove(player);
    }

}
