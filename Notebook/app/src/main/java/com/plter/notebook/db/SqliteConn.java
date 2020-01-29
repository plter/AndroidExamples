package com.plter.notebook.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;

public class SqliteConn extends SQLiteOpenHelper {

	public SqliteConn(Context context) {
		super(context, "notes", new SQLiteDatabase.CursorFactory() {
			
			@Override
			public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery,
					String editTable, SQLiteQuery query) {
				return new NotesCursor(db, masterQuery, editTable, query);
			}
		}, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE notes(" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT," +
				"name TEXT DEFAULT \"\"," +
				"content TEXT DEFAULT \"\"," +
				"date TEXT DEFAULT \"\")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
