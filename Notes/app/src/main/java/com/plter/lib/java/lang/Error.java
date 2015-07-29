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

package com.plter.lib.java.lang;

public class Error extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2276869073549423082L;
	
	
	public Error(String errorMsg,int errorID) {
		super(errorMsg+",error id = "+errorID);
		setErrorID(errorID);
	}
	
	public Error(String errorMsg) {
		super(errorMsg);
	}


	private int errorID=0;public int getErrorID() {return errorID;}private void setErrorID(int errorID) {this.errorID = errorID;}

}
