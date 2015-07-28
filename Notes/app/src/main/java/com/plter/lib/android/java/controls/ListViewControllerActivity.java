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

import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

public class ListViewControllerActivity extends ViewControllerActivity {

	
	private ListView rootLv=null;
	private ListViewController rootLvc=null;
	
	public ListView getListView(){
		if(rootLv==null){
			if (getRootViewController()!=null) {
				rootLv=(ListView) findViewById(android.R.id.list);
			}
			
			if (rootLv==null) {
				rootLvc=new ListViewController(this);
				rootLv=rootLvc.getListView();
				setContentView(rootLvc.getViewStack());
			}
		}
		return rootLv;
	}
	
	
	public void setAdapter(ArrayAdapter<?> adapter){
		getListView().setAdapter(adapter);
	}
	
	
	public void setOnItemClickListener(OnItemClickListener l){
		getListView().setOnItemClickListener(l);
	}
	
	
	public void setOnItemLongClickListener(OnItemLongClickListener l){
		getListView().setOnItemLongClickListener(l);
	}
	
	public void setOnItemSelectedListener(OnItemSelectedListener l){
		getListView().setOnItemSelectedListener(l);
	}
}
