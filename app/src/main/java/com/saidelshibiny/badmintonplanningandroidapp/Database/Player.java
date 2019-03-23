package com.saidelshibiny.badmintonplanningandroidapp.Database;

import android.os.Parcel;
import android.os.Parcelable;

/*
* Created by Chaonan Chen on March 9, 2019
* */
public class Player implements Parcelable {
    private Integer playerId;
    private String firstName;
    private String lastName;
    private Integer ranking;
    private int imageID;
    private boolean isChecked = false;

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

    protected Player(Parcel in) {
        if (in.readByte() == 0) {
            playerId = null;
        } else {
            playerId = in.readInt();
        }
        firstName = in.readString();
        lastName = in.readString();
        if (in.readByte() == 0) {
            ranking = null;
        } else {
            ranking = in.readInt();
        }
        imageID = in.readInt();
        isChecked = in.readByte() != 0;
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

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

    public void setChecked(boolean isChecked) { this.isChecked = isChecked;   }

    public void toggleChecked(){
        isChecked = !isChecked;

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (playerId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(playerId);
        }
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        if (ranking == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(ranking);
        }
        parcel.writeInt(imageID);
        parcel.writeByte((byte) (isChecked ? 1 : 0));
    }
}
