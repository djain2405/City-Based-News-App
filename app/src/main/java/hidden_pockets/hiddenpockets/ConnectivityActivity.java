package hidden_pockets.hiddenpockets;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

public class ConnectivityActivity extends AppCompatActivity {
    EditText etResponse;
    TextView tvIsConnected;

    HashMap<String, Integer> cityMap;
    private String API_URL = "http://www.hidden-pockets.com/wp-json/wp/v2/categories?per_page=100";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("Divya jain.. oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connectivity);
        etResponse = (EditText) findViewById(R.id.etResponse);
        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);


        // check if you are connected or not
        if(isConnected()){
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            //tvIsConnected.setText("You are conncted");
        }
        else{
            //tvIsConnected.setText("You are NOT conncted");
        }

        //new RetrieveCityId().execute(getApplicationContext());

    }

    private boolean isConnected() {

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }


}
