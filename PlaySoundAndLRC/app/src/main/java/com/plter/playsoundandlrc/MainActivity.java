package com.plter.playsoundandlrc;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.plter.lrc.Reader;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer player;
    private TextView tvOut;
    private Reader lrcReader = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        InputStream inputStream = getResources().openRawResource(R.raw.lrc);
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            inputStream.close();

            String content = new String(bytes,"UTF-8");

            lrcReader = new Reader(content);
        } catch (IOException e) {
            e.printStackTrace();
        }


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

                onUpdate();
            }
        }.execute();
    }

    private void onUpdate(){
        int sec = player.getCurrentPosition()/1000;

        if (lrcReader.contains(sec)){
            tvOut.setText(lrcReader.get(sec));
        }
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
