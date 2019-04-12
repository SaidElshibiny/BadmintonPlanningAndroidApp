package com.badminton.riversidesports.Database;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Keegan on 2019-03-13.
 * In this class, we will store information related to the com.saidelshibiny.badmintonplanningandroidapp.Database.User
 */

public class User implements Parcelable {

    private int user_id;
    private String username;
    private String password;
    private int role_id;
    private String verify_string;
    private int active;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int user_id, String username, String password, int role_id, String verify_string, int active) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role_id = role_id;
        this.verify_string = verify_string;
        this.active = active;
    }

    protected User(Parcel in) {
        user_id = in.readInt();
        username = in.readString();
        password = in.readString();
        role_id = in.readInt();
        verify_string = in.readString();
        active = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getVerify_string() {
        return verify_string;
    }

    public void setVerify_string(String verify_string) {
        this.verify_string = verify_string;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(user_id);
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeInt(role_id);
        parcel.writeString(verify_string);
        parcel.writeInt(active);
    }
}


