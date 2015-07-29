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
import android.widget.RelativeLayout;

public class Card extends RelativeLayout {
	
	
	public Card(Context context) {
		super(context);
		
		recto=new RelativeLayout(context);
		addView(recto, -1, -1);
		
		verso=new RelativeLayout(context);
		addView(verso, -1, -1);
		
		showRecto();
	}
	
	
	public void showRecto(){
		recto.setVisibility(View.VISIBLE);
		verso.setVisibility(View.GONE);
		rectoVisible=true;
	}
	
	
	public void showVerso(){
		verso.setVisibility(View.VISIBLE);
		recto.setVisibility(View.GONE);
		rectoVisible=false;
	}
	
	
	private boolean rectoVisible=true;
	
	
	/**
	 * 指示当前正面是否可见
	 * @return
	 */
	public boolean isRectoVisible() {
		return rectoVisible;
	}


	private RelativeLayout recto;
	public RelativeLayout getRecto() {
		return recto;
	}
	public RelativeLayout getVerso() {
		return verso;
	}

	private RelativeLayout verso;

}
