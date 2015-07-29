package com.plter.notes.atys;

import com.plter.notes.R;

class MediaListCellData{
	
	public MediaListCellData(String path) {
		this.path = path;
		
		if (path.endsWith(".jpg")) {
			iconId = R.drawable.icon_photo;
			type = MediaType.PHOTO;
		}else if (path.endsWith(".mp4")) {
			iconId = R.drawable.icon_video;
			type = MediaType.VIDEO;
		}
	}
	
	public MediaListCellData(String path,int id) {
		this(path);
		
		this.id = id;
	}
	
	
	int type = 0;
	int id = -1;
	String path = "";
	int iconId = R.drawable.ic_launcher;
}