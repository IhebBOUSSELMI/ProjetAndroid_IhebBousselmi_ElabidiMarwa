package com.example.mia.gestionenig;

import android.content.Intent;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginTeacher extends AppCompatActivity {
    Button cnxt;
    EditText Logon, password;
    ProgressDialog progressDialog;
    private static final String strURL = "http://192.168.1.33:80/miniprojet/list_teacher.php";
    /**
     * Called when the activity is first created.
     */



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logint);

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        cnxt = (Button) findViewById(R.id.cnxs);
        Logon = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

    }
    public void sendToken(View view) {

        InputStream is = null;
        String result = "";
        // Envoyer la requête au script PHP.
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        // Envoie de la commande http
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(strURL);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
        }
        // Convertion de la requête en string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
                System.out.println(line);
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
        }
        String pp = "";
        int i = 0;
        // Parse les données JSON
        try {
            JSONArray jArray = new JSONArray(result);
            for (i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                // Résultats de la requête
                String id = json_data.getString("id");
                String nom = json_data.getString("nom");
                String prenom = json_data.getString("prenom");
                String mot_de_passe = json_data.getString("mot_de_passe");
                String email = json_data.getString("email");
                String matiere= json_data.getString("matiere");
                String numtel = json_data.getString("tel");
                if (mot_de_passe.equals(password.getText().toString())) {
                    if (email.equals(Logon.getText().toString())) {

                        Intent principale = new Intent(LoginTeacher.this,MainActivityProf.class);
                        principale.putExtra("nom", nom);
                        principale.putExtra("prenom", prenom);
                        principale.putExtra("email", email);
                        principale.putExtra("mat", matiere);
                        principale.putExtra("numtel", numtel);
                        principale.putExtra("id", id);
                        startActivity(principale);
                        break;

                    } else {
                        Toast.makeText(LoginTeacher.this, "E-mail invalide ", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginTeacher.this, "mot de passe invalide ", Toast.LENGTH_LONG).show();
                }
            }
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering Device...");
        progressDialog.show();

        final String token = SharedPreference.getInstance(this).getDeviceToken();
        final String email = Logon.getText().toString();

        if (token == null) {
            progressDialog.dismiss();
            Toast.makeText(this, "Token not generated", Toast.LENGTH_LONG).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER_DEVICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(LoginTeacher.this, obj.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginTeacher.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("token", token);
                return params;
            }
        };
        FcmVolley.getInstance(this).addToRequestQueue(stringRequest);
    }
}