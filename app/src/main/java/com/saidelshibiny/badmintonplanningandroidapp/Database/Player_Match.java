package com.saidelshibiny.badmintonplanningandroidapp.Database;

public class Player_Match {
    private Integer playerId;
    private Integer matchId;
    private boolean isWinner; // 1 for win, 0 for lose

    //constructor
    public Player_Match(){}
    public Player_Match(Integer playerId, Integer matchId, boolean isWinner) {
        this.playerId = playerId;
        this.matchId = matchId;
        this.isWinner = isWinner;
    }

    //getter and setter
    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }
}
