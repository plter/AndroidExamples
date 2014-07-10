package today.ime.snapview;

import java.io.OutputStream;

import today.ime.snapshot.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        root = findViewById(R.id.container);
        root.setDrawingCacheEnabled(true);
        
        findViewById(R.id.btnSnap).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Bitmap bmp = root.getDrawingCache();
				
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
			}
		});
    }
}
