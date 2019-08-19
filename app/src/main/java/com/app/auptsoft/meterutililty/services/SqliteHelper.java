package com.app.auptsoft.meterutililty.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.auptsoft.meterutililty.model.Load;

/**
 * Created by Andrew Oshodin on 6/6/2018.
 */

public class SqliteHelper extends SQLiteOpenHelper {
    public final static int VERSION = 1;
    public final static String DATABASE_NAME = "databaseName";

    private final Load load = new Load();

    private final String CREATE_LOAD_TABLE = load.getCreateTableString();

    public SqliteHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory cursorFactory, int version){
        super(context, databaseName, cursorFactory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_LOAD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + load.getTableName());
    }
}
