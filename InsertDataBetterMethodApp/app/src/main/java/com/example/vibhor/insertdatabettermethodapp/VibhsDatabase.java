package com.example.vibhor.insertdatabettermethodapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class VibhsDatabase {

    VibhsOpenHelper vibhsOpenHelper;

    VibhsDatabase(Context context) {
        vibhsOpenHelper = new VibhsOpenHelper(context);

    }

    public long insertData(String USERNAME, String PASSWORD) {
        SQLiteDatabase sqLiteDatabase = vibhsOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(vibhsOpenHelper.USERNAME, USERNAME);
        contentValues.put(vibhsOpenHelper.PASSWORD, PASSWORD);
        long id = sqLiteDatabase.insert(vibhsOpenHelper.DATABASE_NAME, null, contentValues);
        return id;
    }

    public String getAllData() {

        String[] columns = {vibhsOpenHelper.UID, vibhsOpenHelper.USERNAME, vibhsOpenHelper.PASSWORD};
        SQLiteDatabase sqLiteDatabase = vibhsOpenHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(vibhsOpenHelper.TABLE_NAME, columns, null, null, null, null, null);

        StringBuffer stringBuffer=new StringBuffer();

        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(vibhsOpenHelper.UID);
            int cid = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(vibhsOpenHelper.USERNAME);
            String cusername = cursor.getString(index2);
            int index3 = cursor.getColumnIndex(vibhsOpenHelper.PASSWORD);
            String cpassword = cursor.getString(index3);

            stringBuffer.append(cid +" "+cusername + " " + cpassword );

        }
        return  stringBuffer.toString();
    }
        

    static public class VibhsOpenHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "VIBHS_DATABASE";
        private static final String TABLE_NAME = "VIBHS_TABLE";
        public static final String USERNAME = "USERNAME";
        public static final String PASSWORD = "PASSWORD";
        private static final String UID = "_id";
        private static final int DATABASE_VERSION = 1;
        public static final String CREATE_TABLE = "CREATE TABLE  " + TABLE_NAME + "( " + UID + "INTEGER PRIMARY KEY AUTO_INCREMENT" +
                " , " + USERNAME + " VARCHAR(255)" + PASSWORD + "PASSWORD VARCHAR(255))";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        Context context;

        public VibhsOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }


        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                Message.message(context, "onCreate metrhod called");
                db.execSQL(CREATE_TABLE);
            } catch (SQLException e) {
                Message.message(context, "Something went wrong while creation");
            }

        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
                Message.message(context, "DATABASE HAVE BEEN UPGRADED");
            } catch (SQLException e) {
                Message.message(context, "something went wrong while upgradiing the database");
            }


        }
    }
}
