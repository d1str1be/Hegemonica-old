package com.hegemonica.game.server.net;

import com.hegemonica.game.net.client.ServerConnection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection {
    private final Socket socket;
    
    
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    
    private ServerConnection serverConnection;
    
    public ClientConnection(Socket socket) {
        this.socket = socket;
        
    }
    
    public boolean establish() {
        try {
            this.dataInputStream = new DataInputStream(this.socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void cancel() {
        try {
            this.socket.close();
            this.dataInputStream.close();
            this.dataOutputStream.close();
        } catch (Exception ignored) {
        }
    }
    
    public Socket getSocket() {
        return socket;
    }
    
    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }
    
    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }
}
