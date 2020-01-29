package com.plter.notebook.db;

import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;

public class NotesCursor extends SQLiteCursor {

	public NotesCursor(SQLiteDatabase db, SQLiteCursorDriver driver,
			String editTable, SQLiteQuery query) {
		super(db, driver, editTable, query);
	}
	
	
	public String getString(String name){
		return getString(getColumnIndex(name));
	}
	
	
	public int getInt(String name){
		return getInt(getColumnIndex(name));
	}

}
