package com.example.jaykang.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.jaykang.androidlabs.ChatWindow.ACTIVITY_NAME;


    public class ChatDatabaseHelper extends SQLiteOpenHelper {

        protected static final String ACTIVITY_NAME = "ChatDatabaseHelper";
        private static final String DATABASE_NAME = "Messages.db";
        protected static final String TABLE_NAME = "chatData";
        private static final int DATABASE_VERSION =2;
        protected static final String KEY_ID  = "keyId";
        protected static final String KEY_MESSAGE  = "message";

        public ChatDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db){
         Log.i(ACTIVITY_NAME, "Calling onCreate");
         String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("
                 +KEY_ID+ "TEXT, "
                 +KEY_MESSAGE+ "TEXT )";
         db.execSQL(CREATE_TABLE);
         Log.i(ACTIVITY_NAME,"Calling version()"+db.getVersion());
         Log.i(ACTIVITY_NAME,"Calling onCreate()");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.i(ChatDatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        if (!(TABLE_NAME.isEmpty())){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
            Log.i(ACTIVITY_NAME, "Calling onUpgrade, oldVersion ="+ oldVersion +"newVersion"+ newVersion);
        }

    }
//
//    public void cursor query(){
//
//        while(!cursor.isAfterLast() )
//            Log.i(ACTIVITY_NAME, "SQL MESSAGE:"+
//                cursor.getString( cursor.getColumnIndex( ChatDatabaseHelper.KEY_MESSAGE)));
//
//    }
//
//    public void onDestroy(){
//
//    }


}
