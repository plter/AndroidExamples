package com.jikexueyuan.smsboradcastreceiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView showView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showView = (TextView) findViewById(R.id.showContent);
        Intent intent = getIntent();
        if (intent != null) {
            String address = intent.getStringExtra("sms_address");
            if (address != null) {
                showView.append("\n\n发件人：\n" + address);
                String bodyString = intent.getStringExtra("sms_body");
                if (bodyString != null) {
                    showView.append("\n短信内容：\n" + bodyString);
                }
            }
        }

    }
}