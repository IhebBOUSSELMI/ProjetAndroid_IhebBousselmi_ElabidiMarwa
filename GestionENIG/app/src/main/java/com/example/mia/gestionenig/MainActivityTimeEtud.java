package com.example.mia.gestionenig;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.*;

public class MainActivityTimeEtud extends AppCompatActivity {
    ListView semaines;
    String wk,address;
    public static final String strURL = "http://192.168.1.71:80/miniprojet/list_weeks.php";
    ArrayList<String> tabweeks;
    ArrayList<NameValuePair> params;
    String result = "";
    ArrayAdapter<String> arrayAdapter;
    int n;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_time_etud);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        semaines = findViewById(R.id.semaines);
        params = new ArrayList<>();
        tabweeks = new ArrayList<>();
        int i;
        // Parse les données JSON
        try {
            result = getHttpPost(strURL, params);
            JSONArray jArray = new JSONArray(result);

            for (i = 0; i < jArray.length(); i++) {


                JSONObject json_data = jArray.optJSONObject(i);
                tabweeks.add(json_data.getString("nom"));
            }
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tabweeks);
        semaines.setAdapter(arrayAdapter);


        semaines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                String week = tabweeks.get(position);


                try {

                    String url = "http://192.168.1.71:80/miniprojet/filtre_weeks.php";
                    ArrayList<NameValuePair> params = new ArrayList<>();

                    params.add(new BasicNameValuePair("nom", week));
                    String resultServer = getHttpPost(url, params);
                    Log.d(null, "resultServer - updateData:: " + resultServer);

                    JSONObject c;
                    try {
                        c = new JSONObject(resultServer);
                        wk = c.getString("nom");
                        address = c.getString("url");
                        //Uri uri = Uri.parse(address);
                        //Intent intent = new Intent(ACTION_VIEW, uri);
                        //startActivity(intent);
                        try{
                            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(address));
                            startActivity(myIntent);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(MainActivityTimeEtud.this, "Can't handle opening browser ", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }


                    } catch (JSONException e) {

                        e.printStackTrace();
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }


            }

        });

    }

    public String getHttpPost(String url, List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Status OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();


    }
}