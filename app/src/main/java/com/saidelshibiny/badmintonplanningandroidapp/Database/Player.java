package com.saidelshibiny.badmintonplanningandroidapp.Database;

import android.os.Parcel;
import android.os.Parcelable;

/*
* Created by Chaonan Chen on March 9, 2019
* */
//public class Player implements Parcelable {
public class Player{
    private int id;
    private String firstName;
    private String lastName;
    private int ranking;
    private int imageID;
    private boolean isChecked;

   public Player() {}
    //constructor
    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //constructor
    public Player(String firstName, String lastName, int ranking) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ranking = ranking;
    }

    //constructor
    public Player(String firstName, String lastName, int ranking, int imageID, boolean isChecked) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ranking = ranking;
        this.imageID = imageID;
        this.isChecked = isChecked;
    }

    public Player(int id, String firstName, String lastName, int ranking, int imageID, boolean isChecked) {
       this.id = id;
       this.firstName = firstName;
        this.lastName = lastName;
        this.ranking = ranking;
        this.imageID = imageID;
        this.isChecked = isChecked;
    }

    //getter and setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getRanking() {
        return ranking;
    }
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    public int getImageID() {
        return imageID;
    }
    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
    public boolean getChecked() { return isChecked; }
    public void setChecked(boolean isChecked) { this.isChecked = isChecked;   }
    public void toggleChecked(){ isChecked = !isChecked; }

    public void writeToParcel(Parcel parcel, int i) {
        if (id == 0) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        if (ranking == 0) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(ranking);
        }
        parcel.writeInt(imageID);
        parcel.writeByte((byte) (isChecked ? 1 : 0));
    }


    protected Player(Parcel in) {
       this.id = in.readInt();
       this.firstName = in.readString();
       this.lastName = in.readString();
       this.ranking = in.readInt();
       this.imageID = in.readInt();
       this.isChecked = in.readByte() != 0; // isChecked == true if byte != 0

    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

}

/*
*fake player's data for testing purpose
//    get player info from here - player info displayed
//        players.add(new Player(0, "Sally", "Zhao", 78, R.drawable.girl1, false));
//        players.add(new Player(1, "John", "Json", 88, R.drawable.boy1,false));
//        players.add(new Player(2, "Mitch", "Zhao", 78, R.drawable.girl2,false));
//        players.add(new Player(3, "Sitch", "Json", 88, R.drawable.boy2,false));
//        players.add(new Player(4, "Shelly", "Smith", 78, R.drawable.girl3,false));
//        players.add(new Player(5, "John", "Yuan", 88, R.drawable.boy3,false));
//        players.add(new Player(6, "Sally", "Chen", 78, R.drawable.girl4,false));
//        players.add(new Player(7, "Jess", "Json", 88, R.drawable.boy4,false));
//        players.add(new Player(8, "Shally", "Zhao", 78, R.drawable.girl5,false));
//        players.add(new Player(9, "Steve", "Json", 88, R.drawable.boy4,false));
//        players.add(new Player(10, "Even", "Zhao", 78, R.drawable.girl1,false));
//        players.add(new Player(11, "macheal", "Json", 88, R.drawable.boy1,false));
//        players.add(new Player(12, "Efan", "Zhao", 78, R.drawable.girl2,false));
//        players.add(new Player(13, "John", "Json", 88, R.drawable.boy2,false));
//        players.add(new Player(14, "Jenny", "Zhao", 78, R.drawable.girl3,false));
//        players.add(new Player(15, "Laura", "Json", 88, R.drawable.boy3,false));
//        players.add(new Player(16, "Jenna", "Zhao", 78, R.drawable.girl4,false));
//        players.add(new Player(17, "Sohan", "Json", 88, R.drawable.boy4,false));
//        players.add(new Player(18, "macheal", "Json", 88, R.drawable.boy1,false));
//        players.add(new Player(19, "Efan", "Zhao", 78, R.drawable.girl2,false));
//        players.add(new Player(20, "John", "Json", 88, R.drawable.boy2,false));
//        players.add(new Player(21, "Jenny", "Zhao", 78, R.drawable.girl3,false));
//        players.add(new Player(22, "Laura", "Json", 88, R.drawable.boy3,false));
//        players.add(new Player(23, "Jenna", "Zhao", 78, R.drawable.girl4,false));
//        players.add(new Player(24, "Sohan", "Json", 88, R.drawable.boy4,false));
//

* */