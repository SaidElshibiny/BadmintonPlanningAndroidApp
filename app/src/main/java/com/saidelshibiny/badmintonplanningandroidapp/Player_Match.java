package com.saidelshibiny.badmintonplanningandroidapp;

public class Player_Match {
    private Integer productId;
    private Integer matchId;
    private boolean result; // 1 for win, 0 for lose

    //constructor
    public Player_Match(){}
    public Player_Match(Integer productId, Integer matchId, boolean result) {
        this.productId = productId;
        this.matchId = matchId;
        this.result = result;
    }

    //getter and setter

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
