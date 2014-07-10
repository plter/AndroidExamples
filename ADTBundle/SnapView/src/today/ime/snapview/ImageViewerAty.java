package today.ime.snapview;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageViewerAty extends Activity{

	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		imageView = new ImageView(this);
		setContentView(imageView);

		String name = getIntent().getStringExtra("name");
		if (name!=null) {
			InputStream in;
			try {
				in = openFileInput(name);
				Bitmap bmp = BitmapFactory.decodeStream(in);
				imageView.setImageBitmap(bmp);
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
