package com.demo.dao;

import com.demo.model.Game;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAO {

	private Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/chess", "root", "");
		} catch (Exception e) {
			throw new RuntimeException("Error connecting to the database", e);
		}
	}

	// 1. Add a Game
	public int addGame(Game game) {
		String sql = "INSERT INTO games (player1ID, player2ID, outcome, status, type, analyzed) VALUES (?, ?, ?, ?, ?, ?)";
		int gameId = 0; // Variable to hold the generated game ID

		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setInt(1, game.getPlayer1ID());
			stmt.setInt(2, game.getPlayer2ID());
			stmt.setString(3, game.getOutcome().name());
			stmt.setInt(4, game.getStatus());
			stmt.setInt(5, game.getType());
			stmt.setBoolean(6, game.isAnalyzed());

			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating game failed, no rows affected.");
			}

			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					gameId = generatedKeys.getInt(1);
				} else {
					throw new SQLException("Creating game failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error adding game: " + e.getMessage(), e);
		}

		return gameId;
	}

	// 2. Update a Game
	public void updateGame(Game game) {
		String sql = "UPDATE games SET player1ID = ?, player2ID = ?, outcome = ?, status = ?, type = ?, analyzed = ? WHERE gameID = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, game.getPlayer1ID());
			stmt.setInt(2, game.getPlayer2ID());
			stmt.setString(3, game.getOutcome().name());
			stmt.setInt(4, game.getStatus());
			stmt.setInt(5, game.getType());
			stmt.setBoolean(6, game.isAnalyzed());
			stmt.setInt(7, game.getGameID());

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error updating game", e);
		}
	}

	// 3. Delete a Game
	public void deleteGame(int gameID) {
		String sql = "DELETE FROM games WHERE gameID = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, gameID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error deleting game", e);
		}
	}

	// 4. Get all game by userID
	public List<Game> getGamesByUserId(int userId) {
		List<Game> games = new ArrayList<>();
		String sql = "SELECT * FROM games WHERE player1ID = ? OR player2ID = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, userId);
			stmt.setInt(2, userId);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Game game = new Game();
					game.setGameID(rs.getInt("gameID"));
					game.setPlayer1ID(rs.getInt("player1ID"));
					game.setPlayer2ID(rs.getInt("player2ID"));
					game.setOutcome(Game.GameOutcome.valueOf(rs.getString("outcome")));
					game.setStatus(rs.getInt("status"));
					game.setType(rs.getInt("type"));
					game.setAnalyzed(rs.getBoolean("analyzed"));
					games.add(game);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error retrieving games by user ID", e);
		}
		return games;
	}

	public Game getGame(int gameId) {
		Game game = new Game();
		String sql = "SELECT * FROM games WHERE gameId = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, gameId);
			

			try (ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					
					game.setGameID(rs.getInt("gameID"));
					game.setPlayer1ID(rs.getInt("player1ID"));
					game.setPlayer2ID(rs.getInt("player2ID"));
					game.setOutcome(Game.GameOutcome.valueOf(rs.getString("outcome")));
					game.setStatus(rs.getInt("status"));
					game.setType(rs.getInt("type"));
					game.setAnalyzed(rs.getBoolean("analyzed"));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error retrieving games by user ID", e);
		}
		return game;
	}
}