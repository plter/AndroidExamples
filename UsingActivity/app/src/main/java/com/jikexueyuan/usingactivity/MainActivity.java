package com.jikexueyuan.usingactivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity{

    private EditText et;
    private Button btn;
    private TextView showView;
    public static final int REQUEST_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText)findViewById(R.id.etc);
        btn = (Button) findViewById(R.id.btn);
        showView = (TextView) findViewById(R.id.showContent);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Act2.class);
                intent.putExtra("key",et.getText().toString());
                startActivityForResult(intent, REQUEST_1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==1){
            switch (requestCode){
                case 1:
                    showView.setText(data.getStringExtra("act2key"));
                    break;
            }
        }

    }
}
