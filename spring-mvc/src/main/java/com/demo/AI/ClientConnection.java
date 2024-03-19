package com.demo.AI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection {
    private final Socket socket;
    private final PrintWriter output;
    private final BufferedReader input;
    public ClientConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.output = new PrintWriter(socket.getOutputStream(), true);
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public String sendMessage(String message) throws IOException {
        if (message != null) {
            output.println(message);
            String response = input.readLine();
            System.err.println(response);
            return response; // Trả về phản hồi
        }
        return null;
    }

    public void close() throws IOException {
    	//ServerAI.removeAvailablePythonServer();
        if (input != null) {
            input.close();
        }
        if (output != null) {
            output.close();
        }
        if (socket != null) {
            socket.close();
        }
    }

	
}
