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
    //database name
    private static final String DB_NAME = "riversidebadminton";
    //table names
    private static final String TABLE_PLAYERS = "players";
    private static final String TABLE_MATCHES = "matches";
    private static final String TABLE_PLAYER_MATCH = "player_match";
    private static final String TABLE_USERS = "users";

    //Table attributes name
    //Common Attributes name
    private static final String COLUMN_ID = "id";
    //Attributes Name for players table
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_RANKING = "ranking";
    private static final String COLUMN_IMAGE_ID = "image_id";
    private static final String COLUMN_IS_CHECKED = "is_checked";
    //Attributes Name for matches table
    private static final String COLUMN_WIN_SCORE = "win_score";
    private static final String COLUMN_LOSE_SCORE = "lose_score";
    private static final String COLUMN_MATCH_TIME = "match_time";

    //Attributes Name for player_matches table
    private static final String COLUMN_PLAYER_ID = "player_id";
    private static final String COLUMN_MATCH_ID = "match_id";
    private static final String COLUMN_ISWINNER = "is_winnner";

    // Attributes Name for Users table
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ROLE_ID = "role_id";
    public static final String COLUMN_VERIFY_STRING = "verify_string";
    public static final String COLUMN_ACTIVE = "active";

    /**
     * Create Tables
     */
    public static final String CREATE_PLAYERS_TABLE = "CREATE TABLE " + TABLE_PLAYERS + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_FIRST_NAME + " TEXT,"
            + COLUMN_LAST_NAME + " TEXT,"
            + COLUMN_RANKING + " INTEGER,"
            + COLUMN_IMAGE_ID + " INTEGER,"
            + COLUMN_IS_CHECKED + " BOOLEAN)";

    public static final String CREATE_MATCHES_TABLE = "CREATE TABLE " + TABLE_MATCHES + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_WIN_SCORE + " INTEGER,"
            + COLUMN_LOSE_SCORE + " INTEGER,"
            + COLUMN_MATCH_TIME + " DATE)";

    public static final String CREATE_PLAYER_MATCHES_TABLE = "CREATE TABLE " + TABLE_PLAYER_MATCH + " ("
            + COLUMN_PLAYER_ID + " INTEGER REFERENCES " + TABLE_PLAYERS + "(" + COLUMN_ID + "),"
            + COLUMN_MATCH_ID + " INTEGER REFERENCES " + TABLE_MATCHES + "(" + COLUMN_ID + "),"
            + COLUMN_ISWINNER + " BOOLEAN)";

    public static final String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY,"
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATCHES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER_MATCH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        // recreate the tables if the database version is created
        onCreate(db);
    }

    private void addMatches(Match match, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_WIN_SCORE, match.getWinningScore());
        values.put(COLUMN_LOSE_SCORE, match.getLosingScore());
        values.put(COLUMN_MATCH_TIME, match.getStartTime());
    }

    private void addPlayerMatch(Player_Match player_match, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLAYER_ID, player_match.getPlayerId());
        values.put(COLUMN_MATCH_ID, player_match.getMatchId());
        values.put(COLUMN_ISWINNER, player_match.isWinner());
    }

    // Players CRUD function=============================================================================================
    public void addPlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, player.getFirstName());
        values.put(COLUMN_LAST_NAME, player.getLastName());
        values.put(COLUMN_RANKING, player.getRanking());
        values.put(COLUMN_IMAGE_ID, player.getImageID());
        values.put(COLUMN_IS_CHECKED, player.getChecked());
        db.insert(TABLE_PLAYERS, null, values);
//        long id = db.insert(TABLE_PLAYERS, null, values);
        db.close();
//        return id;
    }

//    //insert records to players table
//    private void addPlayersToTable(SQLiteDatabase db) {
//        Player p1 = new Player("Sohan", "Json", 88, R.drawable.boy4, false);
//        this.addPlayer(p1);
//        Player p2 = new Player("Sally", "Zhao", 78, R.drawable.girl1, false);
//        this.addPlayer(p2);
//    }

    /*Reading one player table the PLAYER table*/
    public Player getPlayer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PLAYERS,
                new String[]{COLUMN_ID, COLUMN_FIRST_NAME, COLUMN_LAST_NAME, COLUMN_RANKING, COLUMN_IMAGE_ID, COLUMN_IS_CHECKED},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if (cursor != null) {cursor.moveToFirst();}
        Player player = new Player(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getInt(4),
                cursor.getWantsAllOnMoveCalls()
        );
        db.close();
        return player;
    }

    /*Reading All records from Player table*/
    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> playersArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_PLAYERS + " ORDER BY " + COLUMN_LAST_NAME + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
//                Player player = new Player();
////                player.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
////                player.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)));
////                player.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)));
////                player.setRanking(cursor.getInt(cursor.getColumnIndex(COLUMN_RANKING)));
////                player.setImageID(cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE_ID)));
////                player.setChecked(cursor.getWantsAllOnMoveCalls());
////                playersArrayList.add(player);
                playersArrayList.add(new Player(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getWantsAllOnMoveCalls()
                        ));
            } while (cursor.moveToNext());
        }
        db.close();
        return playersArrayList;
    }

    public int getPlayerCount() {
        String query = "SELECT * FROM " + TABLE_PLAYERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int getCheckedPlayer() {
        String query = "SELECT * FROM " + TABLE_PLAYERS + " WHERE " + COLUMN_IS_CHECKED + "=true";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int updatePlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RANKING, player.getRanking());
        // updating row
        return db.update(TABLE_PLAYERS, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(player.getId())});
    }

    public void deletePlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLAYERS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(player.getId())});
    }

    public void deleteAllPlayers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLAYERS, null, null);

    }

    public void checkAllPlayers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLAYERS, null, null);

    }

    // Users CRUD functions=============================================================================================

    /**
     * @author Keegan
     * we have two getUser methods
     * each has a different method signatures the first takes in an id
     * the second takes in a username and password
     */
    public User getUser(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;
        Cursor cursor = db.query(TABLE_USERS,
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

    public int updateUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_ROLE_ID, user.getRole_id());
        values.put(COLUMN_VERIFY_STRING, user.getVerify_string());
        values.put(COLUMN_ACTIVE, user.getActive());
        return db.update(TABLE_USERS, values, COLUMN_USER_ID + "= ?",
                new String[]{String.valueOf(user.getUser_id())});
    }









}
