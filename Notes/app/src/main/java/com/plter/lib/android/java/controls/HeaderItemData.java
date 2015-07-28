package com.plter.lib.android.java.controls;

import android.content.Context;

public class HeaderItemData {

	public HeaderItemData(Context context,String label,int iconId,int action) {
		initProperties(context, label, iconId, action);
	}
	
	public HeaderItemData(Context context,int labelRes,int iconId,int action) {
		initProperties(context, context.getString(labelRes), iconId, action);
	}
	
	
	public void initProperties(Context context,String label,int iconId,int action) {
		setIconId(iconId);
		setLabel(label);
		setAction(action);
		setContext(context);
	}
	
	
	public int getIconId() {
		return iconId;
	}
	private void setIconId(int iconId) {
		this.iconId = iconId;
	}


	public String getLabel() {
		return label;
	}
	private void setLabel(String label) {
		this.label = label;
	}


	public int getAction() {
		return action;
	}

	private void setAction(int action) {
		this.action = action;
	}


	public Context getContext() {
		return context;
	}

	private void setContext(Context context) {
		this.context = context;
	}


	private String label=null;
	private int iconId=0;
	private int action=0;
	private Context context=null;
}
