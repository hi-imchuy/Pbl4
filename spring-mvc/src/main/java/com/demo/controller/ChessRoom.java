package com.demo.controller;

import java.io.IOException;

import javax.websocket.Session;

public class ChessRoom {
    protected Session player1;
    protected Session player2;

    public ChessRoom(Session player1, Session player2) {
        this.player1 = player1;
        this.player2 = player2;
        // xác định cờ
        try {
        	System.out.println("Can try 1");
            player1.getBasicRemote().sendText("WHITE");
            player2.getBasicRemote().sendText("BLACK");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean forwardMove(Session sender, String move) throws IOException {
        @SuppressWarnings("resource")
		Session receiver = (sender == player1) ? player2 : player1;
        if("YOU LOSE".equals(move)) {
        	receiver.getBasicRemote().sendText(move);
        	return false;
        }
        System.out.println("Message from client " + receiver.getId()  + " : " +  move);
        receiver.getBasicRemote().sendText(move);
        return true;
    }
}
