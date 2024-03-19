package com.demo.dao;

import com.demo.model.Move;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoveDAO {

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/chess", "root", "");
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    // 1. Get Moves by Game ID
    public List<Move> getMovesByGameId(int gameId) {
        List<Move> moves = new ArrayList<>();
        String sql = "SELECT * FROM moves WHERE gameID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, gameId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Move move = new Move();
                    move.setMoveID(rs.getInt("moveID"));
                    move.setGameID(rs.getInt("gameID"));
                    move.setPlayerID(rs.getInt("playerID"));
                    move.setMoveNotation(rs.getString("moveNotation"));
                    move.setMoveNumber(rs.getInt("moveNumber"));
                    move.setMoveQuality(rs.getString("moveQuality"));
                    move.setBetterMove(rs.getString("betterMove"));
                    moves.add(move);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving moves by game ID", e);
        }
        return moves;
    }

    // 2. Add a Move
    public void addMove(Move move) {
    	
        String sql = "INSERT INTO moves (gameID, playerID, moveNotation, moveNumber, moveQuality, betterMove) VALUES (?, ?, ?, ?, ?, ?)";
        System.err.println(move);
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, move.getGameID());
            stmt.setInt(2, move.getPlayerID());
            stmt.setString(3, move.getMoveNotation());
            stmt.setInt(4, move.getMoveNumber());
            stmt.setString(5, move.getMoveQuality().toString());
            stmt.setString(6, move.getBetterMove());
            stmt.executeUpdate();
          System.err.println("addmove done");
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new RuntimeException("Error adding move", e);
        }
    }

    // 3. Update a Move
    public void updateMove(Move move) {
        String sql = "UPDATE moves SET gameID = ?, playerID = ?, moveNotation = ?, moveNumber = ?, moveQuality = ?, betterMove = ? WHERE moveID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, move.getGameID());
            stmt.setInt(2, move.getPlayerID());
            stmt.setString(3, move.getMoveNotation());
            stmt.setInt(4, move.getMoveNumber());
            stmt.setString(5, move.getMoveQuality().toString());
            stmt.setString(6, move.getBetterMove());
            stmt.setInt(7, move.getMoveID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating move", e);
        }
    }

    // 4. Delete a Move
    public void deleteMove(int moveID) {
        String sql = "DELETE FROM moves WHERE moveID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, moveID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting move", e);
        }
    }
 // Method to count the number of moves for a given game ID
    public int countMovesByGameId(int gameId) {
        String sql = "SELECT COUNT(*) FROM moves WHERE gameID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, gameId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error counting moves by game ID", e);
        }
        return 0;
    }
 // Method to delete the last move of a game if the move count is odd
    public void deleteLastMoveIfOdd(int gameId) {
        int moveCount = countMovesByGameId(gameId);
        if (moveCount % 2 != 0) { // Check if move count is odd
            String sql = "DELETE FROM moves WHERE moveID = (SELECT moveID FROM moves WHERE gameID = ? ORDER BY moveID DESC LIMIT 1)";
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, gameId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Error deleting last move", e);
            }
        }
    }

}
