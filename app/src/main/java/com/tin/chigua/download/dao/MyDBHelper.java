package com.tin.chigua.download.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hasee on 7/4/2017.
 */

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME="downthread";
    private static final String CREATE_TABLE_SQL="create table "+TABLE_NAME+"(_id integer primary "
            +"key autoincrement, threadId, start , end, completed, url)";
    private static final int VERSION = 1;


    public MyDBHelper(Context context){
        super(context,TABLE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists downlog";
        db.execSQL(sql);
    }
}
