package com.plter.lib.android.java.controls;

import android.content.Context;

public abstract class HeaderAdapter extends ArrayAdapter<HeaderItemData> {

	public HeaderAdapter(Context context,int headerItemResId) {
		super(context, headerItemResId);
	}
}
