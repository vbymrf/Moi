package sample.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Klient {
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    public Klient(Socket socket) {
        try {
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    while (true) {
                        String msg = in.readUTF();
                        System.out.println("client: " + msg);
                        sendMsg("echo: " + msg);
                        if (msg.equals("/end")) {
                            System.out.println("clients disconnected");
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            Thread kl = new Thread(() -> {
                while (true) {
                    Scanner kl_send = new Scanner(System.in);
                    String send = kl_send.nextLine();
                    sendMsg("server: " + send);
                }

            });
            kl.setDaemon(true);
            kl.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
