package com.example.mia.gestionenig;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivityConnect extends AppCompatActivity{
    public static final String strURL = "http://192.168.0.6:80/service1.php";
    Button validate;
    EditText mail;
    EditText mdp;
    String usermail;
    String usermdp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_connect);


        validate = (Button) findViewById(R.id.validate);
        mail = (EditText) findViewById(R.id.mail);
        mdp = (EditText) findViewById(R.id.mdp);

        InputStream is = null;  String result = "";
        // Envoyer la requête au script PHP.
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        // Envoie de la commande http
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(strURL);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();     }
            catch(Exception e)
            {     Log.e("log_tag", "Error in http connection " + e.toString());
            }
            // Convertion de la requête en string
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
                System.out.println(line);
            }
            is.close();
            result=sb.toString();    }
            catch(Exception e)
            {
                Log.e("log_tag", "Error converting result " + e.toString());}
            String pp="";
            int i=0;
            // Parse les données JSON
            try{     JSONArray jArray = new JSONArray(result);
                for(i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    // Résultats de la requête
                    json_data.getString("email").toString();
                    json_data.getString("Mot_de_passe").toString();
                    }
                    }catch(JSONException e){
                    Log.e("log_tag", "Error parsing data " + e.toString());    }

    }



    }

