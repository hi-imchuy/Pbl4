package com.demo.service;

import com.demo.dao.GameDAO;
import com.demo.model.Game;
import java.util.List;

public class GameService {

    private GameDAO gameDAO = new GameDAO();

    // 1. Add a Game
    public int addGame(Game game) {
        // Bạn có thể thêm bất kỳ logic nghiệp vụ nào ở đây trước khi lưu
        return gameDAO.addGame(game);
    }

    // 2. Update a Game
    public void updateGame(Game game) {
        // Kiểm tra và xử lý logic nghiệp vụ trước khi cập nhật
        gameDAO.updateGame(game);
    }

    // 3. Delete a Game
    public void deleteGame(int gameID) {
        // Thực hiện các bước kiểm tra hoặc xử lý cần thiết trước khi xóa
        gameDAO.deleteGame(gameID);
    }

    // 4. Get all games by userID
    public List<Game> getGamesByUserId(int userId) {
        return gameDAO.getGamesByUserId(userId);
    }

    // 5. Get a Game by ID
    public Game getGame(int gameId) {
        return gameDAO.getGame(gameId);
    }
}
