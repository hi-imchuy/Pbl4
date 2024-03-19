package com.demo.model;

public class GameHistory {
    private int id;
    private String playerAName;
    private String playerBName;
    private String playerAIcon = "./template/web/QuanCo/Tot_Trang.png"; 
    private String playerBIcon = "./template/web/QuanCo/Tot_Den.png"; // URL hình ảnh
    private String playerAFlag = "./template/web/Icon/flag_icon.png"; // URL cờ
    private String playerBFlag = "./template/web/Icon/flag_icon.png"; // URL cờ
    private String result; // WIN, LOSE, DRAW
    private int playerAEloChange;
    private int playerBEloChange;

    public int getId() {
        return id;
    }
    public void swapIcon() {
    	String tmp = playerAIcon;
    	playerAIcon = playerBIcon;
    	playerBIcon = tmp;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerAName() {
        return playerAName;
    }

    public void setPlayerAName(String playerAName) {
        this.playerAName = playerAName;
    }

    public String getPlayerBName() {
        return playerBName;
    }

    public void setPlayerBName(String playerBName) {
        this.playerBName = playerBName;
    }

    public String getPlayerAIcon() {
        // Nếu playerAIcon chưa được thiết lập, trả về giá trị mặc định
        return playerAIcon;
    }

    public void setPlayerAIcon(String playerAIcon) {
        this.playerAIcon = playerAIcon;
    }

    public String getPlayerBIcon() {
        // Nếu playerBIcon chưa được thiết lập, trả về giá trị mặc định
        return playerBIcon;
    }

    public void setPlayerBIcon(String playerBIcon) {
        this.playerBIcon = playerBIcon;
    }

    public String getPlayerAFlag() {
        return playerAFlag;
    }

    public void setPlayerAFlag(String playerAFlag) {
        this.playerAFlag = playerAFlag;
    }

    public String getPlayerBFlag() {
        return playerBFlag;
    }

    public void setPlayerBFlag(String playerBFlag) {
        this.playerBFlag = playerBFlag;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getPlayerAEloChange() {
        return playerAEloChange;
    }

    public void setPlayerAEloChange(int playerAEloChange) {
        this.playerAEloChange = playerAEloChange;
    }

    public int getPlayerBEloChange() {
        return playerBEloChange;
    }

    public void setPlayerBEloChange(int playerBEloChange) {
        this.playerBEloChange = playerBEloChange;
    }
}
