package com.plter.playsoundandlrc;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer player;
    private TextView tvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOut = (TextView) findViewById(R.id.tvOut);
        player = MediaPlayer.create(this, R.raw.sound);
    }

    @Override
    protected void onResume() {
        super.onResume();


        player.start();

        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                while (player!=null&&player.isPlaying()){
                    publishProgress();

                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }

                return null;
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);

                tvOut.setText(String.format("已播放%d毫秒", player.getCurrentPosition()));
            }
        }.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();

        player.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        player.release();
        player = null;
    }
}
