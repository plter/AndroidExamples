package com.plter.lib.java.db;


public abstract class Db {
	
	public abstract Exception getCurrentException();
	
	/**
	 * 如果成功，则返回true，如果在执行过程中发生任何错误，该方法将返回false
	 * @param sql
	 * @param dbQueryResultHandler
	 * @return
	 */
	public abstract boolean query(String sql,IDbQueryCallback dbQueryResultHandler);
	public abstract boolean update(String sql,IDbUpdateCallback dbUpdateCallback);
	
	public boolean update(String sql){
		return update(sql, null);
	}
}
