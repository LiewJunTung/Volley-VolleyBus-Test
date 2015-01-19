package org.example.jt.realapp2.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.example.jt.realapp2.R;
import org.example.jt.realapp2.model.ParseWeatherJSON;
import org.example.jt.realapp2.model.Weather;
import org.example.jt.realapp2.util.MySingleton;
import org.json.JSONObject;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;


public class MainActivity extends ActionBarActivity {

    private EventBus eventBus;
    private TextView textView;
    private ListView listView;
    private RequestQueue queue;
    private String mURL;
    private ArrayList<Weather> mWeather;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mURL = "http://api.openweathermap.org/data/2.5/weather?q=London,uk";
        queue = MySingleton.getInstance(this).getRequestQueue();
        listView = (ListView) findViewById(R.id.listView);
        processResult();
        eventBus.register(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int pos,
                                    long arg3) {

                Toast.makeText(MainActivity.this, mWeather.get(pos).toString(), Toast.LENGTH_SHORT).show();
                eventBus.post(mWeather);

            }
        });
      }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void processResult(){
        queue.add(new JsonObjectRequest(Request.Method.GET, mURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                ParseWeatherJSON parseWeatherJSON = new ParseWeatherJSON(jsonObject);
                mWeather = parseWeatherJSON.getMWeather();
                ArrayAdapter<Weather> weatherArrayAdapter = new ArrayAdapter<Weather>(MainActivity.this, R.layout.list_item, mWeather);
                listView.setAdapter(weatherArrayAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }));
    }
}
