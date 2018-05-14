package com.mohit.program.database.external;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Author @ Mohit Soni on 14-05-2018 03:48 PM.
 */

public class DBManager {

    public final Context _context;
    private SQLiteDatabase _sqliteDB;
    private DBConstant openHelper;
    private static DBManager instance;

    public DBManager(Context context) {
        this._context = context;
        this.openHelper = new DBConstant(_context);
    }

    public static DBManager getInstance(Context context) {
        if (instance == null) {
            instance = new DBManager(context);
        }
        return instance;
    }

    /**
     * Open the database
     */
    public DBManager open() throws SQLException {
        _sqliteDB = openHelper.getWritableDatabase();
        return this;
    }

    /**
     * Close the database
     */
    public void close() {
        if (openHelper != null)
            openHelper.close();
    }

    public Cursor getData(String id) {
        Cursor cursor = null;
//        cursor = _sqliteDB.rawQuery("select " + DBConstant.USER_NAME + " from " + DBConstant.TABLE_USER + " where " + DBConstant.USER_ID + "=?", new String[]{id});
        return cursor;
    }
}
