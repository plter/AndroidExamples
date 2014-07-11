package today.ime.flashlight;

import com.plter.flashlight.R;

import android.hardware.Camera;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	private OnClickListener btnClickHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnOpenLight:
				openLight();
				break;
			case R.id.btnCloseLight:
				closeLight();
				break;
			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		findViewById(R.id.btnOpenLight).setOnClickListener(btnClickHandler );
		findViewById(R.id.btnCloseLight).setOnClickListener(btnClickHandler);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	private void openLight() {
		if (camera==null) {
			camera = Camera.open();
			Camera.Parameters p = camera.getParameters();
			p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
			camera.setParameters(p);
		}
	}
	
	
	private void closeLight() {
		if (camera!=null) {
			camera.release();
			camera = null;
		}
	}

	private Camera camera=null;
}
