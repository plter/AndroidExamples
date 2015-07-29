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

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;


/**
 * 基于ViewController的Activity
 * @author xtiqin
 *
 */
public class ViewControllerActivity extends Activity implements IViewController{

	private ViewController rootVc;
	
	
	public void setContentView(int layoutResID) {
		rootVc=new ViewController(this,layoutResID);
		super.setContentView(rootVc.getViewStack());
	}
	
	
	public void setContentView(View view) {
		rootVc=new ViewController(view);
		super.setContentView(rootVc.getViewStack());
	}
	
	
	public void setContentView(View view, ViewGroup.LayoutParams params) {
		rootVc=new ViewController(view);
		super.setContentView(rootVc.getViewStack(), params);
	}
	
	
	public View findViewById(int id) {
		return rootVc.findViewByID(id);
	}
	
	
	/**
	 * 向根控制器对象添加一个ViewController
	 * @param c
	 * @param animated
	 */
	public void pushViewController(ViewController c,boolean animated){
		getRootViewController().pushViewController(c, animated);
	}
	
	
	/**
	 * 从根控制器对象中移除一个ViewController
	 * @param animated
	 */
	public void popViewController(boolean animated){
		getRootViewController().popViewController(animated);
	}
	
	
	/**
	 * 移除栈顶控制器对象
	 * @param animated
	 */
	public void popTopViewController(boolean animated){
		getTopViewController().getPrevious().popViewController(animated);
	}
	
	
	/**
	 * 取得栈顶控制器对象
	 * @return
	 */
	public ViewController getTopViewController(){
		return getRootViewController().getTopViewController();
	}
	
	
	/**
	 * 取得根控制器对象
	 * @return
	 */
	public ViewController getRootViewController(){
		return rootVc;
	}
	
	
	public void onBackPressed() {
		if (getTopViewController()!=getRootViewController()) {
			popTopViewController(true);
		}else{
			finish();
		}
	}
}
