package com.plter.notes.data;

import java.io.File;

import android.os.Environment;

public class Config {

	public static File getPlterDir(){
		if (plterDir==null) {
			plterDir = new File(Environment.getExternalStorageDirectory(),"plter");
		}
		if (!plterDir.exists()) {
			plterDir.mkdir();
		}
		return plterDir;
	}

	public static File getProjectDir(){
		if (projectDir==null) {
			projectDir = new File(getPlterDir(), "notes");
		}
		if (!projectDir.exists()) {
			projectDir.mkdir();
		}
		return projectDir;
	}

	public static File getProjectMediaDir(){
		if (projectMediaDir==null) {
			projectMediaDir = new File(getProjectDir(), "media");
		}
		if (!projectMediaDir.exists()) {
			projectMediaDir.mkdir();
		}
		return projectMediaDir;
	}

	private static File projectMediaDir = null;
	private static File projectDir=null;
	private static File plterDir=null;
}
