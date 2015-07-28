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

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public abstract class LogFactory {

	
	public static final String LOG_NAME="com.plter.lib.java.log";
	private static final long START_TIME=System.currentTimeMillis();
	private static Logger log = null;
	public static final Logger getLogger(){
		if (log==null) {
			log = Logger.getLogger(LOG_NAME);
			log.setUseParentHandlers(false);
			ConsoleHandler ch = new ConsoleHandler();
			ch.setFormatter(new Formatter() {
				
				@Override
				public String format(LogRecord record) {
					return String.format("%d[%s]%s[%s.%s]\n", record.getMillis()-START_TIME,record.getLevel(),record.getMessage(),record.getSourceClassName(),record.getSourceMethodName());
				}
			});
			ch.setLevel(Level.ALL);
			log.setLevel(Level.ALL);
			log.addHandler(ch);
		}
		return log;
	}
	
}
