package com.mohit.program.database.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Author @ Mohit Soni on 14-05-2018 12:44 PM.
 */

public class DataBaseOperation {

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";

    private static final String TAG = "Database";
    private DatabaseHelper DbHelper;
    private SQLiteDatabase Db;

    private static final String DATABASE_NAME = "MyDB";
    private static final String DATABASE_TABLE = "MapDetail";
    private static final int DATABASE_VERSION = 1;

    private final Context Ctx;

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + DATABASE_TABLE + " (" +
                    ID + " integer PRIMARY KEY autoincrement," +
                    NAME + "," +
                    SURNAME + ");";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            log(DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            log("Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public DataBaseOperation(Context ctx) {
        this.Ctx = ctx;
    }

    /**
     * open data base
     *
     * @return
     * @throws SQLException
     */
    public DataBaseOperation open() throws SQLException {
        DbHelper = new DatabaseHelper(Ctx);
        Db = DbHelper.getWritableDatabase();
        return this;
    }

    /**
     * close data base
     */
    public void close() {
        if (DbHelper != null) {
            DbHelper.close();
        }
    }

    public long createDetail(String name, String surname) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(NAME, name);
        initialValues.put(SURNAME, surname);
        return Db.insert(DATABASE_TABLE, null, initialValues);
    }

    public int updateDetail(String name, String surname) {
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        return Db.update(DATABASE_TABLE,
                values, SURNAME + "=?",
                new String[]{surname});
    }

    public boolean deleteAllDetail() {
        int doneDelete = 0;
        doneDelete = Db.delete(DATABASE_TABLE, null, null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }


    public boolean deleteDetail(String name) {
        open();
        boolean delete = Db.delete(DATABASE_TABLE, NAME + "= '" + name + "'", null) > 0;
        close();
        return delete;
    }


    public Cursor fetchAllDetail() {
        open();
        Cursor mCursor = Db.query(DATABASE_TABLE, new String[]{ID, NAME, SURNAME},
                null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        close();
        return mCursor;
    }

    public void insertSomeDetail(String name, String surname) {
        open();
        createDetail(name, surname);
        close();
    }

    public static void log(String msg) {
        Log.w(TAG, msg);
    }
}
