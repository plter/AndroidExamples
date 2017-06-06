package top.yunp.opendatabase;

import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db = openOrCreateDatabase("db", MODE_PRIVATE, new SQLiteDatabase.CursorFactory() {
            @Override
            public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {
                return new UserCursor(masterQuery, editTable, query);
            }
        }, new DatabaseErrorHandler() {
            @Override
            public void onCorruption(SQLiteDatabase dbObj) {
                System.out.println(dbObj);
            }
        });
//        db.execSQL("CREATE TABLE data (" +
//                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "name TEXT NOT NULL DEFAULT \"\"," +
//                "age TEXT NOT NULL DEFAULT \"\")");
//
//
//        ContentValues cvs = new ContentValues();
//        cvs.put("name", "小华");
//        cvs.put("age", "18");
//        db.insert("data", null, cvs);

        UserCursor users = (UserCursor) db.query("data", null, null, null, null, null, null, null);
        while (users.moveToNext()) {
            System.out.println("_id=" + users.getId() + ",name=" + users.getName() + ",age=" + users.getAge());
        }
        users.close();

        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
    }
}
