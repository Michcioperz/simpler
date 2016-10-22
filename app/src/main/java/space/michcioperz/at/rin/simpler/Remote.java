package space.michcioperz.at.rin.simpler;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URLEncoder;

public class Remote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
        queue = Volley.newRequestQueue(this);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
    }

    RequestQueue queue;
    SharedPreferences pref;

    private void request(String subrequest) {
        queue.add(new StringRequest(Request.Method.GET, pref.getString("server_url", "bad_url://")+subrequest, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Remote.this, R.string.ACTION_SUCCESSFUL, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Remote.this, R.string.ACTION_ERROR, Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public void commandBackward(View view) {
        request("/control?key=left");
    }
    public void commandFastForward(View view) {
        request("/control?key=up");
    }
    public void commandPause(View view) {
        request("/control?key=+");
    }
    public void commandForward(View view) {
        request("/control?key=right");
    }
    public void commandFastBackward(View view) {
        request("/control?key=down");
    }
    public void commandEject(View view) {
        request("/control?key=q");
    }

}
