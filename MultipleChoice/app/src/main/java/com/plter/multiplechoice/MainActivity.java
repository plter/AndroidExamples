package com.plter.multiplechoice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbBaoding = (CheckBox) findViewById(R.id.cbBaoding);
        cbHandan = (CheckBox) findViewById(R.id.cbHandan);
        cbShijiazhuang = (CheckBox) findViewById(R.id.cbShijiazhuang);
        cbXingtai = (CheckBox) findViewById(R.id.cbXingtai);
        cbBeijing = (CheckBox) findViewById(R.id.cbBeijing);
        cbWuhan = (CheckBox) findViewById(R.id.cbWuhan);
        cbShanghai = (CheckBox) findViewById(R.id.cbShanghai);
        cbChongqing = (CheckBox) findViewById(R.id.cbChongqing);
        cbLuoyang = (CheckBox) findViewById(R.id.cbLuoyang);

        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbBaoding.isChecked()&&
                        cbHandan.isChecked()&&
                        cbXingtai.isChecked()&&
                        cbShijiazhuang.isChecked()&&
                        !cbBeijing.isChecked()&&
                        !cbWuhan.isChecked()&&
                        !cbLuoyang.isChecked()&&
                        !cbChongqing.isChecked()&&
                        !cbShanghai.isChecked()){

                    Toast.makeText(MainActivity.this, "选择正确", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "选择错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private CheckBox cbShijiazhuang,cbBaoding,cbXingtai,cbHandan,cbBeijing,cbWuhan, cbShanghai,cbChongqing,cbLuoyang;
}
