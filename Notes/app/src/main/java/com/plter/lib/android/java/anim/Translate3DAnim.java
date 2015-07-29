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

package com.plter.lib.android.java.anim;

import android.graphics.Camera;
import android.view.animation.Animation;
import android.view.animation.Transformation;


/**
 * 3D变换类
 * @author xtiqin
 * @see <a href="http://plter.sinaapp.com">plter</a>
 *
 */
public class Translate3DAnim extends Animation {

	/**
	 * 翻转时远去
	 */
	public static final int MOVE_FAR=1;
	
	
	/**
	 * 翻转时靠近
	 */
	public static final int MOVE_NEAR=2;
	
	
	/**
	 * 翻转时位置不动
	 */
	public static final int DONOT_MOVE=3;
	
	
	/**
	 * 构建一个3D变换动画效果
	 * @param fromAngle 旋转起始角度 
	 * @param endAngle	旋转结束角度
	 * @param moveMode	移动模式，为MOVE_FAR,MOVE_NEAR,DONOT_MOVE三者之一
	 */
	public Translate3DAnim(float fromAngle,float endAngle,int moveMode) {
		this.fromAngle=fromAngle;
		this.endAngle=endAngle;
		
		this.moveMode=moveMode;
		
		setDuration(350);
		setFillAfter(true);
	}
	
	
//	private int width=0;
//	private int height=0;
	private int parentWidth=0;
	private int parentHeight=0;
	private float maxDepth=0;
	private float fromAngle=0;
	private int moveMode=3;
	private Camera camera;
	
	
	public float getFromAngle() {
		return fromAngle;
	}


	public float getEndAngle() {
		return endAngle;
	}


	private float endAngle=0;
	
	
	public void initialize(int width, int height, int parentWidth,
			int parentHeight) {
//		this.width=width;
//		this.height=height;
		this.parentHeight=parentHeight;
		this.parentWidth=parentWidth;
		maxDepth=parentWidth;
		
		camera=new Camera();
		
		super.initialize(width, height, parentWidth, parentHeight);
	}
	
	
	
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		camera.save();
		
		switch (moveMode) {
		case MOVE_FAR:
			camera.translate(0, 0, maxDepth*interpolatedTime/3*2);
			break;
		case MOVE_NEAR:
			camera.translate(0, 0, maxDepth*(1-interpolatedTime)/3*2);
			break;
		}
		camera.rotateY(fromAngle+(endAngle-fromAngle)*interpolatedTime);
		camera.getMatrix(t.getMatrix());
		camera.restore();
		
		t.getMatrix().preTranslate(-parentWidth/2, -parentHeight/2);
		t.getMatrix().postTranslate(parentWidth/2, parentHeight/2);
		
		super.applyTransformation(interpolatedTime, t);
	}
	
}
