package top.yunp.imageviewdatabinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import top.yunp.imageviewdatabinding.databinding.ActivityMainBinding;

public class ActivityMain extends AppCompatActivity {


    private ActivityMainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = new ActivityMainController((ActivityMainBinding) DataBindingUtil.setContentView(this, R.layout.activity_main));
    }
}
