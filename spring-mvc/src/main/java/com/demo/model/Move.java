package com.demo.model;

public class Move {
	private int moveID;
    private int gameID;
    private int playerID;
    private String moveNotation;
    private int moveNumber;
    private MoveQuality moveQuality = MoveQuality.NONE; // Enum to represent the quality of the move
    private String betterMove = "";
    public enum MoveQuality {
        GOOD, BAD, NEUTRAL, NONE;

        @Override
        public String toString() {
            switch (this) {
                case GOOD:
                    return "GOOD";
                case BAD:
                    return "BAD";
                case NEUTRAL:
                    return "NEUTRAL";
                default:
                    return "NONE";
            }	
        }
    }


    // Constructor
    public Move() {}
    
    public Move(int gameID, int playerID, String moveNotation) {
        this.gameID = gameID;
        this.playerID = playerID;
        this.moveNotation = moveNotation;
    }
   
    public Move(int moveID, int gameID, int playerID, String moveNotation, int moveNumber) {
        this.moveID = moveID;
        this.gameID = gameID;
        this.playerID = playerID;
        this.moveNotation = moveNotation;
        this.moveNumber = moveNumber;
    }

    // Getters and Setters
    public int getMoveID() {
        return moveID;
    }

    public void setMoveID(int moveID) {
        this.moveID = moveID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getMoveNotation() {
        return moveNotation;
    }

    public void setMoveNotation(String moveNotation) {
        this.moveNotation = moveNotation;
    }

    public int getMoveNumber() {
        return moveNumber;
    }

    public void setMoveNumber(int moveNumber) {
        this.moveNumber = moveNumber;
    }
    
    public MoveQuality getMoveQuality() {
        return moveQuality;
    }

    public void setMoveQuality(String moveQualityStr) {
        try {
            this.moveQuality = MoveQuality.valueOf(moveQualityStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            this.moveQuality = MoveQuality.NONE;
        }
    }

    public String getBetterMove() {
        return betterMove;
    }

    public void setBetterMove(String betterMove) {
        this.betterMove = betterMove;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return moveNotation;
    }
}
