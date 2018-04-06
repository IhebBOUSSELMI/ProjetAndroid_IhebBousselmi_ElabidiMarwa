package com.example.mia.gestionenig;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivityListeMatieres extends AppCompatActivity {
    ListView listview;
    String nom,prenom,cin;
    public static final String strURL = "http://192.168.1.33:80/miniprojet/liste_matiere.php";
    public static final String url = "http://192.168.1.33:80/miniprojet/filter.php";
    ArrayList<String> tabmat ;
    ArrayList<NameValuePair> params,param;
    String result = "";
    ArrayAdapter<String> arrayAdapter;
    TextView tvnote_dc,tvnote_ds,tvnote_tp,tvnbr_abs;
    Matiere mat1;
    ArrayList<Matiere> tab;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        nom = intent.getStringExtra("nom");
        prenom = intent.getStringExtra("prenom");
        cin = intent.getStringExtra("cin");

        setContentView(R.layout.activity_main_liste_matieres);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        listview = findViewById(R.id.listview);
        params = new ArrayList<>();
        tabmat = new ArrayList<>();

        int i;
        // Parse les données JSON
        try {
            result = getHttpPost(strURL, params);
            JSONArray jArray = new JSONArray(result);

            for (i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.optJSONObject(i);
                tabmat.add(json_data.getString("nom"));

                String resultServer = getHttpPost(strURL, params);
                Log.d(null, "resultServer - updateData:: " + resultServer);

            }
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tabmat);
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivityListeMatieres.this);
                View mView = getLayoutInflater().inflate(R.layout.consulter, null);
                Toast.makeText(MainActivityListeMatieres.this, tabmat.get(position), Toast.LENGTH_SHORT).show();

                tvnote_dc=mView.findViewById(R.id.tvnote_dc);
                tvnote_ds=mView.findViewById(R.id.tvnote_ds);
                tvnote_tp=mView.findViewById(R.id.tvnote_tp);
                tvnbr_abs=mView.findViewById(R.id.tvnbr_abs);
                Toast.makeText(MainActivityListeMatieres.this, cin, Toast.LENGTH_SHORT).show();


                try {


                     param = new ArrayList<>();
                  //l'id mté3 l'étudiant elli connecté
                    param.add(new BasicNameValuePair("id", cin));
                    param.add(new BasicNameValuePair("tablename", tabmat.get(position)));

                    String resultServer = getHttpPost(url,param);
                    Log.d(null, "resultServer - updateData:: " + resultServer);
                    Log.d(null, cin);
                    Log.d(null,tabmat.get(position));

                    JSONObject c;
                    try{
                        c = new JSONObject(resultServer);
                        Toast.makeText(MainActivityListeMatieres.this, String.valueOf(c.optDouble("note_dc")), Toast.LENGTH_SHORT).show();
                        tvnote_dc.setText(String.valueOf(c.optDouble("note_dc")));

                        tvnote_ds.setText(String.valueOf(c.optDouble("note_ds")));

                        tvnote_tp.setText(String.valueOf(c.optDouble("note_tp")));

                        tvnbr_abs.setText(String.valueOf(c.optInt("nbr_abs")));
                    }

                    catch (Exception e){
                        e.printStackTrace();
                    }
/*

tab= new ArrayList<>();
                        int i;
                        // Parse les données JSON
                        try {
                            result=getHttpPost(strURL,params);
                            JSONArray jArray = new JSONArray(result);

                            for (i = 0; i < jArray.length(); i++) {
                                mat1 = new Matiere();
                                JSONObject json_data = jArray.optJSONObject(i);
                                mat1.setId(json_data.getInt("id"));
                                mat1.setNom(json_data.getString("nom"));
                                mat1.setPrenom(json_data.getString("nom"));

                                mat1.setNote_dc(json_data.optDouble("note_dc"));

                                mat1.setNote_dc(json_data.optDouble("note_ds"));
                                mat1.setNote_tp(json_data.optDouble("note_tp"));
                                mat1.setNbr_abs(json_data.optInt("nbr_abs"));
                                mat1.setNbr_lim_abs(json_data.optInt("nbr_lim_abs"));
                                tab.add(mat1);



                            }
                        } catch (JSONException e) {
                            Log.e("log_tag", "Error parsing data " + e.toString());
                        }


*/
                } catch (Exception e1) {
                    e1.printStackTrace();
                }


                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();

                dialog.show();
                dialog.getWindow().setLayout(300, 400);

            }

        });
    }

    public String getHttpPost(String url,List<NameValuePair> params) {
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