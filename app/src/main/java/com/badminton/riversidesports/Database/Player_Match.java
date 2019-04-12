package com.badminton.riversidesports.Database;

public class Player_Match {
    private int playerId;
    private int matchId;
    private boolean isWinner; // 1 for win, 0 for lose

    //constructor
    public Player_Match(){}
    public Player_Match(int playerId, int matchId, boolean isWinner) {
        this.playerId = playerId;
        this.matchId = matchId;
        this.isWinner = isWinner;
    }

    //getter and setter
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }
}
