package com.hegemonica.game.net.client.listeners;

import com.hegemonica.game.server.net.ClientConnection;
import com.hegemonica.game.server.net.Server;

import java.io.IOException;
import java.net.Socket;

public class ConnectionListener implements Runnable {
    private final Server server;
    private Thread thread;
    private boolean running;
    
    public ConnectionListener(final Server server) {
        this.server = server;
    }
    
    public void start() {
        this.running = true;
        this.thread = new Thread(this);
        this.thread.start();
    }
    
    public void stop() {
        this.running = false;
    }
    
    @Override
    public void run() {
        while (this.running) {
            try {
                final Socket socket = this.server.getServerSocket().accept();
                final ClientConnection clientConnection = new ClientConnection(socket);
                
                if (clientConnection.establish()) {
                
                } else {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
