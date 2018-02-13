package sample.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8119)) {
            System.out.println("Server started... Waiting clients...");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                System.out.println("Client connected");
                new Klient(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}