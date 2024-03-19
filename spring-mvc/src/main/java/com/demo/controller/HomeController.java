package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.AI.HandlerMessage;
import com.demo.AI.RoomManager;
import com.demo.AI.ServerAI;
import com.demo.dao.GameDAO;
import com.demo.dao.MoveDAO;
import com.demo.dao.UserDAO;
import com.demo.model.Game;
import com.demo.model.Move;
import com.demo.model.ServerStatus;
import com.demo.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
	UserDAO userDAO = new UserDAO();
	MoveDAO moveDAO = new MoveDAO();
	GameDAO gameDAO = new GameDAO();
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView infomation() {
		return new ModelAndView("infomation");
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage(HttpServletRequest request) {
		HttpSession session = request.getSession(false); // Get the current session if it exists, but don't create a new one
		ModelAndView mav;
		if (session != null && session.getAttribute("userID") != null) {
			mav = new ModelAndView("homeLogged");
		} else {
			mav = new ModelAndView("home");
		}
		return mav;
	}
	
	@RequestMapping(value = "/analysis", method = RequestMethod.GET)
	public ModelAndView analysisPage(HttpServletRequest request, 
	                                 @RequestParam(name = "gameId", required = false) Integer gameId) {
	    HttpSession session = request.getSession(false); // Get the current session if it exists, but don't create a new one
	    if (session == null || session.getAttribute("userID") == null) {
	        return new ModelAndView("redirect:/login");
	    }

	    ModelAndView mav = new ModelAndView("analysis");
	    System.err.println(gameId);
	    if (gameId != null) {
	    	List<Move> moves = moveDAO.getMovesByGameId(gameId);
//	    	System.err.println(moves);
//	    	System.err.println(moves.get(1).getBetterMove());
	    	if (moves.get(1).getBetterMove().isEmpty()) {
	            moves = RoomManager.analysis(moves);
	        }
	    	String movesJson = new Gson().toJson(moves);
	        mav.addObject("movesJson", movesJson);
	    } else {
	        mav.addObject("error", "Game ID is invalid or not provided");
	    }
	    
	    return mav;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public ModelAndView historyPage(@RequestParam(required = false) String action, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userID") == null) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("history");
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signupPage() {
		return new ModelAndView("signup");
	}
	
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public ModelAndView managerPage(@RequestParam(required = false) String action, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userID") == null) {
			return new ModelAndView("redirect:/login");
		}
		User user = userDAO.getUserByID(Integer.parseInt(session.getAttribute("userID").toString()));
		if(!user.getUsername().equals("admin")) {
			return new ModelAndView("redirect:/home");
		}
			
		ModelAndView mav = new ModelAndView("managerPage");
		List<ServerStatus> listStatus = ServerAI.getAllStatus();
		Map<String, Integer> pvpCounts = new HashMap<>();
		Map<String, Integer> pveCounts = new HashMap<>();
		for (ServerStatus status : listStatus) {
		    pvpCounts.put(status.getServerName(), status.getGamePVP());
		    pveCounts.put(status.getServerName(), status.getGamePVE());
		}

		mav.addObject("pvpCounts", pvpCounts);
		mav.addObject("pveCounts", pveCounts);
		mav.addObject("listStatus", listStatus);
		return mav;
	}
	
	@RequestMapping(value = "/getServerData", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getServerData() {
	    List<ServerStatus> listStatus = ServerAI.getAllStatus();
	    Map<String, Integer> pvpCounts = new HashMap<>();
	    Map<String, Integer> pveCounts = new HashMap<>();
	    
	    for (ServerStatus status : listStatus) {
	        pvpCounts.put(status.getServerName(), status.getGamePVP());
	        pveCounts.put(status.getServerName(), status.getGamePVE());
	    }

	    Map<String, Object> responseData = new HashMap<>();
	    responseData.put("listStatus", listStatus);
	    responseData.put("pvpCounts", pvpCounts);
	    responseData.put("pveCounts", pveCounts);

	    return responseData;
	}

	// Đánh với máy:
	@RequestMapping(value = "/playcomputer", method = RequestMethod.GET)
	public ModelAndView playCompuer(@RequestParam(required = false) String action, HttpServletRequest request) throws JsonProcessingException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userID") == null) {
			return new ModelAndView("redirect:/login");
		}
		ModelAndView mav = new ModelAndView("play");
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		int gameID = userDAO.checkGameIN_PROGRESS(userID, 0);
		User user = userDAO.getUserByID(userID);
		ObjectMapper objectMapper = new ObjectMapper();
		String userJson = objectMapper.writeValueAsString(user);
		mav.addObject("userJson", userJson);
		System.err.println(gameID);
		if (gameID == -1) {
			System.err.println("computer");
			gameID = HandlerMessage.handleCreate(userID, "0");
			return mav;
		}
		
		List<Move> prevMove = moveDAO.getMovesByGameId(gameID);
		if(HandlerMessage.Check(gameID)) {
			System.err.println("có game id");
		}
		else {
			System.err.println("không có gameid");
			Game game = gameDAO.getGame(gameID);
			HandlerMessage.PushGameIN_PROGRESS(gameID, game.getPlayer1ID(), game.getPlayer2ID(), prevMove.toString());
		}
		mav.addObject("prevMove", prevMove);
		String legal = HandlerMessage.getLegal(gameID);
		mav.addObject("legal", legal);
		return mav;
	}
	
	// Đánh với người:
	@RequestMapping(value = "/playonline", method = RequestMethod.GET)
	public ModelAndView playOnline(@RequestParam(required = false) String action, HttpServletRequest request) throws JsonProcessingException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userID") == null) {
			return new ModelAndView("redirect:/login");
		}
		
		int userID = Integer.parseInt(session.getAttribute("userID").toString());
		ModelAndView mav;
		int gameID;
		// nếu có game pvp chưa xong tải lại:
		if(-1 != userDAO.checkGameIN_PROGRESS(userID, 1)) {
			
		}
		// nếu không :
		gameID = HandlerMessage.handleCreate(userID, "1");
		if(/*nếu có người đang đợi ==> bên đen*/ gameID != -1) {
			mav = new ModelAndView("playBlack");
			mav.addObject("Type", "NewGame");
			int userID_oth = HandlerMessage.StartGame(gameID, userID);
			User user_oth = userDAO.getUserByID(userID_oth);
			User user = userDAO.getUserByID(userID);
			ObjectMapper objectMapper = new ObjectMapper();
			String userJson = objectMapper.writeValueAsString(user);
			String userOthJson = objectMapper.writeValueAsString(user_oth);
			mav.addObject("userJson", userJson);
			mav.addObject("userOthJson", userOthJson);
			WebSocket.sendMessageToUser(userID_oth, "Start " + userID + " " + user.getUsername() + " " + user.getElo());
		}
		else /*bên trắng chờ*/{
			mav = new ModelAndView("playWhite");
			mav.addObject("Type", "NewGame");
			User user = userDAO.getUserByID(userID);
			ObjectMapper objectMapper = new ObjectMapper();
			String userJson = objectMapper.writeValueAsString(user);
			mav.addObject("userJson", userJson);
		}
		
		return mav;
	}
	
}
