package com.hegemonica.game.server.net;

import java.util.HashMap;
import java.util.Map;

public class PlayerHandler {
    public static PlayerHandler INSTANCE = new PlayerHandler();
    
    
    private final Map<ClientConnection, Player> players;
    
    public PlayerHandler() {
        this.players = new HashMap<>();
    }
    
    public void addPlayer(final ClientConnection clientConnection, final String username) {
        if (this.players.containsKey(clientConnection)) return;
        
        final Player player = new Player(username);
        this.players.put(clientConnection, player);
    }
    
    public void removePlayer(final ClientConnection clientConnection) {
        this.players.remove(clientConnection);
    }
    
    public Map<ClientConnection, Player> getPlayers() {
        return players;
    }
}
