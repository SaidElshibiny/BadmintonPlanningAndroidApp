package com.saidelshibiny.badmintonplanningandroidapp.Database;

/*
* Created by Chaonan Chen on March 9, 2019
* */
public class Player {
    private Integer playerId;
    private String firstName;
    private String lastName;
    private Integer ranking;
    private String imageURL;

   public Player() {}

    //constructor
    public Player(Integer playerId, String firstName, String lastName, Integer ranking, String imageURL) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ranking = ranking;
        this.imageURL = imageURL;
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

    public String getImageURL() {
        return imageURL;
    }

    //setter

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
}
