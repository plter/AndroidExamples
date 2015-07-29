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
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class AlphaCard1 extends Card implements AnimationListener {

	
	private AlphaAnimation aaTo0;
	private AlphaAnimation aaTo1;
	
	public AlphaCard1(Context context) {
		super(context);
		
		aaTo0=new AlphaAnimation(1, 0);
		aaTo0.setDuration(600);
		aaTo0.setFillAfter(true);
		
		aaTo1=new AlphaAnimation(0, 1);
		aaTo1.setDuration(600);
		aaTo1.setFillAfter(true);
		
		aaTo0.setAnimationListener(this);
	}
	
	
	
	public void goToRecto() {
		if (!isRectoVisible()) {
			showTwoSides();
			
			getRecto().setAnimation(null);
			getVerso().setAnimation(null);
			getRecto().startAnimation(aaTo1);
			getVerso().startAnimation(aaTo0);
		}
	}
	
	
	public void goToVerso() {
		if (isRectoVisible()) {
			showTwoSides();
			
			getRecto().setAnimation(null);
			getVerso().setAnimation(null);
			getRecto().startAnimation(aaTo0);
			getVerso().startAnimation(aaTo1);
		}
	}
	
	
	private void showTwoSides(){
		getRecto().setVisibility(View.VISIBLE);
		getVerso().setVisibility(View.VISIBLE);
	}



	
	public void onAnimationEnd(Animation animation) {
		if (isRectoVisible()) {
			showVerso();
		}else{
			showRecto();
		}
	}



	
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}



	
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}
}
