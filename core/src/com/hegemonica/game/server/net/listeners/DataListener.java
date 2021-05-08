package com.hegemonica.game.server.net.listeners;

import com.hegemonica.game.HegeLog;
import com.hegemonica.game.server.net.ClientConnection;
import com.hegemonica.game.server.net.MessageHandler;
import com.hegemonica.game.server.net.PlayerHandler;

public class DataListener implements Runnable {
    
    private final ClientConnection clientConnection;
    private boolean running;
    
    
    public DataListener(ClientConnection clientConnection) {
        this.clientConnection = clientConnection;
    }
    
    public void start() {
        this.running = true;
        Thread thread = new Thread(this);
        thread.start();
    }
    
    public void stop() {
        this.running = false;
    }
    
    @Override
    public void run() {
        while (this.running) {
            try {
                byte packetID = this.clientConnection.getDataInputStream().readByte();
                
                if (packetID == 0) {
                    String username = this.clientConnection.getDataInputStream().readUTF();
                    PlayerHandler.INSTANCE.addPlayer(this.clientConnection, username);
                }
                if (packetID == 1) {
                    String msg = this.clientConnection.getDataInputStream().readUTF();
    
                    MessageHandler.INSTANCE.sendMessageToAll(PlayerHandler.INSTANCE.getPlayers().get(this.clientConnection), msg);
                }
            } catch (Exception e) {
                HegeLog.log("Client Connection", "Got exception, closing client");
                this.clientConnection.cancel();
            }
        }
    }
}
