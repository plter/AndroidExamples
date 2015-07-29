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

package com.plter.lib.java.lang;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import com.plter.lib.java.utils.LogFactory;

public class FilePool {

	private static Map<String, File> fileMap = new ConcurrentHashMap<String, File>();
	private static final Logger log = LogFactory.getLogger();
	
	public static File getFile(String path){
		File f = fileMap.get(path);
		if (f==null) {
			f=new File(path);
			fileMap.put(path, f);
			
			log.config("Create a file object ".concat(f.getAbsolutePath()));
		}
		return f;
	}
	
	public static void clear(){
		fileMap.clear();
	}
	
	
}
