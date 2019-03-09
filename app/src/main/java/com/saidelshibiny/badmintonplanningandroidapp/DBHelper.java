package com.saidelshibiny.badmintonplanningandroidapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * Created by Chaonan Chen on March 9, 2019
 * */

public class DBHelper extends SQLiteOpenHelper {

    //DB version,
    private static final int DB_VERSION = 1;
    //databasename
    private static final String DB_NAME = "riversidebadminton";
    //table name
    private static final String DB_TABLE_PLAYERS = "players";
    private static final String DB_TABLE_MATCHES = "matches";
    private static final String DB_TABLE_PLAYER_MATCH = "plaer_match";

    //Table attributes name
    //1. Common Attributes name
    private static final String KEY_ID = "id";

    //Attributes Name for players table
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";
    private static final String KEY_RANKING = "ranking";
    private static final String KEY_IMAGE_ID = "image_id";

    //Attributes Name for matches table
    private static final String KEY_WIN_SCORE= "win_score";
    private static final String KEY_LOSE_SCORE= "lose_score";
    private static final String KEY_MATCH_TIME= "match_time";

    //Attributes Name for player_matches table
    private static final String KEY_PLAYER_ID= "player_id";
    private static final String KEY_MATCH_ID= "match_id";
    private static final boolean KEY_RESULT= false;

    /*
    CRUD operation of the table
     */
    public static final String CREATE_PLAYERS_TABLE = "CREATE TABLE " + DB_TABLE_PLAYERS + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_FIRST_NAME + " TEXT,"
            + KEY_LAST_NAME + " TEXT,"
            + KEY_RANKING + " INTEGER,"
            + KEY_IMAGE_ID + " INTEGER)";

    public static final String CREATE_MATCHES_TABLE = "CREATE TABLE " + DB_TABLE_MATCHES + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_WIN_SCORE + " INTEGER,"
            + KEY_LOSE_SCORE + " INTEGER,"
            + KEY_MATCH_TIME + " DATE)";

    public static final String CREATE_PLAYER_MATCHES_TABLE = "CREATE TABLE " + DB_TABLE_PLAYER_MATCH + " ("
            + KEY_PLAYER_ID + " INTEGER REFERENCES " + DB_TABLE_PLAYERS + "(" + KEY_ID + "),"
            + KEY_MATCH_ID + " INTEGER REFERENCES " + DB_TABLE_MATCHES + "(" + KEY_ID + "),"
            + KEY_RESULT + " BOOLEAN)";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PLAYERS_TABLE);
        db.execSQL(CREATE_MATCHES_TABLE);
        db.execSQL(CREATE_PLAYER_MATCHES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    private void addPlayersToTable(SQLiteDatabase db){

    }
}
