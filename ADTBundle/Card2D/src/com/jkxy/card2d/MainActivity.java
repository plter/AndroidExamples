package com.jkxy.card2d;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;


public class MainActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initProperties();

		findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (imageA.getVisibility()==View.VISIBLE) {
					imageA.startAnimation(saTo0);
				}else{
					imageB.startAnimation(saTo0);
				}
			}
		});
	}

	private void initProperties(){
		
		imageA = (ImageView) findViewById(R.id.ivA);
		imageB = (ImageView) findViewById(R.id.ivB);
		showImageA();
		
		
		saTo0.setDuration(350);
		saTo1.setDuration(350);

		saTo0.setAnimationListener(new Animation.AnimationListener(){

			@Override
			public void onAnimationEnd(Animation arg0) {
				if (imageA.getVisibility()==View.VISIBLE) {
					imageA.setAnimation(null);
					showImageB();
					imageB.startAnimation(saTo1);
				}else{
					imageB.setAnimation(null);
					showImageA();
					imageA.startAnimation(saTo1);
				}
			}

			@Override public void onAnimationRepeat(Animation arg0) {}
			@Override public void onAnimationStart(Animation arg0) {}
		});
	}


	private void showImageA(){
		imageA.setVisibility(View.VISIBLE);
		imageB.setVisibility(View.INVISIBLE);
	}
	
	private void showImageB(){
		imageA.setVisibility(View.INVISIBLE);
		imageB.setVisibility(View.VISIBLE);
	}

	private ImageView imageA;
	private ImageView imageB;
	private ScaleAnimation saTo0 = new ScaleAnimation(1, 0, 1, 1, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
	private ScaleAnimation saTo1 = new ScaleAnimation(0, 1, 1, 1, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);

}
