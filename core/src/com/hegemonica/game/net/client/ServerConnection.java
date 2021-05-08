package com.hegemonica.game.net.client;

import com.hegemonica.game.net.client.listeners.DataListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ServerConnection {
    
    private final String host;
    private final int port;
    
    private Socket socket;
    
    
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    
    private DataListener dataListener;
    
    public ServerConnection(final String host, final int port) {
        this.host = host;
        this.port = port;
    }
    
    public boolean establish() {
        try {
            this.socket = new Socket(host, port);
            this.dataInputStream = new DataInputStream(this.socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
            
            this.dataListener = new DataListener(this);
            this.dataListener.start();
            return true;
        } catch (Exception e) {
            this.cancel();
        }
        return false;
    }
    
    public void cancel() {
        try {
            this.dataListener.stop();
            this.socket.close();
            
            this.dataInputStream.close();
            this.dataOutputStream.close();
            
        } catch (Exception ignored) {
        }
    }
    
    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }
    
    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }
    
    public Socket getSocket() {
        return socket;
    }
}
