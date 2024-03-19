package com.demo.controller;

import java.io.*;
import java.net.*;

public class ServerAI {

    public static void main(String[] args) {
        int port = 8888;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept(); // Chờ kết nối từ client
                System.out.println("New client connected: " + socket.getInetAddress().getHostAddress());

                // Tạo một luồng mới để xử lý kết nối.
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (DataInputStream input = new DataInputStream(socket.getInputStream())) {
            while (true) {
                String message = input.readUTF();
                if ("exit".equalsIgnoreCase(message.trim())) {
                    System.out.println("Client requested to close the connection.");
                    break;
                }
                System.out.println("Client: " + message);
            }
        } catch (IOException e) {
            System.out.println("Error in client connection: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
