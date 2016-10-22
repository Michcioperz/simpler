package space.michcioperz.at.rin.simpler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Catcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent source = getIntent();

        String url;

        if (source.getType().equals(Intent.ACTION_VIEW))
            url = source.getData().toString();
        else
            url = source.getStringExtra(Intent.EXTRA_TEXT);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest req = null;
        try {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

            req = new StringRequest(Request.Method.GET, sharedPref.getString("server_url", "bad_url://")+"/open?video_path="+ URLEncoder.encode(url, "UTF-8"), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(Catcher.this, R.string.ACTION_SUCCESSFUL, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Catcher.this, R.string.ACTION_ERROR, Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        queue.add(req);
    }
}
