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

public class MainActivityProfilProf extends AppCompatActivity {
    TextView idprof,nomprof,prenomprof,mailprof,phoneprof,matprof;
    String nom,prenom,email,numtel,mat,id;
    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profil_prof);

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);


        idprof = (TextView) findViewById(R.id.idprof);
        nomprof = (TextView) findViewById(R.id.nomprof);
        prenomprof = (TextView) findViewById(R.id.prenomprof);
        mailprof = (TextView) findViewById(R.id.mailprof);
        phoneprof = (TextView) findViewById(R.id.phoneprof);
        matprof = (TextView)findViewById(R.id.matprof) ;
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        nom = intent.getStringExtra("nom");
        prenom = intent.getStringExtra("prenom");
        email = intent.getStringExtra("email");
        numtel = intent.getStringExtra("numtel");
        mat = intent.getStringExtra("matiere");



        idprof.setText(id);
        nomprof.setText(nom);
        prenomprof.setText(prenom);
        mailprof.setText(email);
        phoneprof.setText(numtel);
        matprof.setText(mat);





    }
}