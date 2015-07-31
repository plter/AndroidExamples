package com.jikexueyuan.getphonenum;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private List<PhoneInfo> lists;
	private Context context;
	private LinearLayout layout;

	public MyAdapter(List<PhoneInfo> lists, Context context) {
		this.lists = lists;
		this.context = context;
	}

	@Override
	public int getCount() {
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		layout = (LinearLayout) inflater.inflate(R.layout.cell, null);
		TextView tv1 = (TextView) layout.findViewById(R.id.textView1);
		TextView tv2 = (TextView) layout.findViewById(R.id.textView2);
		tv1.setText(lists.get(position).getName());
		tv2.setText(lists.get(position).getNumber());
		return layout;
	}

}
