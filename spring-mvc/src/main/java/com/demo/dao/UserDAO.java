package com.demo.dao;

import com.demo.model.Game;
import com.demo.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	private Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/chess", "root", "");
		} catch (Exception e) {
			throw new RuntimeException("Error connecting to the database", e);
		}
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM users";
		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				User user = new User();
				user.setUserID(rs.getInt("userID"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setElo(rs.getInt("Elo"));
				user.setLastname(rs.getString("lastname"));
				user.setFirstname(rs.getString("firstname"));
				user.setEmail(rs.getString("email"));
				user.setAge(rs.getInt("age"));
				user.setCountry(rs.getString("country"));
				users.add(user);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error retrieving users", e);
		}
		return users;
	}

	public boolean addUser(User user) {
		String sql = "INSERT INTO users (username, password, Elo, lastname, firstname, email, age, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setInt(3, user.getElo());
			stmt.setString(4, user.getLastname());
			stmt.setString(5, user.getFirstname());
			stmt.setString(6, user.getEmail());
			stmt.setInt(7, user.getAge());
			stmt.setString(8, user.getCountry());

			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			//throw new RuntimeException("Error adding new user", e);
			return false;
		}
	}

	public User getUserByID(int userID) {
		String sql = "SELECT * FROM users WHERE userID = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, userID);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setUserID(rs.getInt("userID"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setElo(rs.getInt("Elo"));
					user.setLastname(rs.getString("lastname"));
					user.setFirstname(rs.getString("firstname"));
					user.setEmail(rs.getString("email"));
					user.setAge(rs.getInt("age"));
					user.setCountry(rs.getString("country"));
					return user;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error finding user by ID", e);
		}
		return null;
	}

	public void updateUser(User user) {
		String sql = "UPDATE users SET username = ?, password = ?, Elo = ?, lastname = ?, firstname = ?, email = ?, age = ?, country = ? WHERE userID = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setInt(3, user.getElo());
			stmt.setString(4, user.getLastname());
			stmt.setString(5, user.getFirstname());
			stmt.setString(6, user.getEmail());
			stmt.setInt(7, user.getAge());
			stmt.setString(8, user.getCountry());
			stmt.setInt(9, user.getUserID());

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error updating user", e);
		}
	}

	public void deleteUser(int userID) {
		String sql = "DELETE FROM users WHERE userID = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, userID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error deleting user", e);
		}
	}
	
	public User checkLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        User user = new User();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    
                    user.setUserID(rs.getInt("userID"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setElo(rs.getInt("Elo"));
                    user.setLastname(rs.getString("lastname"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setEmail(rs.getString("email"));
                    user.setAge(rs.getInt("age"));
                    user.setCountry(rs.getString("country"));
                    
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking user login", e);
        }
        return user;
    }
	
	public int checkGameIN_PROGRESS(int userID, int mode) {
		GameDAO gameDAO = new GameDAO();
		List<Game> games = gameDAO.getGamesByUserId(userID);
		for(Game game : games) {
			if(game.getStatus() == 0 && game.getType() == mode) {
				return game.getGameID();
			}
		}
		return -1;
	}
	public void updateUser1(String firstname, String lastname, String location, int userID) {
		String sql = "UPDATE users SET lastname = ?, firstname = ?,  country = ? WHERE userID = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {


			stmt.setString(1, lastname);
			stmt.setString(2,firstname);

			stmt.setString(3, location);
			stmt.setInt(4, userID);

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error updating user", e);
		}
	}
	public User getUserByUsername(String username) {
		String sql = "SELECT * FROM users WHERE username = ?";
		try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, username);
			try (ResultSet rs = stmt.executeQuery()){
				if(rs.next()) {
					User user = new User();
					user.setUsername(rs.getString("username"));
					user.setLastname(rs.getString("lastname"));
					user.setFirstname(rs.getString("firstname"));
					user.setElo(rs.getInt("Elo"));
					return user;
				}
			}
		} catch(SQLException e) {
			throw new RuntimeException("Error finding user by username", e);
		}
		return null;
	}
}
