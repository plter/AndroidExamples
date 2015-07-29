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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class AlphaCard extends Card implements AnimationListener {

	
	private AlphaAnimation aaTo0;
	private AlphaAnimation aaTo1;
	
	
	public AlphaCard(Context context) {
		super(context);
		
		
		aaTo0=new AlphaAnimation(1, 0);
		aaTo0.setDuration(300);
		aaTo1=new AlphaAnimation(0, 1);
		aaTo1.setDuration(300);
		
		aaTo0.setAnimationListener(this);
	}

	
	/**
	 * 到反面
	 */
	public void goToVerso(){
		if (isRectoVisible()) {
			getVerso().setAnimation(null);
			getRecto().startAnimation(aaTo0);
		}
	}
	
	
	/**
	 * 到正面
	 */
	public void goToRecto(){
		if (!isRectoVisible()) {
			getRecto().setAnimation(null);
			getVerso().startAnimation(aaTo0);
		}
	}
	
	

	
	public void onAnimationEnd(Animation animation) {
		
		if (isRectoVisible()) {
			showVerso();
			
			getRecto().setAnimation(null);
			getVerso().startAnimation(aaTo1);
		}else{
			showRecto();
			
			getRecto().startAnimation(aaTo1);
			getVerso().setAnimation(null);
		}
		
	}


	
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}


	
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}

}
