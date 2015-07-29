package com.plter.ruler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

public class CM extends TextView {

	public CM(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		initProperties();
	}

	public CM(Context context) {
		super(context);
		
		initProperties();
	}

	public CM(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		initProperties();
	}
	
	
	private void initProperties() {
		setGravity(Gravity.BOTTOM);
	}
	
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		float mmWidth = ((float)getWidth())/10;
		Paint p = new Paint();
		p.setColor(Color.WHITE);
		float top = 2;
		
		for (int i = 0; i < 10; i++) {
			if (i%2==1) {
				canvas.drawRect(i*mmWidth, top, i*mmWidth+mmWidth, top+mmWidth, p);
			}
		}
	}

}
