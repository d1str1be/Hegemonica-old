package com.hegemonica.game.net.client;

public class MessageSender {
    public static MessageSender INSTANCE = new MessageSender();
    
    public void sendMessage(final String msg, final ServerConnection serverConnection) {
        try {
            serverConnection.getDataOutputStream().writeByte(1);
            serverConnection.getDataOutputStream().writeUTF("Test");
            serverConnection.getDataOutputStream().writeUTF(msg);
            serverConnection.getDataOutputStream().flush();
        } catch (Exception e) {
            System.out.println("Server Connection cancelled");
            serverConnection.cancel();
        }
    }
    
}
