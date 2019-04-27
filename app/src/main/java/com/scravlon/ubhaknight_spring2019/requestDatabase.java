package com.scravlon.ubhaknight_spring2019;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class requestDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "requestData.db";
    public static final String REQUEST_TABLE_NAME = "requests";
    public static final String REQUEST_ID = "id";
    public static final String REQUEST_ITEMS = "itemname";      //CSV
    public static final String REQUEST_USER = "requser";
    public static final String REQUEST_STATUS = "reqstatus";



    public requestDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    public requestDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "
                + REQUEST_TABLE_NAME + " (" +
                REQUEST_ID + " INTEGER PRIMARY KEY, " +
                REQUEST_ITEMS + " TEXT NOT NULL, " +
                REQUEST_USER + " TEXT NOT NULL, " +
                REQUEST_STATUS + " INTEGER NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    /**
     * Insert a new User
     * @param name: username of the user
     * @param password: Password of user after encryption
     * @return: boolean of the status of adding
     */
    public boolean insertUser (String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(REQUEST_ID, getCount());
        contentValues.put(REQUEST_ITEMS, name);
        contentValues.put(REQUEST_STATUS, password);
        db.insert("contacts", null, contentValues);
        return true;
    }

    /**
     * Check if the user
     * @param username
     * @param password
     * @return -2 if user not found, -1 if user Not found else user id
     */
    public int queryPassword(String username, String password){
        Cursor c = getData(username);
        if(c == null){
            return -2;
        }
        c.moveToFirst();
        String passwordRef = c.getString(c.getColumnIndex(REQUEST_STATUS));
        if(password.equals(passwordRef)){
            return c.getInt((int)c.getColumnIndex(REQUEST_ID));
        } else {return -1;}
    }

    public boolean checkUser(String username, String password){
        Cursor c = getData(username);
        if(c == null || c.getCount() ==0){
            return false;
        }
        c.moveToFirst();
        if(c.getString(c.getColumnIndex(REQUEST_STATUS)).equals(password)){
            return true;
        }
        return false;
    }

    public boolean checkUserDB(String username, String password){
        Cursor c = getData(username);
        if(c == null || c.getCount() ==0){
            return false;
        }
        c.moveToFirst();
        if(c.getString(c.getColumnIndex(REQUEST_ITEMS)).equals(username)){
            return true;
        }
        return false;
    }

    void addContact(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(REQUEST_ID, getCount());
        values.put(REQUEST_ITEMS, username);
        values.put(REQUEST_STATUS, password);
        db.insert(REQUEST_TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    public Cursor getData(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        if(getCount()==0){
            return null;
        }
        Cursor res =  db.rawQuery( "SELECT * FROM " + REQUEST_TABLE_NAME + " WHERE " + REQUEST_ITEMS + "=\"" +username+"\"", null );
        return res;
    }

    public String[] strArray(String username){
        Cursor c = getData(username);
        if(c == null){
            return null;
        }
        c.moveToFirst();
        String[] retVal = new String[2];
        retVal[0] = c.getString(c.getColumnIndex(REQUEST_ITEMS));
        retVal[1] = c.getString(c.getColumnIndex(REQUEST_ID));
        return retVal;
    }

    /**
     * Get number of row in the Database
     * @return: Int of row in database
     */
    public int getCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, REQUEST_TABLE_NAME);
        return numRows;
    }

    public void delete(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(REQUEST_TABLE_NAME, null, null);    }

    public Cursor getAllRecords(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + REQUEST_TABLE_NAME;
        return db.rawQuery(query, null);
    }
}
