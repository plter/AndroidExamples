/**
   Copyright [plter] [xtiqin]
   http://plter.sinaapp.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   
   This is a part of PlterAndroidLib on 
   http://plter.sinaapp.com/?cat=14
*/


package com.plter.lib.android.java.controls;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;

public class Card2D extends Card implements AnimationListener {
	
	
	private ScaleAnimation saTo0;
	private ScaleAnimation saTo1;
	
	
	public Card2D(Context context) {
		super(context);
	}
	
	
	/**
	 * 翻转到正面
	 */
	public void turnToRecto(){
		if (!isRectoVisible()) {
			saTo0=new ScaleAnimation(1, 0, 1, 1,getWidth()/2,0);
			saTo0.setDuration(300);
			saTo0.setAnimationListener(this);
			getVerso().startAnimation(saTo0);
		}
	}
	
	
	/**
	 * 翻转到反面
	 */
	public void turnToVerso(){
		if (isRectoVisible()) {
			saTo0=new ScaleAnimation(1, 0, 1, 1,getWidth()/2,0);
			saTo0.setDuration(300);
			saTo0.setAnimationListener(this);
			getRecto().startAnimation(saTo0);
		}
	}
	
	
	
	public void onAnimationEnd(Animation animation) {
		
		saTo1=new ScaleAnimation(0, 1, 1, 1, getWidth()/2, 0);
		saTo1.setDuration(300);
		
		if (isRectoVisible()) {
			showVerso();
			
			getRecto().setAnimation(null);
			getVerso().startAnimation(saTo1);
		}else{
			showRecto();
			
			getVerso().setAnimation(null);
			getRecto().startAnimation(saTo1);
		}
		
	}


	
	public void onAnimationRepeat(Animation animation) {
	}


	
	public void onAnimationStart(Animation animation) {
	}
}
