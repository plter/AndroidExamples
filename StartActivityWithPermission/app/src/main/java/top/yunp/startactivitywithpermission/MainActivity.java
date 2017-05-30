package top.yunp.startactivitywithpermission;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import top.yunp.startactivitywithpermission.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new Presenter(this);
        binding.setPresenter(presenter);
    }
}
