package today.ime.snapvideo;

import java.io.File;
import java.io.OutputStream;

import today.ime.snapsurfaceview.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		root = findViewById(R.id.root);
		
		videoUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "video.mp4"));

		videoView = (VideoView) findViewById(R.id.vv);
		videoView.setMediaController(new MediaController(this));
		videoView.setVideoURI(videoUri);
		
		root.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MediaMetadataRetriever retriever = new MediaMetadataRetriever();
				retriever.setDataSource(MainActivity.this, videoUri);
				Bitmap bmp = retriever.getFrameAtTime(videoView.getCurrentPosition()*1000);
				
				try {
					OutputStream out = openFileOutput("snapshot", Context.MODE_PRIVATE);
					bmp.compress(CompressFormat.JPEG, 100, out);
					out.close();

					Intent i = new Intent(MainActivity.this, ImageViewerAty.class);
					i.putExtra("name", "snapshot");
					startActivity(i);
				} catch (Exception e) {
					e.printStackTrace();

					Toast.makeText(MainActivity.this, "Fail to snap!", Toast.LENGTH_SHORT).show();
				}
				retriever.release();
			}
		});
		
		Toast.makeText(this, "Click to snap the video screen", Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		videoView.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		videoView.stopPlayback();
	}
	
	private VideoView videoView;
	private View root;
	private Uri videoUri;
}
