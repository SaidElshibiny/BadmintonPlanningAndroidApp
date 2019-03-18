package com.saidelshibiny.badmintonplanningandroidapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/*
 * Created by Chaonan Chen on March 9, 2019
 * */

public class DBHelper extends SQLiteOpenHelper {

    //DB version,
    private static final int DB_VERSION = 1;

    //database name
    private static final String DB_NAME = "riversidebadminton";

    //table names
    private static final String DB_TABLE_PLAYERS = "players";
    private static final String DB_TABLE_MATCHES = "matches";
    private static final String DB_TABLE_PLAYER_MATCH = "player_match";
    private static final String DB_TABLE_USERS = "users";

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

    // Users table
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ROLE_ID = "role_id";
    public static final String COLUMN_VERIFY_STRING = "verify_string";
    public static final String COLUMN_ACTIVE = "active";



    /** Create Operations */

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


    // Users Table
    public static final String CREATE_USERS_TABLE = "CREATE TABLE " +
            DB_TABLE_USERS + "(" + COLUMN_USER_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_USERNAME + " VARCHAR," + COLUMN_PASSWORD + " VARCHAR,"
            + COLUMN_ROLE_ID + " INTEGER," + COLUMN_VERIFY_STRING + " VARCHAR,"
            + COLUMN_ACTIVE + " INTEGER)";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PLAYERS_TABLE);
        db.execSQL(CREATE_MATCHES_TABLE);
        db.execSQL(CREATE_PLAYER_MATCHES_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
        addPlayersToTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_PLAYERS);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_MATCHES);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_PLAYER_MATCH);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_USERS);

/**
 * we want to recreate the tables if the database version is created
  */
        db.execSQL(CREATE_PLAYERS_TABLE);
        db.execSQL(CREATE_MATCHES_TABLE);
        db.execSQL(CREATE_PLAYER_MATCHES_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
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


    // Users

    /**
     * @author Keegan
     * we have two getUser methods
     * each has a different method signatures the first takes in an id
     * the second takes in a username and password
     */
    public User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;
        Cursor cursor = db.query(DB_TABLE_USERS,
                new String[]{COLUMN_USER_ID, COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_ROLE_ID,
                        COLUMN_VERIFY_STRING, COLUMN_ACTIVE},
                COLUMN_USER_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            user = new User(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    Integer.parseInt(cursor.getString(3)),
                    cursor.getString(4),
                    Integer.parseInt(cursor.getString(5)));
        }
        db.close();
        return user;
    }

//    public User getUser(String username, String password){
//        SQLiteDatabase db = null;
//        User user = null;
//        Cursor cursor = null;
//        try {
//            db = this.getReadableDatabase();
//            cursor = db.query(DB_TABLE_USERS,
//                    new String[]{COLUMN_USER_ID, COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_ROLE_ID,
//                            COLUMN_VERIFY_STRING, COLUMN_ACTIVE},
//                    COLUMN_USER_ID  + "=?" + " AND " + COLUMN_PASSWORD + "=?",
//                    new String[]{username, password},
//                    null, null, null, "1");
//            if (cursor != null && cursor.moveToFirst()) {
//                user = new User(Integer.parseInt(cursor.getString(0)),
//                        user = new User(Integer.parseInt(cursor.getString(0)),
//                                cursor.getString(1),
//                                cursor.getString(2),
//                                Integer.parseInt(cursor.getString(3)),
//                                cursor.getString(4),
//                                Integer.parseInt(cursor.getString(5)));
//            }
//        }catch (final Exception e){
//            Log.d("DATABASE", "something went wrong");
//        }finally {
//            db.close();
//            cursor.close();
//        }
//        return  user;
//    }


    public int updateUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_ROLE_ID, user.getRole_id());
        values.put(COLUMN_VERIFY_STRING, user.getVerify_string());
        values.put(COLUMN_ACTIVE, user.getActive());
        return db.update(DB_TABLE_USERS, values, COLUMN_USER_ID + "= ?",
                new String[]{String.valueOf(user.getUser_id())});
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
