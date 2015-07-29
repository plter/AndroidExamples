/**
 * Copyright [http://game2d.sinaapp.com] [xtiqin]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.plter.lib.java.lang;


/**
 * 数学相关的类
 * @author xtiqin
 * @see <a href="http://weibo.com/plter">plter</a>
 * @see <a href="http://game2d.sinaapp.com">Game2D</a>
 *
 */
public final class Math {

	/**
	 * 求两个值的最小值
	 * @param a 第一个值
	 * @param b 第二个值
	 * @return 最小值
	 */
	public static float min(float a,float b) {
		return a>b?b:a;
	}
	
	/**
	 * 求很float类型值的最小值
	 * @param args
	 * @return
	 */
	public static float min(float...args) {
		float minValue=Float.MAX_VALUE;
		for (float f : args) {
			minValue=min(minValue, f);
		}
		return minValue;
	}
	
	/**
	 * 求两个值的最大值
	 * @param a 第一个值
	 * @param b 第二个值
	 * @return 最大值
	 */
	public static float max(float a,float b) {
		return a>b?a:b;
	}
	
	/**
	 * 求很多float类型值的最大值
	 * @param args 
	 * @return
	 */
	public static float max(float...args) {
		float maxValue=Float.MIN_VALUE;
		for (float f : args) {
			maxValue=max(maxValue, f);
		}
		return maxValue;
	}
}
