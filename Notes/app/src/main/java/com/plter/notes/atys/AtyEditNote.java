package com.plter.notes.atys;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.plter.lib.android.java.controls.ArrayAdapter;
import com.plter.notes.R;
import com.plter.notes.data.Config;
import com.plter.notes.data.NotesDB;

public class AtyEditNote extends ListActivity {

	
	private OnClickListener btnClickHandler=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i;File f;
			
			switch (v.getId()) {
			case R.id.btnAddPhoto:
				
				i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				f = new File(Config.getProjectMediaDir(), System.currentTimeMillis()+".jpg");
				if (!f.exists()) {
					try {
						f.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				currentPath = f.getAbsolutePath();
				i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
				startActivityForResult(i, REQUEST_CODE_GET_PHOTO);
				break;
			case R.id.btnAddVideo:
				
				i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				f = new File(Config.getProjectMediaDir(), System.currentTimeMillis()+".mp4");
				if (!f.exists()) {
					try {
						f.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				currentPath = f.getAbsolutePath();
				i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
				
				startActivityForResult(i, REQUEST_CODE_GET_VIDEO);
				break;
			case R.id.btnSave:
				saveMedia(saveNote());
				setResult(RESULT_OK);
				finish();
				break;
			case R.id.btnCancel:
				setResult(RESULT_CANCELED);
				finish();
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_eidt_note);
		
		db = new NotesDB(this);
		dbRead = db.getReadableDatabase();
		dbWrite = db.getWritableDatabase();
		
		adapter = new ArrayAdapter<MediaListCellData>(this,R.layout.media_list_cell) {
			
			@Override
			public void initListCell(int position, View listCell, ViewGroup parent) {
				MediaListCellData data = getItem(position);
				
				ImageView ivIcon = (ImageView) listCell.findViewById(R.id.ivIcon);
				TextView tvPath = (TextView) listCell.findViewById(R.id.tvPath);
				
				ivIcon.setImageResource(data.iconId);
				tvPath.setText(data.path);
			}
		};
		setListAdapter(adapter);
		
		etName = (EditText) findViewById(R.id.etName);
		etContent = (EditText) findViewById(R.id.etContent);
		
		noteId = getIntent().getIntExtra(EXTRA_NOTE_ID, -1);
		
		if (noteId>-1) {
			etName.setText(getIntent().getStringExtra(EXTRA_NOTE_NAME));
			etContent.setText(getIntent().getStringExtra(EXTRA_NOTE_CONTENT));
			
			Cursor c = dbRead.query(NotesDB.TABLE_NAME_MEDIA, null, NotesDB.COLUMN_NAME_MEDIA_OWNER_NOTE_ID+"=?", new String[]{noteId+""}, null, null, null);
			while(c.moveToNext()){
				adapter.add(new MediaListCellData(c.getString(c.getColumnIndex(NotesDB.COLUMN_NAME_MEDIA_PATH)),c.getInt(c.getColumnIndex(NotesDB.COLUMN_NAME_ID))));
			}
			adapter.notifyDataSetChanged();
		}
		
		findViewById(R.id.btnSave).setOnClickListener(btnClickHandler);
		findViewById(R.id.btnCancel).setOnClickListener(btnClickHandler);
		findViewById(R.id.btnAddPhoto).setOnClickListener(btnClickHandler);
		findViewById(R.id.btnAddVideo).setOnClickListener(btnClickHandler);
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		MediaListCellData data = adapter.getItem(position);
		Intent i;
		
		switch (data.type) {
		case MediaType.PHOTO:
			i = new Intent(Intent.ACTION_VIEW);
			i.setDataAndType(Uri.fromFile(new File(data.path)), "image/jpeg");
			startActivity(i);
			break;
		case MediaType.VIDEO:
			i = new Intent(Intent.ACTION_VIEW);
			i.setDataAndType(Uri.fromFile(new File(data.path)), "video/mp4");
			startActivity(i);
			break;
		}
		
		super.onListItemClick(l, v, position, id);
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		System.out.println(data);
		
		switch (requestCode) {
		case REQUEST_CODE_GET_PHOTO:
		case REQUEST_CODE_GET_VIDEO:
			if (resultCode==RESULT_OK) {
				adapter.add(new MediaListCellData(currentPath));
				adapter.notifyDataSetChanged();
			}
			break;
		default:
			break;
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	public void saveMedia(int noteId){
		
		MediaListCellData data;
		ContentValues cv;
		
		for (int i = 0; i < adapter.getCount(); i++) {
			data = adapter.getItem(i);
			
			if (data.id<=-1) {
				cv = new ContentValues();
				cv.put(NotesDB.COLUMN_NAME_MEDIA_PATH, data.path);
				cv.put(NotesDB.COLUMN_NAME_MEDIA_OWNER_NOTE_ID, noteId);
				dbWrite.insert(NotesDB.TABLE_NAME_MEDIA, null, cv);
			}
		}
		
	}
	
	@SuppressLint("SimpleDateFormat")
	public int saveNote(){
		
		ContentValues cv = new ContentValues();
		cv.put(NotesDB.COLUMN_NAME_NOTE_NAME, etName.getText().toString());
		cv.put(NotesDB.COLUMN_NAME_NOTE_CONTENT, etContent.getText().toString());
		cv.put(NotesDB.COLUMN_NAME_NOTE_DATE, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		
		if (noteId>-1) {
			dbWrite.update(NotesDB.TABLE_NAME_NOTES, cv, NotesDB.COLUMN_NAME_ID+"=?", new String[]{noteId+""});
			return noteId;
		}else{
			return (int) dbWrite.insert(NotesDB.TABLE_NAME_NOTES, null, cv);
		}
	}
	
	
	@Override
	protected void onDestroy() {
		dbRead.close();
		dbWrite.close();
		super.onDestroy();
	}
	
	
	private int noteId = -1;
	private EditText etName,etContent;
	private ArrayAdapter<MediaListCellData> adapter;
	private NotesDB db;
	private SQLiteDatabase dbRead,dbWrite;
	private String currentPath = null;
	
	public static final int REQUEST_CODE_GET_PHOTO = 1;
	public static final int REQUEST_CODE_GET_VIDEO = 2;
	
	
	public static final String EXTRA_NOTE_ID = "noteId";
	public static final String EXTRA_NOTE_NAME = "noteName";
	public static final String EXTRA_NOTE_CONTENT = "noteContent";
}
