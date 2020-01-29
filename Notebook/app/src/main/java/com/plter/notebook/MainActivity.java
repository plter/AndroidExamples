package com.plter.notebook;

import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.plter.notebook.atys.AddNoteAty;
import com.plter.notebook.atys.EditNoteAty;
import com.plter.notebook.db.NotesCursor;
import com.plter.notebook.db.SqliteConn;

public class MainActivity extends ListActivity {
	
	
	private SimpleCursorAdapter sca;
	private SQLiteDatabase dbRead;
	private SqliteConn sqliteConn;
	
	public static final int REQUEST_CODE_EDIT_NOTE=1;
	public static final int REQUEST_CODE_ADD_NOTE=2;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sqliteConn = new SqliteConn(this);
		dbRead = sqliteConn.getReadableDatabase();
		
		sca = new SimpleCursorAdapter(this, R.layout.notes_list_cell, null, new String[]{"name","date"}, new int[]{R.id.tvName,R.id.tvDate});
		setListAdapter(sca);
		
		showNotes();
		
		findViewById(R.id.btnAddNote).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent(MainActivity.this, AddNoteAty.class), REQUEST_CODE_ADD_NOTE);
			}
		});
	}
	
	
	private void showNotes(){
		sca.changeCursor(dbRead.query("notes", new String[]{"_id","name","content","date"}, null, null, null, null, null));
	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		switch (requestCode) {
		case REQUEST_CODE_EDIT_NOTE:
		case REQUEST_CODE_ADD_NOTE:
			showNotes();
			break;
		default:
			break;
		}
		
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		SimpleCursorAdapter adapter = (SimpleCursorAdapter) getListAdapter();
		NotesCursor cursor = (NotesCursor) adapter.getCursor();
		cursor.moveToPosition(position);
		
		int noteId = cursor.getInt("_id");
		
		Intent i = new Intent(this, EditNoteAty.class);
		i.putExtra(EditNoteAty.EXTRA_KEY_NOTE_ID, noteId);
		startActivityForResult(i, REQUEST_CODE_EDIT_NOTE);
		
		super.onListItemClick(l, v, position, id);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
