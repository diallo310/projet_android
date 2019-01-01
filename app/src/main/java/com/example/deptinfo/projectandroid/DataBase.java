package com.example.deptinfo.projectandroid;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

public abstract class DataBase {
    public final static int version =1;
    public final static String nom = "database.db";

    protected SQLiteDatabase mDb =null;

    public DataBase(Context context){

    }
}
