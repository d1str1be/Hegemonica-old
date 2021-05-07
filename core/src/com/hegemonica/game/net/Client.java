package com.hegemonica.game.net;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.Disposable;

import java.io.IOException;

public class Client implements Disposable {
    private Socket socket;
    private ClientThread thread;

    public Client() {
        thread = new ClientThread();
        thread.start();
    }

    public class ClientThread extends Thread {
        @Override
        public void run() {
            SocketHints hints = new SocketHints();
            hints.connectTimeout = 15000;
            socket = Gdx.net.newClientSocket(Net.Protocol.TCP, "local", 5037, hints);
            if (socket != null) {
                try {
                    socket.getOutputStream().write(new String("Hi  Server!").getBytes());

                    byte[] read = new byte[1024];
                    socket.getInputStream().read(read);

                    String readString = new String(read).trim();
                    System.out.println("Recieved from Server : " + readString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket.dispose();
            }
            Gdx.app.exit();
        }
    }

    @Override
    public void dispose() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (socket != null)
            socket.dispose();
    }
}
