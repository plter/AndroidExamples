package com.plter.lib.java.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDbQueryCallback {

	void onResult(ResultSet resultSet) throws SQLException;
	
}
