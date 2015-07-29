package com.plter.lib.android.java.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class Header extends GridView {

	public Header(Context context,int itemResId) {
		super(context);
		
		initProperties();
	}
	
	public Header(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		initProperties();
	}


	public Header(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		initProperties();
	}


	private void initProperties(){
		setNumColumns(5);
	}
}
