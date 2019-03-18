package com.saidelshibiny.badmintonplanningandroidapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
    private static final String DB_TABLE_PLAYER_MATCH = "player_match";

    //Table attributes name
    //1. Common Attributes name
    private static final String KEY_ID = "id";

    //Attributes Name for players table
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";
    private static final String KEY_RANKING = "ranking";
    private static final String KEY_IMAGE_ID = "image_id";

    //Attributes Name for matches table
    private static final String KEY_WIN_SCORE = "win_score";
    private static final String KEY_LOSE_SCORE = "lose_score";
    private static final String KEY_MATCH_TIME = "match_time";

    //Attributes Name for player_matches table
    private static final String KEY_PLAYER_ID = "player_id";
    private static final String KEY_MATCH_ID = "match_id";
    private static final boolean KEY_ISWINNER = false;

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
            + KEY_ISWINNER + " BOOLEAN)";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PLAYERS_TABLE);
        db.execSQL(CREATE_MATCHES_TABLE);
        db.execSQL(CREATE_PLAYER_MATCHES_TABLE);
        addPlayersToTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_PLAYERS);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_MATCHES);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_PLAYER_MATCH);
    }

    private void addMatches(Match match, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(KEY_WIN_SCORE, match.getWinningScore());
        values.put(KEY_LOSE_SCORE, match.getLosingScore());
        //TODO: Debug
//        values.put(KEY_MATCH_TIME, match.getStartTime());

    }

    private void addPlayerMatch(Player_Match player_match, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(KEY_PLAYER_ID, player_match.getPlayerId());
        values.put(KEY_MATCH_ID, player_match.getMatchId());
        //TODO: Debug
//        values.put(KEY_ISWINNER, player_match.isWinner());
    }


    public void addPlayer(Player player, SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, player.getFirstName());
        values.put(KEY_LAST_NAME, player.getLastName());
        values.put(KEY_RANKING, player.getRanking());
        values.put(KEY_IMAGE_ID, player.getImageID());
        db.insert(DB_TABLE_PLAYERS, null, values);
    }


    //insert records to players table
    private void addPlayersToTable(SQLiteDatabase db){
     //   Player p1 = new Player(0, "Sally", "Zhao", 78, "drawable://" + R.drawable.boy1);
    //    this.addPlayer(p1, db);
       // Player p2 = new Player(2, "Mario", "Zhang", 68, "drawable://" + R.drawable.boy1);
     //   this.addPlayer(p2, db);
    }

/*Reading one player table the PLAYER table*/
    public Player getPlayer(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Player player = null;
        String query = "SELECT * FROM " + DB_TABLE_PLAYERS +
                        "WHERE " + KEY_ID + "=" + id;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor != null){
            cursor.moveToFirst();
            player = new Player(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getWantsAllOnMoveCalls()
            );
        }
        db.close();
        return player;
    }


    /*Reading All records from Player table*/
    public ArrayList<Player> getAllPlayers(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Player> playersArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + DB_TABLE_PLAYERS;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                playersArrayList.add(new Player(
                                                cursor.getInt(0),
                                                cursor.getString(1),
                                                cursor.getString(2),
                                                cursor.getInt(3),
                                                cursor.getInt(4),
                                                cursor.getWantsAllOnMoveCalls()
                                                ));
            } while (cursor.moveToNext());
        }
        db.close();
        return  playersArrayList;
    }




}
