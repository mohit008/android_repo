package com.mohit.program.database.external;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Author @ Mohit Soni on 14-05-2018 03:48 PM.
 */

public class DBConstant  extends SQLiteAssetHelper {
    public  static final int DB_VERSION = 1;
    public  static String DATABASE_NAME = "user.db";
    public  static final String TABLE_USER = "profile";

    // user - columns names
    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";

    public DBConstant(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }
}
