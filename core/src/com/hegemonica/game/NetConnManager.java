package com.hegemonica.game;

import com.hegemonica.game.net.Client;
import com.hegemonica.game.net.Server;

public class NetConnManager {

    public Client client;
    public Server server;

    public NetConnManager() {
        if (Core.IS_SERVER)
            server = new Server();
        else
            client = new Client();
    }
}
