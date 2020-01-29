package com.plter.notebook.atys;

import java.util.Date;

import com.plter.notebook.R;
import com.plter.notebook.db.NotesCursor;
import com.plter.notebook.db.SqliteConn;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditNoteAty extends Activity {

	
	
	public static final String EXTRA_KEY_NOTE_ID="noteId";
	
	private SqliteConn conn;
	private SQLiteDatabase dbRead,dbWrite;
	private EditText etTitle,etContent;
	private int noteId=-1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_note);
		
		etContent = (EditText) findViewById(R.id.etContent);
		etTitle = (EditText) findViewById(R.id.etTitle);
		
		noteId = getIntent().getIntExtra(EXTRA_KEY_NOTE_ID, -1);
		if (noteId<0) {
			Toast.makeText(this, "无效的日志id", Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
		
		
		conn = new SqliteConn(this);
		dbRead = conn.getReadableDatabase();
		dbWrite = conn.getWritableDatabase();
		
		showNote();
		
		findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Editable title = etTitle.getText();
				if (TextUtils.isEmpty(title)) {
					Toast.makeText(EditNoteAty.this, "标题不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				Editable content = etContent.getText();
				if (TextUtils.isEmpty(content)) {
					Toast.makeText(EditNoteAty.this, "内容不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				
				ContentValues cv = new ContentValues();
				cv.put("name", title.toString());
				cv.put("content", content.toString());
				cv.put("date", new Date().toGMTString());
				
				dbWrite.update("notes", cv, "_id=?", new String[]{noteId+""});
				
				finish();
			}
		});
	}
	
	
	public void showNote(){
		NotesCursor c = (NotesCursor) dbRead.query("notes", new String[]{"name","content"}, "_id=?", new String[]{noteId+""}, null, null, null);
		if (c.getCount()>=1) {
			c.moveToPosition(0);
			
			etTitle.setText(c.getString("name"));
			etContent.setText(c.getString("content"));
		}
	}
	
	
	@Override
	protected void onDestroy() {
		dbRead.close();
		dbWrite.close();
		super.onDestroy();
	}
}
