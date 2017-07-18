package com.tin.chigua.download.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tin.chigua.LUtil;
import com.tin.chigua.MyApplication;
import com.tin.chigua.download.bean.ThreadInfo;

/**
 * Created by hasee on 7/18/2017.
 */

public class DBHelperDao {

    private static final String TABLE_NAME = MyDBHelper.TABLE_NAME;

    private SQLiteDatabase mDatabase;
    private Context mContext;

    private DBHelperDao(){
        mContext = MyApplication.getContext();
        mDatabase = new MyDBHelper(mContext).getWritableDatabase();
    }

    private static class DBDaoHolder{
        private static final DBHelperDao INSTANCE = new DBHelperDao();
    }

    public static DBHelperDao getInstance(){
        return DBDaoHolder.INSTANCE;
    }

    public SQLiteDatabase getDatabase(){
        return mDatabase;
    }

    public synchronized void insert(ThreadInfo threadInfo){
        ContentValues values = new ContentValues();
        values.put("threadId",threadInfo.getThreadId());
        values.put("start",threadInfo.getStart());
        values.put("end",threadInfo.getEnd());
        values.put("completed",threadInfo.getCompleted());
        values.put("url",threadInfo.getUrl());
        long rowId = mDatabase.insert(TABLE_NAME,null,values);
        if(rowId!=-1){
            LUtil.i("插入线程记录成功");
        }else{
            LUtil.i("插入线程记录失败");
        }
    }

    public synchronized void delete(String threadId,String url){
        long rowId = mDatabase.delete(TABLE_NAME,"url =? and threadId =?",
                new String[]{url, threadId});
        if(rowId!=-1){
            LUtil.i("删除下载线程记录成功");
        }else{
            LUtil.i("删除下载线程记录失败");
        }
    }

    public synchronized void delete(String url){
        long rowId = mDatabase.delete(TABLE_NAME,"url =?", new String[]{url});
        if(rowId!=-1){
            LUtil.i("删除下载线程记录成功");
        }else{
            LUtil.i("删除下载线程记录失败");
        }
    }

    public synchronized void update(ThreadInfo threadInfo){
        ContentValues values = new ContentValues();
        values.put("start",threadInfo.getStart());
        values.put("completed",threadInfo.getCompleted());
        long rowId = mDatabase.update(TABLE_NAME, values, "url =? and threadId =?",
                new String[]{threadInfo.getUrl(),threadInfo.getThreadId()});
        if(rowId!=-1){
            LUtil.i("更新下载线程记录成功");
        }else{
            LUtil.i("更新下载线程记录失败");
        }
    }

    public synchronized ThreadInfo query(String threadId,String queryUrl){
        Cursor cursor = mDatabase.query(TABLE_NAME, null, "url =? and threadId =?",
                new String[]{queryUrl, threadId}, null, null, null);
        ThreadInfo info = new ThreadInfo();
        if(cursor != null){
            while (cursor.moveToNext()){
                info.setThreadId(threadId);
                info.setStart(cursor.getInt(2));
                info.setEnd(cursor.getInt(3));
                info.setCompleted(cursor.getInt(4));
                info.setUrl(cursor.getString(5));
            }
            cursor.close();
        }
        return info;
    }

    public void close(){
        mDatabase.close();
    }

    public boolean isExist(String url){
        Cursor cursor = mDatabase.query(TABLE_NAME, null, "url =?",
                new String[]{url}, null, null, null);
        boolean isExist =  cursor.moveToNext();
        cursor.close();
        return isExist;
    }

}
