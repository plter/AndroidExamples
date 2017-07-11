package top.yunp.okhttpexample

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvResult: TextView = findViewById(R.id.tvResult) as TextView

        object : AsyncTask<Void, Void, String>() {
            override fun doInBackground(vararg p0: Void?): String? {
                return OkHttpClient().newCall(Request.Builder().url("http://yunp.top").build()).execute().body()?.string()
            }

            override fun onPostExecute(result: String?) {
                tvResult.text = result
            }

        }.execute()
    }
}
