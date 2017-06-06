package top.yunp.opendatabase;

import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteQuery;

/**
 * Created by plter on 6/6/17.
 */

public class UserCursor extends SQLiteCursor {


    public UserCursor(SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
        super(driver, editTable, query);
    }

    public int getId() {
        return getInt(0);
    }

    public String getName() {
        return getString(1);
    }

    public String getAge() {
        return getString(2);
    }
}
