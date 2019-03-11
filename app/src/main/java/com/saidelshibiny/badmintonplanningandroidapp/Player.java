package com.saidelshibiny.badmintonplanningandroidapp;

/*
* Created by Chaonan Chen on March 9, 2019
* */
public class Player {
    private Integer playerId;
    private String firstName;
    private String lastName;
    private Integer ranking;
    private Integer imageId;

    public Player() {}

    //constructor
    public Player(Integer playerId, String firstName, String lastName, Integer ranking, Integer imageId) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ranking = ranking;
        this.imageId = imageId;
    }

    //getter
    public Integer getPlayerId() {
        return playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getRanking() {
        return ranking;
    }

    public Integer getImageId() {
        return imageId;
    }

    //setter

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
}
