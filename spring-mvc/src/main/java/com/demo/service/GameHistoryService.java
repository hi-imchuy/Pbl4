package com.demo.service;

import java.util.ArrayList;
import java.util.List;


import com.demo.dao.GameDAO;
import com.demo.dao.UserDAO;
import com.demo.model.Game;
import com.demo.model.GameHistory;
import com.demo.model.User;

public class GameHistoryService {

	private GameDAO gameDAO = new GameDAO();
	private UserDAO userDAO = new  UserDAO();

    public List<GameHistory> getGameHistoriesByUserId(Integer userId) {
    	System.err.println(userId);
        List<Game> games = gameDAO.getGamesByUserId(userId);
        List<GameHistory> history = new ArrayList<>();
        User user = userDAO.getUserByID(userId);
        
        for(Game game : games) {
        	System.err.println("1");
        	GameHistory gamehistory = new GameHistory();
        	gamehistory.setId(game.getGameID());
        	gamehistory.setPlayerAEloChange(user.getElo());
        	gamehistory.setPlayerAName(user.getUsername());
        	if(user.getUserID() == game.getPlayer1ID()) {
        		User user_oth = userDAO.getUserByID(game.getPlayer2ID());
            	gamehistory.setPlayerBEloChange(user_oth.getElo());
            	gamehistory.setPlayerBName(user_oth.getUsername());
            	gamehistory.setResult(game.getOutcome().toString());
        	}
        	else {
        		User user_oth = userDAO.getUserByID(game.getPlayer1ID());
        		gamehistory.setPlayerBEloChange(user_oth.getElo());
            	gamehistory.setPlayerBName(user_oth.getUsername());
            	String res = game.getOutcome().toString();
            	gamehistory.setResult(res == "WIN" ? "LOSE" : (res == "LOSE" ? "WIN" : res));
            	gamehistory.swapIcon();
        	}
        	history.add(gamehistory);
        }
        
        return history;
    }
}