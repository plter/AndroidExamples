package com.plter.guessnumber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private int theNum = 0;
    private EditText etInputNumber = null;
    private TextView tvMsgOut = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restartGame();

        etInputNumber = (EditText) findViewById(R.id.etNumberInput);
        tvMsgOut = (TextView) findViewById(R.id.tvMsgOutput);
        findViewById(R.id.btnSubmit).setOnClickListener(this);
        findViewById(R.id.btnRestart).setOnClickListener(this);
    }

    private void submitBtnClickedHandler() {
        if (!TextUtils.isEmpty(etInputNumber.getText())){
            try {
                int userInputNumber = Integer.parseInt(etInputNumber.getText().toString());
                if (userInputNumber>theNum){
                    tvMsgOut.setText(R.string.the_number_input_is_bigger);
                }else if (userInputNumber<theNum){
                    tvMsgOut.setText(R.string.the_number_input_is_smaller);
                }else {
                    tvMsgOut.setText(R.string.right);
                }
            }catch (RuntimeException e){
                Toast.makeText(MainActivity.this, R.string.the_input_data_is_not_number_format, Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(MainActivity.this, R.string.please_input_number_in_textfield, Toast.LENGTH_SHORT).show();
        }
    }

    private void restartGame(){
        theNum = (int) (Math.random()*100);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSubmit:
                submitBtnClickedHandler();
                break;
            case R.id.btnRestart:
                restartGame();
                break;
        }
    }
}
