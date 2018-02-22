package hidden_pockets.hiddenpockets;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by divya on 11/5/2017.
 */

class RetrieveCityId extends AsyncTask<Object, Void, String> {

    private Exception exception;
    Context context;
    HashMap<String, Integer> cityMap;
    private String API_URL = "http://www.hidden-pockets.com/wp-json/wp/v2/categories?per_page=100";

    protected String doInBackground(Object... params) {
        //String apiUrl = etResponse.getText().toString();
        // Do some validation here

        context = (Context) params[0];
        cityMap = new HashMap<>();

        try {
            //URL url = new URL(API_URL + "email=" + email + "&apiKey=" + API_KEY);
            URL url = new URL(API_URL);
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

    protected void onPostExecute(String response) {

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
                String id = obj.getString("id");
                String name = obj.getString("name");
                cityMap.put(name, Integer.parseInt(id));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        SharedPreferences mapValues = context.getSharedPreferences("shared_preference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mapValues.edit();
        for(String s : cityMap.keySet())
        {
            editor.putInt(s, cityMap.get(s));
        }
        editor.commit();
        // tvIsConnected.setText(response);
    }
}