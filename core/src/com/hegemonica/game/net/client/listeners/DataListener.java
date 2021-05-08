package com.hegemonica.game.net.client.listeners;

import com.hegemonica.game.HegeLog;
import com.hegemonica.game.net.client.Message;
import com.hegemonica.game.net.client.ServerConnection;
import com.hegemonica.game.server.net.MessageHandler;

public class DataListener implements Runnable {
    
    
    private final ServerConnection serverConnection;
    private boolean running;
    
    
    public DataListener(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
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
                final byte packetID = this.serverConnection.getDataInputStream().readByte();
                
                if (packetID == 0) {
                    String username = this.serverConnection.getDataInputStream().readUTF();
                    String msg = this.serverConnection.getDataInputStream().readUTF();
    
                    MessageHandler.INSTANCE.getMessages().add(new Message(username, msg))
                }
                if (packetID == 1) {
                    String msg = this.serverConnection.getDataInputStream().readUTF();
                }
            } catch (Exception e) {
                HegeLog.log("Client Connection", "Got exception, closing client");
                this.serverConnection.cancel();
            }
        }
    }
}
