package com.jikexueyuan.usingactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.jikexueyuan.usingactivity.R.id.act2btn;

public class Act2 extends AppCompatActivity {


    private EditText et;
    private TextView showview;
    private Button btn;
    public static final int REQUEST_ACT2 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);

        et = (EditText) findViewById(R.id.act2etc);
        showview = (TextView) findViewById(R.id.act2View);
        btn = (Button) findViewById(act2btn);

        String val = getIntent().getStringExtra("key");
        showview.setText("Hello:"+val);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("act2key", et.getText().toString());
                setResult(REQUEST_ACT2, intent);
                finish();
            }
        });
    }

}
