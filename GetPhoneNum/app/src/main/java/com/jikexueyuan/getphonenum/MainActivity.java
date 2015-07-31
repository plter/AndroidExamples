package com.jikexueyuan.getphonenum;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends Activity {

	private TextView tv;
	private ListView lv;
	private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyNumber.GetNumber(this);
        
        lv = (ListView) findViewById(R.id.listView1);
        adapter = new MyAdapter(MyNumber.lists, this);
        lv.setAdapter(adapter);
        
    }
}
