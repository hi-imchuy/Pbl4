package com.demo.AI;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;


import com.demo.controller.WebSocket;
import com.demo.dao.MoveDAO;
import com.demo.dao.UserDAO;
import com.demo.model.Move;
import com.demo.model.User;

@SuppressWarnings("unused")
public class HandlerMessage {
	private static MoveDAO moveDAO = new MoveDAO();
    private static RoomManager roomManager = new RoomManager();
    private static Queue<Integer> waitList = new LinkedList<>();
    private static Map<Integer, Integer> userGameMap = new ConcurrentHashMap<>();
    private static Map<Integer, Pair> gameUsersMap = new ConcurrentHashMap<>();

    public static void MessageHandler(int userID, String message) {
    	System.err.println("MessHandler");
        // Phân tách message để xác định loại yêu cầu
        String[] parts = message.split(" ");
        String command = parts[0];
        
        switch (command) {
            case "create":
                handleCreate(userID, parts[1]);
                break;
            case "move":
                handleMove(userID, parts[1]);
                break;
            case "draw":
                handleDrawRequest(userID);
                break;
            case "resign":
                handleResign(userID);
                break;
            case "analyze":
                handleAnalyze(userID);
                break;
            default:
            	int gameID = userGameMap.get(userID);
            	Pair userIDs = gameUsersMap.get(gameID);
            	int userID_oth = userIDs.first == userID ? userIDs.second : userIDs.first;
                WebSocket.sendMessageToUser(userID_oth, message);;
        }
    }

    public static int StartGame(int gameID, int userID) {
    	for (Map.Entry<Integer, Integer> entry : userGameMap.entrySet()) {
            if (entry.getValue().equals(gameID) && entry.getKey() != userID) {
            	return entry.getKey();
            }
        }
    	return -1;
    }
    public static int handleCreate(int userID, String type) {
    	int gameID = -1;
        if ("0".equals(type)) {
            // Tạo phòng chơi với máy
        	gameID = roomManager.createRoomWithAI(userID);
            userGameMap.put(userID, gameID);
            gameUsersMap.put(gameID, new Pair(userID, 0));
        } else if ("1".equals(type)) {
            
        	if(waitList.size() != 0) {
        		if(waitList.peek() == userID) return gameID;
        		int userID_oth = waitList.poll();
        		gameID = roomManager.createRoomWithPlayer(userID_oth, userID);
        		userGameMap.put(userID, gameID);
        		userGameMap.put(userID_oth, gameID);
        		gameUsersMap.put(gameID, new Pair(userID_oth, userID));
        	}
        	else {
        		if(waitList.contains(userID)) return gameID;
        		waitList.add(userID);
        	}
        } else if("2".equals(type)) {
        	// chơi với bạn
        }
       return gameID;
    }
    
    public static String handleGET(int gameID) {
    	String board = roomManager.getBoard(gameID);
    	
    	
    	
    	return board;
    }
    
    private static void handleMove(int userID, String move) {
        // Xử lý nước đi
    	
    	int gameID = userGameMap.get(userID);
    	Move newMove = new Move(gameID, userID, move);
    	Pair userIDs = gameUsersMap.get(gameID);
    	int userID_oth = userIDs.first == userID ? userIDs.second : userIDs.first;
        if(userID_oth != 0) {
            String move_oth =  roomManager.sendMove(gameID, move, userID);
        	System.err.println(userID_oth);
        	WebSocket.sendMessageToUser(userID_oth, move_oth);
        }
        	
        else {
        	moveDAO.addMove(newMove);
            String move_oth =  roomManager.sendMove(gameID, move, userID);
        	System.err.println(userID_oth);
        	WebSocket.sendMessageToUser(userID, move_oth);
        }
        	
    }

    private static void handleDrawRequest(int userID) {
        // Xử lý yêu cầu cầu hòa
    	int gameID = userGameMap.get(userID);
        roomManager.requestDraw(gameID, userID);
    }

    private static void handleResign(int userID) {
        // Xử lý đầu hàng
    	int gameID = userGameMap.get(userID);
        roomManager.resignGame(gameID, userID);
    }

    private static void handleAnalyze(int gameID) {
        // Phân tích trận đấu
        roomManager.analyzeGame(gameID);
    }

	public static boolean Check(int gameID) {
		return roomManager.CheckIsValidGame(gameID);
	}

	public static void PushGameIN_PROGRESS(int gameID, int player1id, int player2id, String movelist) {
		// TODO Auto-generated method stub
		roomManager.addRoom(gameID, 0, player1id, player2id, movelist);
		userGameMap.put(player1id, gameID);
		gameUsersMap.put(gameID, new Pair(player1id, player2id));
		if(player2id != 0) userGameMap.put(player2id, gameID);
	}

	public static String getLegal(int gameID) {
		return roomManager.getLegal(gameID);
	}
	
    static class Pair {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
	
}
