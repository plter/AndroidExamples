package com.plter.singlechoice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbA = (RadioButton) findViewById(R.id.rbA);

        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbA.isChecked()){
                    Toast.makeText(MainActivity.this, "选择正确", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "选择错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
