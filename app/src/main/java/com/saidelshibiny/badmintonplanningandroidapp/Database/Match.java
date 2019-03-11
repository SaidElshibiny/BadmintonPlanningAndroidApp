package com.saidelshibiny.badmintonplanningandroidapp.Database;

import java.sql.Date;

public class Match {
    private Integer matchId;
    private Integer winningScore;
    private Integer losingScore;
    private Date startTime;

    //constructor
    public Match(){}

    public Match(Integer matchId, Integer winningScore, Integer losingScore, Date startTime) {
        this.matchId = matchId;
        this.winningScore = winningScore;
        this.losingScore = losingScore;
        this.startTime = startTime;
    }

    //getter and setter

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getWinningScore() {
        return winningScore;
    }

    public void setWinningScore(Integer winningScore) {
        this.winningScore = winningScore;
    }

    public Integer getLosingScore() {
        return losingScore;
    }

    public void setLosingScore(Integer losingScore) {
        this.losingScore = losingScore;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
