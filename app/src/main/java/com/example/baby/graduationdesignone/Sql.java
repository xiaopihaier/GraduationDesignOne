package com.example.baby.graduationdesignone;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by baby on 2017/4/15.
 */

public class Sql extends SQLiteOpenHelper {
    public static final String SQL = "create table Sql("
            + "id integer primary key autoincrement,"
            + "Login_button int ,"
            + "cirImageView int ,"
            + "username int,"
            + "password text)";
    private Context mContext;

    public Sql(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
