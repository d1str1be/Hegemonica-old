package com.hegemonica.game.server.net;

import com.hegemonica.game.net.client.listeners.ConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    
    public static void main(String[] args) {
        new Server(5000).start();
    }
    
    private ServerSocket serverSocket;
    private final int port;
    
    private ConnectionListener connectionListener;
    
    public Server(final int port) {
        this.port = port;
    }
    
    
    public void start() {
        try {
            this.serverSocket = new ServerSocket(this.port);
            
            this.connectionListener = new ConnectionListener(this);
            this.connectionListener.start();
            System.out.println("Server started");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void stop() {
        try {
            this.connectionListener.stop();
            this.serverSocket.close();
        } catch (Exception ignored) {
        }
    }
    
    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
}
