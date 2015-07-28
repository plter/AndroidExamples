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

import com.plter.lib.android.java.anim.AnimationStyle;

import android.content.Context;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListViewController extends ViewController {

	
	public ListViewController(Context context, int resid) {
		super(context, resid);
		
		setAnimationStyle(AnimationStyle.PUSH_RIGHT_TO_LEFT);
		
		listView=(ListView) findViewByID(android.R.id.list);
	}
	
	
	public ListViewController(Context context){
		super(new ListView(context));
		
		setAnimationStyle(AnimationStyle.PUSH_RIGHT_TO_LEFT);
		
		listView=(ListView) getView();
	}

	public void setOnItemClickListener(OnItemClickListener l){
		getListView().setOnItemClickListener(l);
	}

	public ListView getListView(){
		return listView;
	}
	
	public void setAdapter(ArrayAdapter<?> adapter){
		getListView().setAdapter(adapter);
	}
	
	
	private ListView listView=null;
}
