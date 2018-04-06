package com.example.mia.gestionenig;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mia.gestionenig.Constants;
import com.example.mia.gestionenig.FcmVolley;
import com.example.mia.gestionenig.MainActivityEtud;
import com.example.mia.gestionenig.R;
import com.example.mia.gestionenig.SharedPreference;

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
import java.util.HashMap;
import java.util.Map;

public class MainActivityProfilEtud extends AppCompatActivity {
TextView idetd,nometd,prenometd,mailetd,phoneetd;
String cin,nom,prenom,email,mot_de_passe,numtel;

    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profil_etud);

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);


        idetd = (TextView) findViewById(R.id.idetd);
        nometd = (TextView) findViewById(R.id.nometd);
        prenometd = (TextView) findViewById(R.id.prenometd);
        mailetd = (TextView) findViewById(R.id.mailetd);
        phoneetd = (TextView) findViewById(R.id.phoneetd);
        Intent intent = getIntent();
        cin = (intent.getStringExtra("cin"));
        nom = intent.getStringExtra("nom");
        prenom = intent.getStringExtra("prenom");
        email = intent.getStringExtra("mailetd");
        mot_de_passe = intent.getStringExtra("mot_de_passe");
        numtel = intent.getStringExtra("phoneetd");



                idetd.setText(cin);
                nometd.setText(nom);
                prenometd.setText(prenom);
                mailetd.setText(email);
                phoneetd.setText(numtel);




}
}