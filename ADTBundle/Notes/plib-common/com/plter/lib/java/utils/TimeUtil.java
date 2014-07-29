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

package com.plter.lib.java.utils;

import java.util.Calendar;

public class TimeUtil {

	/**
	 * 将时间格式化
	 * @param time
	 * @return
	 */
	public static String timeFormat(int time){
		return time>=10?""+time:"0"+time;
	}


	/**
	 * 取得当前的时间字符串
	 * @param dateSp	日期之间的分割符
	 * @param sp		时期和时间之间的分割符
	 * @param timeSp	时间之间的分割符
	 * @return
	 */
	public static String getCurrentTimeString(String dateSp,String sp,String timeSp){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR)+dateSp+
				timeFormat(c.get(Calendar.MONTH)+1)+dateSp+
				timeFormat(c.get(Calendar.DAY_OF_MONTH))+sp+
				timeFormat(c.get(Calendar.HOUR_OF_DAY))+timeSp+
				timeFormat(c.get(Calendar.MINUTE))+timeSp+
				timeFormat(c.get(Calendar.SECOND));
	}



	public static String getCurrentTimeString(){
		return getCurrentTimeString("-"," ",":");
	}
}
