package com.hegemonica.game.server.net;

import com.hegemonica.game.net.client.Message;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MessageHandler {
    
    
    
    private Map<String, Message> messages = new HashMap<>();
    
    public static final MessageHandler INSTANCE = new MessageHandler();
    
    public void sendMessageToAll(Player owner, String msg) {
        for (ClientConnection clientConnection : PlayerHandler.INSTANCE.getPlayers().keySet()) {
            try {
                clientConnection.getDataOutputStream().writeByte(0);
                clientConnection.getDataOutputStream().writeUTF(owner.getUsername());
                clientConnection.getDataOutputStream().writeUTF(msg);
                clientConnection.getDataOutputStream().flush();
            } catch (IOException e) {
                clientConnection.cancel();
            }
        }
    }
    public void sendMessage(ClientConnection clientConnection, String msg) {
            try {
                clientConnection.getDataOutputStream().writeByte(0);
                clientConnection.getDataOutputStream().writeUTF("Server");
                clientConnection.getDataOutputStream().writeUTF(msg);
                clientConnection.getDataOutputStream().flush();
            } catch (IOException e) {
                clientConnection.cancel();
            }
        
    }
    public Map<String, Message> getMessages() {
        return messages;
    }
    
}
