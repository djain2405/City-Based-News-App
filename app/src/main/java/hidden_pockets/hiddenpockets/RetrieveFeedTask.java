package hidden_pockets.hiddenpockets;

import android.app.ProgressDialog;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import hidden_pockets.hiddenpockets.constant.FeedContent;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by divya on 11/5/2017.
 */

public class RetrieveFeedTask extends AsyncTask<Object, Void, String> {

    private Exception exception;
    Context context;
    int cityId;
    String cityName;
    ArrayList<FeedContent> feedlist;
    private ProgressDialog progressBar;
    private String API_URL = "http://www.hidden-pockets.com/wp-json/wp/v2/posts?categories=";

    public RetrieveFeedTask(Context context)
    {
        this.context = context;
        progressBar = new ProgressDialog(this.context);
    }

    protected String doInBackground(Object... params) {
        cityId = (Integer) params[0];
        cityName = (String) params[1];
        try {

            URL url = new URL(API_URL+cityId);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {


            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPreExecute()
    {
        progressBar.setTitle("Loading Data");
        progressBar.show();
    }
    protected void onPostExecute(String response) {

        feedlist = new ArrayList<>();

        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        //progressBar.setVisibility(View.GONE);
        Log.i("INFO", response);


        JSONArray jObject = null;
        try {
            jObject = new JSONArray(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        for(int i = 0; i < jObject.length(); i++)
        {
            try {
                JSONObject obj = jObject.getJSONObject(i);
                String title = obj.getString("title");
                FeedContent feedcontent = new FeedContent();
                feedcontent.setTitle(obj.getJSONObject("title").getString("rendered"));
                feedcontent.setExcerpt(obj.getJSONObject("excerpt").getString("rendered"));
                feedcontent.setURL(obj.getString("link"));
                feedlist.add(feedcontent);



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        progressBar.dismiss();
        Intent feedIntent = new Intent(context, FeedActivity.class);
        feedIntent.putExtra("cityname", cityName);
        feedIntent.putExtra("feedlist", feedlist);
        startActivity(context, feedIntent, null);

    }
}