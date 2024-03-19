package com.demo.AI;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import com.demo.dao.GameDAO;
import com.demo.dao.MoveDAO;
import com.demo.model.Game;
import com.demo.model.Move;
import com.google.gson.Gson;
import com.demo.model.Game.GameOutcome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;

@SuppressWarnings("unused")
public class RoomManager {
	private ConcurrentHashMap<Integer, ClientConnection> activeGames = new ConcurrentHashMap<>();
	private ConcurrentHashMap<Integer, Game> games = new ConcurrentHashMap<>();
	private GameDAO gameDAO = new GameDAO();
	private static MoveDAO moveDAO = new MoveDAO();

	public int createGame(int mode, int player1ID, int player2ID) throws IOException {
		Game newGame = new Game();
		newGame.setAnalyzed(false);
		newGame.setOutcome(GameOutcome.IN_PROGRESS);
		newGame.setType(mode);
		newGame.setPlayer1ID(player1ID);
		newGame.setPlayer2ID(player2ID);
		newGame.setStatus(0);
		int gameId = gameDAO.addGame(newGame);

		ClientConnection connection = ServerAI.getAvailablePythonServer();
		if (connection != null) {
			connection.sendMessage("createGame mode=" + mode + " " + player1ID + " " + player2ID + " " + gameId);
			activeGames.put(gameId, connection);
			games.put(gameId, newGame);
			if (mode == 0) {
				ServerAI.updatePVE(connection, +1);
			} else {
				ServerAI.updatePVP(connection, +1);
			}
		}

		return gameId;
	}

	public String sendMove(int gameId, String move, int userID) {

		ClientConnection connection = activeGames.get(gameId);
		if (connection != null) {
			try {
				String res = connection.sendMessage("move gameId=" + gameId + " move=" + move);
				System.err.println(res);
				ParsedData parse = parseData(res, gameId);
				if (parse.isE()) {
					Game game = gameDAO.getGame(gameId);
					game.setStatus(1);
					if (userID == game.getPlayer2ID()) {
						game.setOutcome(Game.GameOutcome.WIN);
					} else {
						game.setOutcome(Game.GameOutcome.LOSE);
					}
					gameDAO.updateGame(game);
					if (game.getType() == 0)
						ServerAI.updatePVE(connection, -1);
					else
						ServerAI.updatePVP(connection, -1);
				}
				res = parse.toString();
				System.err.println(res);
				return res;
			} catch (IOException e) {
				ServerAI.removeAvailablePythonServer(connection);
				moveDAO.deleteLastMoveIfOdd(gameId);
				e.printStackTrace();
			}
		}
		return null;
	}

	public void removeGame(int gameId, GameOutcome outcome) {
		Game game = games.get(gameId);
		if (game != null) {
			game.setStatus(1);
			game.setOutcome(outcome);
			gameDAO.updateGame(game);
			activeGames.remove(gameId);
			games.remove(gameId);
		}
	}

	public Game getGame(int gameId) {
		return games.get(gameId);
	}

	public ClientConnection getActiveGame(int gameId) {
		return activeGames.get(gameId);
	}

	public int createRoomWithAI(int userID) {
		try {
			return createGame(0, userID, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int createRoomWithPlayer(int userID, int userID_oth) {
		try {
			return createGame(1, userID, userID_oth);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public void requestDraw(int gameID, int userID) {

	}

	public void resignGame(int gameID, int userID) {

	}

	public void analyzeGame(int gameID) {

	}

	public static ParsedData parseData(String input, int gameId) {
		Map<String, List<String>> legalMoves = new HashMap<>();

		// Tách chuỗi để lấy thông tin computer moved
		Pattern movePattern = Pattern.compile("Computer moved: (.*?),");
		Matcher moveMatcher = movePattern.matcher(input);
		String computer = moveMatcher.find() ? moveMatcher.group(1) : null;
		Move newMove = new Move(gameId, 0, computer);
		System.err.println("computer " + newMove);
		moveDAO.addMove(newMove);
		// Tách chuỗi để lấy thông tin legal moves
		Pattern legalPattern = Pattern.compile("'([A-Za-z]@)(\\w+)': \\[([^]]+)\\]");

		Matcher legalMatcher = legalPattern.matcher(input);
		while (legalMatcher.find()) {
			String position = legalMatcher.group(2);
			String[] moves = legalMatcher.group(3).replace("'", "").split(", ");
			for (int i = 0; i < moves.length; i++) {
				moves[i] = moves[i].substring(moves[i].length() - 2);
			}
			legalMoves.put(position, Arrays.asList(moves));
		}

		return new ParsedData(computer, legalMoves);
	}

	static class ParsedData {
		public final String computer;
		public final Map<String, List<String>> legalMoves;

		public ParsedData(String computer, Map<String, List<String>> legalMoves) {
			this.computer = computer;
			this.legalMoves = legalMoves;
		}

		public boolean isE() {
			return legalMoves.isEmpty();
		}

		@Override
		public String toString() {
			if (!isE())
				return computer + " AND " + legalMoves;
			return computer + " AND YOU LOSE";
		}

	}

	public String getBoard(int gameID) {
		// TODO Auto-generated method stub
		ClientConnection connection = activeGames.get(gameID);
		try {
			return connection.sendMessage("get " + gameID);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean CheckIsValidGame(int gameID) {
		ClientConnection connection = activeGames.get(gameID);
		System.err.println("CheckIsValidGame" + gameID);
		System.err.println(connection);
		if (connection != null) {
			try {
				String res = connection.sendMessage("Check " + gameID);
				return res.equals("have");
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return false;
	}

	public String parseMoves(String data) {
		Map<String, List<String>> movesMap = new HashMap<>();
		data = data.substring(1, data.length() - 1); // Loại bỏ dấu ngoặc nhọn ở đầu và cuối

		String[] entries = data.split(", '(?=[A-Z]@)"); // Tách mỗi cặp key-value

		for (String entry : entries) {
			String[] keyValue = entry.split(": "); // Tách key và value
			String key = keyValue[0].split("@")[1].replaceAll("[\\{'\\}]", "").trim(); // Lấy vị trí hiện tại
			String[] movesArray = keyValue[1].replaceAll("[\\[\\]' ]", "").split(","); // Lấy các ô có thể đến

			List<String> destinations = new ArrayList<>();
			for (String move : movesArray) {
				destinations.add(move.substring(2));
			}
			movesMap.put(key, destinations);
		}

		return ("" + movesMap);
	}

	public String getLegal(int gameID) {
		ClientConnection connection = activeGames.get(gameID);
		if (connection != null) {
			try {
				String res = connection.sendMessage("Legal " + gameID);
				System.err.println(res);
				res = parseMoves(res);
				System.err.println(res);
				return res;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public void addRoom(int gameId, int mode, int player1ID, int player2ID, String moves) {
		// TODO Auto-generated method stub
		ClientConnection connection = ServerAI.getAvailablePythonServer();
		if (connection != null) {
			try {
				connection.sendMessage(
						"Add mode=" + mode + " " + player1ID + " " + player2ID + " " + gameId + " " + moves);
				System.err.println("Add mode=" + mode + " " + player1ID + " " + player2ID + " " + gameId + " " + moves);
				if (mode == 1)
					ServerAI.updatePVE(connection, +1);
				else
					ServerAI.updatePVP(connection, +1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			activeGames.put(gameId, connection);
			games.put(gameId, gameDAO.getGame(gameId));

		}

	}

	@SuppressWarnings("unused")
	public static List<Move> analysis(List<Move> moves) {
		// TODO Auto-generated method stub
		ClientConnection connection = ServerAI.getAvailablePythonServer();
		if (connection != null) {
			try {
				System.err.println("Analysis " + moves);
				String jsonResponse = connection.sendMessage("Analysis " + moves);
				updateMovesFromResponse(moves, jsonResponse);
				for(Move move : moves) {
					System.err.println(move.getMoveNotation() + " " + move.getBetterMove() +  " " + move.getMoveQuality());
				}
				return moves;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void updateMovesFromResponse(List<Move> moves, String jsonResponse) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<Map<String, String>>>() {
		}.getType();
		List<Map<String, String>> moveAnalysis = gson.fromJson(jsonResponse, type);

		for (int i = 0; i < moves.size(); i++) {
			Move currentMove = moves.get(i);
			Map<String, String> analysis = moveAnalysis.get(i);
			String quality = analysis.get("quality");
			currentMove.setMoveQuality(quality);
			Move nextMove = moves.get((i + 1) % moves.size());
			nextMove.setBetterMove(analysis.get("optimal_move"));
		}
		for(Move move : moves) {
			moveDAO.updateMove(move);
		}
	}

}
