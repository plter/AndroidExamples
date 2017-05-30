package top.yunp.swipetofresh;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import top.yunp.swipetofresh.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                System.out.println("Refresh start");

                new Thread() {
                    @Override
                    public void run() {
                        super.run();

                        try {
                            sleep(3000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    binding.srl.setRefreshing(false);
                                    System.out.println("Refresh end");
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

    }
}
