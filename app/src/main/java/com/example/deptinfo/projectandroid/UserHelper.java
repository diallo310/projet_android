package com.example.deptinfo.projectandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class UserHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="database.db";
    public static final String USER_TABLE_NAME ="Users";
    public static final String USER_EMAIL="email";
    public static final String USER_NAME="username";
    public static final String USER_PASSWORD ="password";
    public static final String USER_KEY ="id";

    public static final String USERS_TABLE_CREATE = "CREATE TABLE  "+USER_TABLE_NAME +
            "("+USER_KEY+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                USER_EMAIL+" TEXT, "+
                USER_NAME+" TEXT, "+
                USER_PASSWORD+" TEXT );";

   // public static final String  USER_DROP_TABLE ="DROP IF EXISTS"+USER_TABLE_NAME+";";

    public UserHelper(Context context) {
        super(context, DATABASE_NAME, null ,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USERS_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL(USER_DROP_TABLE);
        onCreate(db);
    }

    public boolean insertUser(String email,String password,String username){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("username",username);
        contentValues.put("password",password);
        db.insert(USER_TABLE_NAME,null,contentValues);
        return true;
    }

    public Cursor findUser(String email,String password){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor res  = db.rawQuery("select * from Users where email = '"+email+"'and password ='"+password+"'",null);
        return res;
    }

    public Cursor findEmail(String email){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor res  = db.rawQuery("select * from Users where email = '"+email+"'",null);
        return res;
    }

}
