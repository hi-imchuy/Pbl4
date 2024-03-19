package com.demo.controller;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import com.demo.AI.HandlerMessage;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ServerEndpoint("/websocket")
public class WebSocket {
    private static final Map<Session, Integer> sessionUserMap = new ConcurrentHashMap<>();
    private static final Map<Integer, Session> userSessionMap = new ConcurrentHashMap<>();
    private static final ExecutorService messageProcessor = Executors.newFixedThreadPool(10);

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        try {
            Map<String, List<String>> params = session.getRequestParameterMap();
            int userID = Integer.parseInt(params.get("userID").get(0));
            sessionUserMap.put(session, userID);
            userSessionMap.put(userID, session);
            System.err.println("have new websocket: " + userID);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // chuyển tới trang /logout
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        messageProcessor.submit(() -> {
            Integer userID = sessionUserMap.get(session);
            System.err.println(userID + " " + message);
			HandlerMessage.MessageHandler(userID, message);
        });
    }

    @OnClose
    public void onClose(Session session) {
        // Xóa session khi kết nối đóng
        Integer userID = sessionUserMap.remove(session);
        userSessionMap.remove(userID);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Xử lý lỗi tại đây
        throwable.printStackTrace();
    }

    // Phương thức gửi tin nhắn đến userID cụ thể
    public static void sendMessageToUser(int userID, String message) {
    	System.err.println(message);
        Session targetSession = userSessionMap.get(userID);
        if (targetSession != null && targetSession.isOpen()) {
            try {
                targetSession.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
