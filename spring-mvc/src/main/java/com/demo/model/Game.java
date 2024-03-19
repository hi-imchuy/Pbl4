package com.demo.model;

public class Game {
	private int gameID;
	private int player1ID;
	private int player2ID;
	private GameOutcome outcome; 
	private int status; // Consider defining specific values for different statuses
	private int type; // 1: human, 0: machine;
	private boolean analyzed;

	// Enum for outcome
	public enum GameOutcome {
		WIN, LOSE, DRAW, IN_PROGRESS
	}

	// Constructor without parameters
	public Game() {
		// You can set default values if necessary
		this.status = 0; // Assuming 0 is the default status for a new game
		this.outcome = GameOutcome.IN_PROGRESS; // Assuming the default outcome for a new game is IN_PROGRESS
		this.type = 1; // Assuming default type is human (1)
	}

	// Constructor with parameters
	public Game(int gameID, int player1ID, int player2ID, GameOutcome outcome, int status, int type) {
		this.gameID = gameID;
		this.player1ID = player1ID;
		this.player2ID = player2ID;
		this.outcome = outcome;
		this.status = status;
		this.type = type;
	}

	// Getter and Setter
	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public int getPlayer1ID() {
		return player1ID;
	}

	public void setPlayer1ID(int player1ID) {
		this.player1ID = player1ID;
	}

	public int getPlayer2ID() {
		return player2ID;
	}

	public void setPlayer2ID(int player2ID) {
		this.player2ID = player2ID;
	}

	public GameOutcome getOutcome() {
		return outcome;
	}

	public void setOutcome(GameOutcome outcome) {
		this.outcome = outcome;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		// Ensure that status is a valid value
		if (statusIsValid(status)) {
			this.status = status;
		} else {
			throw new IllegalArgumentException("Invalid status value");
		}
	}

	// Helper method to validate status
	private boolean statusIsValid(int status) {
		// Define valid status values
		final int STATUS_ONGOING = 0;
		final int STATUS_COMPLETED = 1;
		final int STATUS_ABORTED = 2;

		return status == STATUS_ONGOING || status == STATUS_COMPLETED || status == STATUS_ABORTED;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		if (type == 0 || type == 1) {
			this.type = type;
		} else {
			throw new IllegalArgumentException("Invalid type value");
		}
	}

	public boolean isAnalyzed() {
		return analyzed;
	}

	public void setAnalyzed(boolean analyzed) {
		this.analyzed = analyzed;
	}
}
