package com.jikexueyuan.javacallbackexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.plter.lib.utils.Callater;

public class MainActivity extends AppCompatActivity {

    private TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOutput = (TextView) findViewById(R.id.tvOutput);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Callater.setTimeout(new Callater.Func<String>() {
            @Override
            public void execute(String... args) {
                tvOutput.setText(args[0]+"\n"+args[1]);
            }
        }, 5000, "第一个参数", "第二个参数");
    }
}
