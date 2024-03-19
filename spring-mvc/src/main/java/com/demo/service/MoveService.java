package com.demo.service;

import com.demo.dao.MoveDAO;
import com.demo.model.Move;
import java.util.List;

public class MoveService {

    private MoveDAO moveDAO = new MoveDAO();

    // 1. Get Moves by Game ID
    public List<Move> getMovesByGameId(int gameId) {
        // Bạn có thể thêm logic kiểm tra hoặc xử lý ở đây
        return moveDAO.getMovesByGameId(gameId);
    }

    // 2. Add a Move
    public void addMove(Move move) {
        // Bạn có thể thêm bất kỳ logic nghiệp vụ nào ở đây trước khi lưu
        moveDAO.addMove(move);
    }

    // 3. Update a Move
    public void updateMove(Move move) {
        // Kiểm tra và xử lý logic nghiệp vụ trước khi cập nhật
        moveDAO.updateMove(move);
    }

    // 4. Delete a Move
    public void deleteMove(int moveID) {
        // Thực hiện các bước kiểm tra hoặc xử lý cần thiết trước khi xóa
        moveDAO.deleteMove(moveID);
    }
}
