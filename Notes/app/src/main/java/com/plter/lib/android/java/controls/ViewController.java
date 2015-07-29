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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import com.plter.lib.android.java.anim.AnimationStyle;
import com.plter.lib.android.java.anim.Translate3DAnim;
import com.plter.lib.java.event.EventListenerList;


/**
 * 视频控制器
 * @author xtiqin
 * @see <a href="http://plter.sinaapp.com">plter</a>
 *
 */
public class ViewController implements IViewController{


	/**
	 * 创建一个ViewController控制器对象
	 * @param view 所绑定的目标视图
	 */
	public ViewController(View view) {
		this.view=view;
	}

	/**
	 * 创建一个ViewController控制器对象
	 * @param context
	 * @param viewID 所绑定的布局的资源id
	 */
	public ViewController(Context context,int viewID) {
		this.viewID=viewID;

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.view=inflater.inflate(viewID, null);
	}


	private int viewID=0;

	/**
	 * 取得目标布局的资源id
	 * @return
	 */
	public int getViewID() {
		return viewID;
	}

	public View findViewByID(int resid){
		return getView().findViewById(resid);
	}

	public Context getContext(){
		return view.getContext();
	}

	public void setOnClickListener(View.OnClickListener l){
		getView().setOnClickListener(l);
	}

	public void setOnTouchListener(View.OnTouchListener l){
		getView().setOnTouchListener(l);
	}


	/**
	 * 向堆栈中加入一个控制器对象，如果该控制器对象已经存在下一个对象，则不执行任何操作
	 * @param vc
	 * @param animated
	 */
	public void pushViewController(ViewController vc,boolean animated){
		if (next!=null) {
			return;
		}

		next=vc;

		if (animated) {

			switch (getNext().getAnimationStyle()) {
			case AnimationStyle.PUSH_RIGHT_TO_LEFT:
			case AnimationStyle.PUSH_LEFT_TO_RIGHT:
				addNext();
				getView().startAnimation(getSelfOutAnim(getNext().getAnimationStyle()));
				getNext().getView().startAnimation(getNextInAnim(getNext().getAnimationStyle()));
				break;
			case AnimationStyle.COVER_VERTICAL:
				addNext();
				getNext().getView().startAnimation(getCoverVerticalIn());
				break;
			case AnimationStyle.COVER_HORIZONTAL_RIGHT_TO_LEFT:
				addNext();
				getNext().getView().startAnimation(getCoverHorizontalIn());
				break;
			case AnimationStyle.FLIP_HORIZONTAL:
				getView().startAnimation(getTurnOverMoveFar());
				break;
			case AnimationStyle.CROSS_DISSOLVE:
				addNext();
				getView().startAnimation(getSelfOutAlphaAnim());
				getNext().getView().startAnimation(getNextInAlphaAnim());
				break;
			default:
				break;
			}
		}else{
			addNext();
		}
	}


	/**
	 * 从堆栈中移除当前控制器对象的所以后续控制器对象
	 * @param animated 是否呈现动画效果
	 */
	public void popViewController(boolean animated){
		if (next!=null) {
			if (next.backing.dispatch(new ViewControllerEvent(ViewControllerEvent.BACKING))) {
				forcePopViewController(animated);
			}
		}
	}


	/**
	 * 强制从堆栈中移除当前控制器对象的所以后续控制器对象，该方法不会触发ViewControllerEvent.ON_BACKING事件，直接移除指定的控制器
	 * @param animated 是否呈现动画效果
	 */
	public void forcePopViewController(boolean animated){
		if (next!=null) {

			//TODO 目前不清楚为何不加这个判断会导致系统程序异常，因此暂时禁用动画效果
			if (getNext()!=getTopViewController()) {
				animated=false;
			}

			if (animated) {

				switch (getNext().getAnimationStyle()) {
				case AnimationStyle.PUSH_RIGHT_TO_LEFT:
				case AnimationStyle.PUSH_LEFT_TO_RIGHT:
					showThis();
					getView().startAnimation(getSelfInAnim(getNext().getAnimationStyle()));
					getNext().getView().startAnimation(getNextOutAnim(getNext().getAnimationStyle()));
					break;
				case AnimationStyle.COVER_VERTICAL:
					showThis();
					getNext().getView().startAnimation(getCoverVerticalOut());
					break;
				case AnimationStyle.COVER_HORIZONTAL_RIGHT_TO_LEFT:
					showThis();
					getNext().getView().startAnimation(getCoverHorizobtalOut());
					break;
				case AnimationStyle.FLIP_HORIZONTAL:
					next.getView().startAnimation(getTurnBackMoveFar());
					break;
				case AnimationStyle.CROSS_DISSOLVE:
					showThis();
					getView().startAnimation(getSelfInAlphaAnim());
					getNext().getView().startAnimation(getNextOutAlphaAmin());
					break;

				default:
					break;
				}
			}else{
				showThis();
				removeNext();
			}
		}
	}


	private final AnimationListener animListener=new AnimationListener() {


		public void onAnimationStart(Animation animation) {}


		public void onAnimationRepeat(Animation animation) {}


		public void onAnimationEnd(Animation animation) {
			switch (getNext().getAnimationStyle()) {
			case AnimationStyle.PUSH_RIGHT_TO_LEFT:
			case AnimationStyle.PUSH_LEFT_TO_RIGHT:
				if (animation==selfOutAnim) {
					hideThis();
				}else if (animation==nextOutAnim) {
					removeNext();
				}
				break;
			case AnimationStyle.COVER_VERTICAL:
				if (animation==getCoverVerticalIn()) {
					hideThis();
				}else if(animation==getCoverVerticalOut()){
					removeNext();
				}
				break;
			case AnimationStyle.COVER_HORIZONTAL_RIGHT_TO_LEFT:
				if (animation==getCoverHorizontalIn()) {
					hideThis();
				}else if (animation==getCoverHorizobtalOut()) {
					removeNext();
				}
				break;
			case AnimationStyle.FLIP_HORIZONTAL:

				if (animation==getTurnOverMoveFar()) {
					addNext();
					hideThis();

					getView().setAnimation(null);
					next.getView().startAnimation(getTurnOverMoveNear());
				}else if (animation==getTurnBackMoveFar()) {
					next.getView().setAnimation(null);

					showThis();
					removeNext();
					getView().startAnimation(getTurnBackMoveNear());
				}
				break;
			case AnimationStyle.CROSS_DISSOLVE:
				if (animation==getSelfOutAlphaAnim()) {
					hideThis();
				}else if (animation==getNextOutAlphaAmin()) {
					removeNext();
				}
				break;
			default:
				break;
			}
		}
	};


	/**
	 * 取得链表中当前对象的上一个控制器对象
	 * @return
	 */
	public ViewController getPrevious(){
		return previous;
	}


	/**
	 * 取得栈顶控制器对象
	 * @return
	 */
	public ViewController getTopViewController(){
		if (getNext()!=null) {
			return getNext().getTopViewController();
		}else{
			return this;
		}
	}


	/**
	 * 取得根控制器
	 * @return
	 */
	public ViewController getRootViewController(){
		if (getPrevious()!=null) {
			return getPrevious().getRootViewController();
		}
		return this;
	}


	private ViewController previous=null;

	private ViewGroup stack=null;


	/**
	 * 取得堆栈对象
	 * @return
	 */
	public ViewGroup getViewStack(){
		if (getPrevious()!=null) {
			return getPrevious().getViewStack();
		}else{
			if (stack==null) {
				stack=new FrameLayout(getContext());
				stack.addView(getView(),-1,-1);
			}

			return stack;
		}
	}


	/**
	 * 判断当前控制器对象是否为根容器对象
	 * @return
	 */
	public boolean isRootViewController(){
		return getRootViewController()==this;
	}


	private ViewController next=null;

	/**
	 * 取得链表中当前对象的下一个控制器对象
	 * @return
	 */
	public ViewController getNext() {
		return next;
	}


	private void addNext(){

		if (getNext()==null||getNext().getView().getParent()!=null) {
			return;
		}

		getViewStack().addView(next.getView(),-1,-1);
		next.previous=this;
		next.stack=getViewStack();
		next.navigateTo.dispatch(new ViewControllerEvent(ViewControllerEvent.NAVIGATE_TO));
	}


	private void hideThis(){
		getView().setVisibility(View.INVISIBLE);
	}

	private void showThis(){
		getView().setVisibility(View.VISIBLE);
	}


	private void removeNext(){
		if (next==null) {
			return;
		}

		next.back.dispatch(new ViewControllerEvent(ViewControllerEvent.BACK));
		navigateTo.dispatch(new ViewControllerEvent(ViewControllerEvent.NAVIGATE_TO));

		if (next.getNext()!=null) {
			next.removeNext();
		}

		getViewStack().removeView(next.getView());

		next.stack=null;
		next.previous=null;
		next=null;
	}


	private AlphaAnimation selfInAlphaAnim=null;
	private AlphaAnimation selfOutAlphaAnim=null;
	private AlphaAnimation nextInAlphaAnim=null;
	private AlphaAnimation nextOutAlphaAmin=null;


	public AlphaAnimation getSelfInAlphaAnim() {
		if (selfInAlphaAnim==null) {
			selfInAlphaAnim=new AlphaAnimation(0, 1);
			selfInAlphaAnim.setDuration(350);
		}
		return selfInAlphaAnim;
	}

	public AlphaAnimation getSelfOutAlphaAnim() {
		if (selfOutAlphaAnim==null) {
			selfOutAlphaAnim=new AlphaAnimation(1, 0);
			selfOutAlphaAnim.setDuration(350);
			selfOutAlphaAnim.setAnimationListener(animListener);
		}
		return selfOutAlphaAnim;
	}

	public AlphaAnimation getNextInAlphaAnim() {
		if (nextInAlphaAnim==null) {
			nextInAlphaAnim=new AlphaAnimation(0, 1);
			nextInAlphaAnim.setDuration(350);
		}
		return nextInAlphaAnim;
	}

	public AlphaAnimation getNextOutAlphaAmin() {
		if (nextOutAlphaAmin==null) {
			nextOutAlphaAmin=new AlphaAnimation(1, 0);
			nextOutAlphaAmin.setDuration(350);
			nextOutAlphaAmin.setAnimationListener(animListener);
		}
		return nextOutAlphaAmin;
	}

	private TranslateAnimation selfInAnim=null;
	private TranslateAnimation selfOutAnim=null;
	private TranslateAnimation nextInAnim=null;
	private TranslateAnimation nextOutAnim=null;


	public TranslateAnimation getSelfInAnim(int mode) {
		if (mode==AnimationStyle.PUSH_RIGHT_TO_LEFT) {
			selfInAnim=new TranslateAnimation(-getViewStack().getWidth(), 0, 0, 0);
		}else{
			selfInAnim=new TranslateAnimation(getViewStack().getWidth(), 0, 0, 0);
		}
		selfInAnim.setDuration(350);
		return selfInAnim;
	}

	public TranslateAnimation getSelfOutAnim(int mode) {
		if (mode==AnimationStyle.PUSH_RIGHT_TO_LEFT) {
			selfOutAnim=new TranslateAnimation(0, -getViewStack().getWidth(), 0, 0);
		}else{
			selfOutAnim=new TranslateAnimation(0, getViewStack().getWidth(), 0, 0);
		}
		selfOutAnim.setDuration(350);
		selfOutAnim.setAnimationListener(animListener);
		return selfOutAnim;
	}

	public TranslateAnimation getNextInAnim(int mode) {
		if (mode==AnimationStyle.PUSH_RIGHT_TO_LEFT) {
			nextInAnim=new TranslateAnimation(getViewStack().getWidth(), 0, 0, 0);
		}else{
			nextInAnim=new TranslateAnimation(-getViewStack().getWidth(), 0, 0, 0);
		}
		nextInAnim.setDuration(350);
		return nextInAnim;
	}

	public TranslateAnimation getNextOutAnim(int mode) {
		if (mode==AnimationStyle.PUSH_RIGHT_TO_LEFT) {
			nextOutAnim=new TranslateAnimation(0, getViewStack().getWidth(), 0, 0);
		}else{
			nextOutAnim=new TranslateAnimation(0, -getViewStack().getWidth(), 0, 0);
		}
		nextOutAnim.setDuration(350);
		nextOutAnim.setAnimationListener(animListener);
		return nextOutAnim;
	}

	private TranslateAnimation coverHorizontalIn=null;

	public TranslateAnimation getCoverHorizontalIn() {
		if (coverHorizontalIn==null) {
			coverHorizontalIn=new TranslateAnimation(getViewStack().getWidth(), 0, 0, 0);
			coverHorizontalIn.setDuration(300);
			coverHorizontalIn.setAnimationListener(animListener);
		}
		return coverHorizontalIn;
	}

	public TranslateAnimation getCoverHorizobtalOut() {
		if (coverHorizobtalOut==null) {
			coverHorizobtalOut=new TranslateAnimation(0, getViewStack().getWidth(), 0, 0);
			coverHorizobtalOut.setDuration(300);
			coverHorizobtalOut.setAnimationListener(animListener);
		}
		return coverHorizobtalOut;
	}

	private TranslateAnimation coverHorizobtalOut=null;


	private TranslateAnimation coverVerticalIn=null;

	public TranslateAnimation getCoverVerticalIn() {
		if (coverVerticalIn==null) {
			coverVerticalIn=new TranslateAnimation(0, 0, getViewStack().getHeight(), 0);
			coverVerticalIn.setDuration(300);
			coverVerticalIn.setAnimationListener(animListener);
		}

		return coverVerticalIn;
	}

	private TranslateAnimation coverVerticalOut=null;

	public TranslateAnimation getCoverVerticalOut() {
		if (coverVerticalOut==null) {
			coverVerticalOut=new TranslateAnimation(0, 0, 0, getViewStack().getHeight());
			coverVerticalOut.setDuration(350);
			coverVerticalOut.setAnimationListener(animListener);
		}

		return coverVerticalOut;
	}

	private Translate3DAnim turnOverMoveFar=null;
	private Translate3DAnim getTurnOverMoveFar() {
		if (turnOverMoveFar==null) {
			turnOverMoveFar=new Translate3DAnim(0, 90, Translate3DAnim.MOVE_FAR);
			turnOverMoveFar.setAnimationListener(animListener);
		}

		return turnOverMoveFar;
	}


	private Translate3DAnim turnOverMoveNear=null;
	private Translate3DAnim getTurnOverMoveNear() {
		if (turnOverMoveNear==null) {
			turnOverMoveNear=new Translate3DAnim(-90, 0, Translate3DAnim.MOVE_NEAR);
		}

		return turnOverMoveNear;
	}


	private Translate3DAnim turnBackMoveFar=null;
	private Translate3DAnim getTurnBackMoveFar() {
		if (turnBackMoveFar==null) {
			turnBackMoveFar=new Translate3DAnim(0, -90, Translate3DAnim.MOVE_FAR);
			turnBackMoveFar.setAnimationListener(animListener);
		}

		return turnBackMoveFar;
	}

	private Translate3DAnim turnBackMoveNear=null;
	private Translate3DAnim getTurnBackMoveNear() {
		if (turnBackMoveNear==null) {
			turnBackMoveNear=new Translate3DAnim(90, 0, Translate3DAnim.MOVE_NEAR);
		}

		return turnBackMoveNear;
	}


	private View view=null;

	/**
	 * 取得该UIView对象所绑定的目标布局
	 * @return
	 */
	public View getView() {
		return view;
	}

	private int animationStyle=1;

	/**
	 * 取得动画效果样式，为AnimationStyle类中的常量之一
	 * @return
	 */
	public int getAnimationStyle() {
		return animationStyle;
	}

	/**
	 * 设置动画效果样式，为AnimationStyle类中的常量之一
	 * @param animationStyle
	 */
	public void setAnimationStyle(int animationStyle) {
		this.animationStyle = animationStyle;
	}



	public final EventListenerList<ViewControllerEvent> backing = new EventListenerList<ViewControllerEvent>();
	public final EventListenerList<ViewControllerEvent> back = new EventListenerList<ViewControllerEvent>();
	public final EventListenerList<ViewControllerEvent> navigateTo = new EventListenerList<ViewControllerEvent>();

}
