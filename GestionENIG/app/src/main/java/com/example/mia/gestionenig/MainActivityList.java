package com.example.mia.gestionenig;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

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
import java.util.HashMap;

public class MainActivityList extends AppCompatActivity {
    LinearLayout L1, L2;
    FirebaseAuth auth;
    JSONParser jsonParser = new JSONParser();
    ProgressDialog pDialog;
    ProgressBar progressBar;
    Button bt, bs, register, login, validate, cnxt, cnxs;
    EditText mail, mdp, nom, prenom, cin, matiere, inputEmail, inputPassword;
    Animation uptodown, downtoup;
    Dialog MyDialog, Registert, Registers, Logint, Logins;
    //lignes de l'auth
    //JSON Node names
    private static String url_create_product = "http://192.168.1.33/miniprojet/login.php";
    private static final String TAG_SUCCESS = "success";
    AlertDialog.Builder alert;
    JSONObject json;
    int success;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        auth = FirebaseAuth.getInstance();
        L1 = (LinearLayout) findViewById(R.id.L1);
        L2 = (LinearLayout) findViewById(R.id.L2);
        bt = findViewById(R.id.bt);
        bs = findViewById(R.id.bs);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        L1.setAnimation(uptodown);
        L2.setAnimation(downtoup);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivityList.this, LoginTeacher.class);
                //myIntent.putExtra("key", value); //Optional parameters
                MainActivityList.this.startActivity(myIntent);
            }
        });


        bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivityList.this, LoginStudent.class);
                //myIntent.putExtra("key", value); //Optional parameters
                MainActivityList.this.startActivity(myIntent);
            }


        });


    }

}