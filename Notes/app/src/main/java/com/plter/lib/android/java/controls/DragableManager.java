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

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;



/**
 * 此类要求指定的组件所在的父级容器必须为RelativeLayout
 * @author plter
 * @see <a href="http://plter.sinaapp.com">plter</a>
 *
 */
public class DragableManager implements OnTouchListener {


	private float offsetX=0;
	private float offsetY=0;
	private RelativeLayout.LayoutParams lp;
	private boolean dragable=false;

	private View target=null;


	public DragableManager(View target) {
		this.target=target;
		setDragable(true);
	}

	public View getTarget() {
		return target;
	}


	public boolean isDragable() {
		return dragable;
	}


	public void setDragable(boolean dragable) {
		this.dragable = dragable;
		if (dragable) {
			getTarget().setOnTouchListener(this);
		}else{
			getTarget().setOnTouchListener(null);
		}
	}


	
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			lp=(LayoutParams) getTarget().getLayoutParams();
			lp.leftMargin=(int) (event.getX()+lp.leftMargin-offsetX);
			lp.topMargin=(int) (event.getY()+lp.topMargin-offsetY);
			getTarget().setLayoutParams(lp);
			
			if (getOnDragingListener()!=null) {
				getOnDragingListener().onDraging(this);
			}
			break;
		case MotionEvent.ACTION_DOWN:
			offsetX=event.getX();
			offsetY=event.getY();
			
			if (getOnDragStartListener()!=null) {
				getOnDragStartListener().onDragStart(this);
			}

			if(isBringChildToFrontWhenDraging()){
				getTarget().bringToFront();
			}
			break;
		case MotionEvent.ACTION_UP:
			if (getOnDragEndListener()!=null) {
				getOnDragEndListener().onDragEnd(this);
			}
			break;
		default:
			break;
		}
		return true;
	}

	
	/**
	 * 是否在拖动时当该对象放在显示列表最前面
	 * @return
	 */
	public boolean isBringChildToFrontWhenDraging() {
		return bringChildToFrontWhenDraging;
	}
	

	/**
	 * 是否在拖动时把目标组件放在其父级容器中的最前面
	 * @param bringChildToFrontWhenDraging
	 */
	public void setBringChildToFrontWhenDraging(boolean bringChildToFrontWhenDraging) {
		this.bringChildToFrontWhenDraging = bringChildToFrontWhenDraging;
	}
	
	private OnDragEndListener onDragEndListener=null;

	public OnDragEndListener getOnDragEndListener() {
		return onDragEndListener;
	}

	public void setOnDragEndListener(OnDragEndListener onDragEndListener) {
		this.onDragEndListener = onDragEndListener;
	}

	public OnDragStartListener getOnDragStartListener() {
		return onDragStartListener;
	}

	public void setOnDragStartListener(OnDragStartListener onDragStartListener) {
		this.onDragStartListener = onDragStartListener;
	}
	
	public OnDragingListener getOnDragingListener() {
		return onDragingListener;
	}

	public void setOnDragingListener(OnDragingListener onDragingListener) {
		this.onDragingListener = onDragingListener;
	}

	private OnDragStartListener onDragStartListener=null;

	private OnDragingListener onDragingListener=null;

	private boolean bringChildToFrontWhenDraging=true;
	
	
	public static interface OnDragingListener{
		
		void onDraging(DragableManager target);
	}
	
	public static interface OnDragEndListener{
		void onDragEnd(DragableManager target);
	}
	
	public static interface OnDragStartListener{
		void onDragStart(DragableManager target);
	}
}
