package com.demo.dao;

import com.demo.model.Friendship;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendshipDAO {

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/chess", "root", "");
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    // 1. Get List of Friendships
    public List<Friendship> getListFriends() {
        List<Friendship> friendships = new ArrayList<>();
        String sql = "SELECT * FROM friendships";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Friendship friendship = new Friendship();
                friendship.setFriendshipID(rs.getInt("friendshipID"));
                friendship.setUserID1(rs.getInt("userID1"));
                friendship.setUserID2(rs.getInt("userID2"));
                friendship.setStatus(rs.getInt("status"));
                friendships.add(friendship);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving friendships", e);
        }
        return friendships;
    }

    // 2. Add a Friendship
    public void addFriendship(Friendship friendship) {
        String sql = "INSERT INTO friendships (userID1, userID2, status) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, friendship.getUserID1());
            stmt.setInt(2, friendship.getUserID2());
            stmt.setInt(3, friendship.getStatus());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding friendship", e);
        }
    }

    // 3. Update a Friendship
    public void updateFriendship(Friendship friendship) {
        String sql = "UPDATE friendships SET userID1 = ?, userID2 = ?, status = ? WHERE friendshipID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, friendship.getUserID1());
            stmt.setInt(2, friendship.getUserID2());
            stmt.setInt(3, friendship.getStatus());
            stmt.setInt(4, friendship.getFriendshipID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating friendship", e);
        }
    }

    // 4. Delete a Friendship
    public void deleteFriendship(int friendshipID) {
        String sql = "DELETE FROM friendships WHERE friendshipID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, friendshipID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting friendship", e);
        }
    }
}
