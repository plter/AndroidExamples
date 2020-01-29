package com.plter.notebook.atys;

import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.plter.notebook.R;
import com.plter.notebook.db.SqliteConn;

public class AddNoteAty extends Activity {

	private SqliteConn conn;
	private SQLiteDatabase dbWrite;
	private EditText etTitle,etContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_note);
		
		conn = new SqliteConn(this);
		dbWrite = conn.getWritableDatabase();
		
		etTitle = (EditText) findViewById(R.id.etTitle);
		etContent = (EditText) findViewById(R.id.etContent);
		
		findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Editable title = etTitle.getText();
				
				if(TextUtils.isEmpty(title)){
					Toast.makeText(AddNoteAty.this, "标题不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				
				Editable content = etContent.getText();
				if(TextUtils.isEmpty(content)){
					Toast.makeText(AddNoteAty.this, "内容不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				
				ContentValues cv = new ContentValues();
				cv.put("name", title.toString());
				cv.put("content", content.toString());
				cv.put("date", new Date().toGMTString());
				dbWrite.insert("notes", null, cv);
				
				finish();
			}
		});
	}
	
	
	@Override
	protected void onDestroy() {
		dbWrite.close();
		super.onDestroy();
	}
}
