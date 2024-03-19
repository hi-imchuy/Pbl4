package com.demo.service;

import com.demo.dao.FriendshipDAO;
import com.demo.model.Friendship;
import java.util.List;

public class FriendshipService {

    private FriendshipDAO friendshipDAO = new FriendshipDAO();

    // 1. Get List of Friendships
    public List<Friendship> getListFriends() {
        return friendshipDAO.getListFriends();
    }

    // 2. Add a Friendship
    public void addFriendship(Friendship friendship) {
        // Bạn có thể thêm bất kỳ logic nghiệp vụ nào ở đây trước khi lưu
        friendshipDAO.addFriendship(friendship);
    }

    // 3. Update a Friendship
    public void updateFriendship(Friendship friendship) {
        // Kiểm tra và xử lý logic nghiệp vụ trước khi cập nhật
        friendshipDAO.updateFriendship(friendship);
    }

    // 4. Delete a Friendship
    public void deleteFriendship(int friendshipID) {
        // Thực hiện các bước kiểm tra hoặc xử lý cần thiết trước khi xóa
        friendshipDAO.deleteFriendship(friendshipID);
    }
}
