package com.badminton.riversidesports.Database;

public class Match {
    private int matchId;
    private int winningScore;
    private int losingScore;
    private String startTime;

    //constructor
    public Match(){}

    public Match(int matchId, int winningScore, int losingScore, String startTime) {
        this.matchId = matchId;
        this.winningScore = winningScore;
        this.losingScore = losingScore;
        this.startTime = startTime;
    }

    //getter and setter

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getWinningScore() {
        return winningScore;
    }

    public void setWinningScore(int winningScore) {
        this.winningScore = winningScore;
    }

    public int getLosingScore() {
        return losingScore;
    }

    public void setLosingScore(Integer losingScore) {
        this.losingScore = losingScore;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
