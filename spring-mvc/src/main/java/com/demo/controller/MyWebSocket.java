package com.demo.controller;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@ServerEndpoint("/websocket")
public class MyWebSocket {

    private static Map<String, Session> waitingPlayers = new ConcurrentHashMap<>();
    private static Map<String, ChessRoom> activeRooms = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("New connection with client: " + session.getId());
    }

    @SuppressWarnings("unused")
	@OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("Message from client " + session.getId() + ": " + message);
       
        if ("play".equals(message)) {
            if (waitingPlayers.isEmpty() || waitingPlayers.containsKey(session.getId())) {
                if(!activeRooms.containsKey(session.getId()))
                	waitingPlayers.put(session.getId(), session);
                	Client nclient = new Client("localhost", 8888);
            } else {
            	
                String opponentId = waitingPlayers.keySet().iterator().next();
                Session opponentSession = waitingPlayers.remove(opponentId);
                
                ChessRoom room = new ChessRoom(session, opponentSession);
                activeRooms.put(session.getId(), room);
                activeRooms.put(opponentSession.getId(), room);
            }
        } else {
        	
            ChessRoom room = activeRooms.get(session.getId());
            if (room != null) {
                if(!room.forwardMove(session, message)) {
                	activeRooms.remove(room.player1.getId());
                	activeRooms.remove(room.player2.getId());
                }
            }
        }
        //System.out.println("Size of waitting: " + waitingPlayers.size());
        //System.out.println("Size of activeRoom: " + activeRooms.size());
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Closed connection with client: " + session.getId());
        waitingPlayers.remove(session.getId());
        activeRooms.remove(session.getId());
    }
}
