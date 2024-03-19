package com.demo.model;

public class Friendship {
    private int friendshipID;
    private int userID1;
    private int userID2;
    private int status; // 1: accepted, 0: pending, -1: declined

    // Getter and Setter
    public int getFriendshipID() {
        return friendshipID;
    }

    public void setFriendshipID(int friendshipID) {
        this.friendshipID = friendshipID;
    }

    public int getUserID1() {
        return userID1;
    }

    public void setUserID1(int userID1) {
        this.userID1 = userID1;
    }

    public int getUserID2() {
        return userID2;
    }

    public void setUserID2(int userID2) {
        this.userID2 = userID2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        if(status == 1 || status == 0 || status == -1) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid status value");
        }
    }
}
