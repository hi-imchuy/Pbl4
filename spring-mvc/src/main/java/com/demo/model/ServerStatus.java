package com.demo.model;

import com.demo.AI.ClientConnection;

public class ServerStatus {
    private String serverName;
    private int gamePVP;
    private int gamePVE;
    private transient  ClientConnection connection;
    private String status;

    // Constructor
    public ServerStatus() {
    }

    // Setters
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setGamePVP(int gamePVP) {
        this.gamePVP = gamePVP;
    }

    public void setGamePVE(int gamePVE) {
        this.gamePVE = gamePVE;
    }

    public void setConnection(ClientConnection connection) {
        this.connection = connection;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getters
    public String getServerName() {
        return serverName;
    }

    public int getGamePVP() {
        return gamePVP;
    }

    public int getGamePVE() {
        return gamePVE;
    }

    public ClientConnection getConnection() {
        return connection;
    }

    public String getStatus() {
        return status;
    }

    // Override toString() method if needed for debugging
    @Override
    public String toString() {
        return "Server{" +
                "serverName='" + serverName + '\'' +
                ", gamePVP=" + gamePVP +
                ", gamePVE=" + gamePVE +
                ", connection=" + connection +
                ", status='" + status + '\'' +
                '}';
    }
}
