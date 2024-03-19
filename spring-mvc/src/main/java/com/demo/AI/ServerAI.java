package com.demo.AI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import com.demo.model.ServerStatus;

public class ServerAI {
    private static final int PORT = 8888;
    private static ConcurrentLinkedQueue<ClientConnection> availablePythonServers = new ConcurrentLinkedQueue<>();
    private static ConcurrentHashMap<ClientConnection, ServerStatus> serverStatusMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                String newServerName = socket.getInetAddress().getHostAddress();
                System.out.println("Connected to: " + newServerName);

                ClientConnection connection = new ClientConnection(socket);
                addAvailablePythonServer(connection);

                // Check if a server with this name already exists
                ServerStatus existingServer = findServerByName(newServerName);
                if (existingServer != null) {
                    // Server exists, update its status and connection
                    //existingServer.setStatus("connected");
                    //existingServer.setConnection(connection);
                    //serverStatusMap.put(connection, existingServer);
                } else {
                    // Create and add new server status
                    ServerStatus newServer = new ServerStatus();
                    newServer.setServerName(newServerName);
                    newServer.setConnection(connection);
                    newServer.setGamePVE(0);
                    newServer.setGamePVP(0);
                    newServer.setStatus("connected");
                    serverStatusMap.put(connection, newServer);
                }
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Method to find a server by its name
    private static ServerStatus findServerByName(String serverName) {
        for (ServerStatus status : serverStatusMap.values()) {
            if (status.getServerName().equals(serverName)) {
            	status.setStatus("connected");
                return status;
            }
        }
        return null;
    }

    public static List<ServerStatus> getAllStatus() {
        return new ArrayList<>(serverStatusMap.values());
    }
    
    public static void updatePVE(ClientConnection connection, int value) {
        ServerStatus serverS = serverStatusMap.get(connection);
        if (serverS != null) {
            serverS.setGamePVE(serverS.getGamePVE() + value);
            serverStatusMap.put(connection, serverS);
        } else {
            System.out.println("Connection not found in serverStatusMap.");
        }
    }
    public static void updatePVP(ClientConnection connection, int value) {
        ServerStatus serverS = serverStatusMap.get(connection);
        if (serverS != null) {
            serverS.setGamePVP(serverS.getGamePVP() + value);
            serverStatusMap.put(connection, serverS);
        } else {
            System.out.println("Connection not found in serverStatusMap.");
        }
    }
    public static void updateStatus(ClientConnection connection, String status) {
        ServerStatus serverS = serverStatusMap.get(connection);
        if (serverS != null) {
            serverS.setStatus(status);
            serverStatusMap.put(connection, serverS);
        } else {
            System.out.println("Connection not found in serverStatusMap.");
        }
    }
    
    public static synchronized void addAvailablePythonServer(ClientConnection connection) {
        availablePythonServers.add(connection);
        System.out.println("Python server added. Total available: " + availablePythonServers.size());
    }

   
    public static synchronized void removeAvailablePythonServer(ClientConnection connection) {
        availablePythonServers.remove(connection);
        System.out.println("Python server removed. Total available: " + availablePythonServers.size());
        ServerStatus serverS = serverStatusMap.get(connection);
        serverS.setStatus("disconnect");
    }

    
    public static synchronized ClientConnection getAvailablePythonServer() {
    	ClientConnection connection = availablePythonServers.poll();
    	availablePythonServers.add(connection);
        return connection;
    }
}
