/**
   Copyright [2013-2018] [plter] http://plter.com

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

package com.plter.lib.java.utils;

import java.util.Timer;
import java.util.TimerTask;

public class Looper {

	public static final void loop(final Condition condition,final Executer executer,final Completer completer,final long delay,final long period){
		new Looper(condition, executer,completer, delay,period);
	}
	
	public static final void loop(final Condition condition,final Executer executer,final Completer completer,final long period){
		loop(condition, executer,completer, defaultDelay,period);
	}
	
	public static final void loop(final Condition condition,final Executer executer,final Completer completer){
		loop(condition, executer,completer, defaultPeriod);
	}
	
	public static final void loop(final Condition condition,final Executer executer,final long period){
		loop(condition, executer,null, period);
	}
	
	public static final void loop(final Condition condition,final Executer executer){
		loop(condition, executer, defaultPeriod);
	}
	
	public static final void loop(final int times,final Executer executer,final Completer completer,final long period){
		
		loop(new Condition() {
			int current = 0 ;
			
			@Override
			public boolean condition() {
				current++;
				return current>times;
			}
		}, executer, completer, period);
		
	}
	
	public static final void loop(final int times,final Executer executer,final long period){
		loop(times, executer, null, period);
	}
	
	public static final void loop(final int times,final Executer executer){
		loop(times, executer, defaultPeriod);
	}
	
	private static int defaultDelay=1;public static int getDefaultDelay() {return defaultDelay;}public static void setDefaultDelay(int defaultDelay) {Looper.defaultDelay = defaultDelay;}
	private static int defaultPeriod=10;public static int getDefaultPeriod() {return defaultPeriod;}public static void setDefaultPeriod(int defaultPeriod) {Looper.defaultPeriod = defaultPeriod;}
	
	
	
	private Looper(final Condition condition,final Executer executer,final Completer completer,final long delay,final long period) {
		
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {				
				if (condition.condition()) {
					executer.execute();
					
					if (!condition.condition()) {
						cancelTask();
					}
				}else{
					cancelTask();
				}
			}
			
			private void cancelTask(){
				cancel();
				
				if (completer!=null) {
					completer.onComplete();
				}
			}
		};
		
		executer.setCurrentLooperTask(task);
		timer.schedule( task , delay, period);
	}
	

	private static final Timer timer = new Timer();
	
	public static abstract class Condition{
		public abstract boolean condition();
	}
	
	public static abstract class Executer{
		public abstract void execute();
		
		public boolean cancel(){
			return getCurrentLooperTask().cancel();
		}
		
		private TimerTask currentLooper=null;
		private final TimerTask getCurrentLooperTask(){
			return currentLooper;
		}
		private final void setCurrentLooperTask(TimerTask looper){
			currentLooper=looper;
		}
	}
	
	public static abstract class Completer{
		public abstract void onComplete();
	}
}
