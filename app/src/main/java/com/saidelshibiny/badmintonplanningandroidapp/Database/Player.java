package com.saidelshibiny.badmintonplanningandroidapp.Database;

/*
* Created by Chaonan Chen on March 9, 2019
* */
public class Player {
    private Integer playerId;
    private String firstName;
    private String lastName;
    private Integer ranking;
    private int imageID;
    private boolean isChecked;

   public Player() {}

    //constructor
    public Player(Integer playerId, String firstName, String lastName, Integer ranking, int imageID, boolean isChecked) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ranking = ranking;
        this.imageID = imageID;
        this.isChecked = false;
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

    public int getImageID() {
        return imageID;
    }

    public boolean getChecked() { return isChecked;
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

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public void setChecked(boolean checked) { isChecked = checked;   }
}
