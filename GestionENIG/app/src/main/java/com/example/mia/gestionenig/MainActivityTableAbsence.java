package com.example.mia.gestionenig;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.ListView;
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

public class MainActivityTableAbsence extends AppCompatActivity {

    public static final String strURL = "http://192.168.1.33:80/miniprojet/liste.php";
    ArrayList<Matiere> tabmat1 ;
    ArrayList<String> idList ;
    MainActivityAbsenceAdapter arrayAdapter;
    ListView listview;
    TextView tvnbr_abs,tvnbr_abs1,tvid,tvnom,tvprenom;
    ArrayList<NameValuePair> params;
    String result = "";
    int nbr,nbr_lim_abs,nbr_abs;
    Button btnenregistrer,btnplus;
    String nom,prenom,matiere;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_table_absence);

        Intent intent = getIntent();
        nom = intent.getStringExtra("nom");
        prenom = intent.getStringExtra("prenom");
        matiere = intent.getStringExtra("matiere");
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        listview = findViewById(R.id.listview);



        params=new ArrayList<>();
        idList=new ArrayList<>();
        tabmat1=getList();
        arrayAdapter = new MainActivityAbsenceAdapter(this, tabmat1);
        listview.setAdapter(arrayAdapter);



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivityTableAbsence.this);
                View mView = getLayoutInflater().inflate(R.layout.modifier_absence, null);

                btnenregistrer = mView.findViewById(R.id.btnenregistrer);
                btnplus=mView.findViewById(R.id.btnplus);
                tvnbr_abs=mView.findViewById(R.id.tvnbr_abs);
                tvnbr_abs1=mView.findViewById(R.id.tvnbr_abs1);
                tvid=mView.findViewById(R.id.tvid);
                tvnom=mView.findViewById(R.id.tvnom);
                tvprenom=mView.findViewById(R.id.tvprenom);
                tvid.setText(String.valueOf(tabmat1.get(position).getId()));
                tvnom.setText(tabmat1.get(position).getNom());
                tvprenom.setText(tabmat1.get(position).getPrenom());
                tvnbr_abs.setText(String.valueOf(tabmat1.get(position).getNbr_abs()));
                nbr_abs=tabmat1.get(position).getNbr_abs();
                nbr_lim_abs=tabmat1.get(position).getNbr_lim_abs();
                nbr=nbr_abs;
                btnplus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(nbr<nbr_lim_abs){
                            nbr=nbr+1;
                            tvnbr_abs1.setText(String.valueOf(nbr));}
                        else{
                            Toast.makeText(MainActivityTableAbsence.this, "Le nombre d'absence ne peut pas dépasser :"+" "+nbr_lim_abs, Toast.LENGTH_SHORT).show();
                        }

                    }
                });


                btnenregistrer.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View view) {
                        try{
                            String url = "http://192.168.1.33:80/miniprojet/modifier2.php";
                            ArrayList<NameValuePair> params = new ArrayList<>();
                            params.add(new BasicNameValuePair("tablename",matiere));
                            params.add(new BasicNameValuePair("id", idList.get(position)));
                            params.add(new BasicNameValuePair("nbr_abs", String.valueOf(nbr)));
                            String resultServer = getHttpPost(url, params);
                            Log.d(null, "resultServer - updateData:: " + resultServer);
                            Log.d(null,String.valueOf(nbr));
                            String strStatusID = "0";


                            JSONObject c;
                            try {
                                c = new JSONObject(resultServer);
                                strStatusID = c.getString("StatusID");

                            } catch (JSONException e) {

                                e.printStackTrace();
                            }

                            // Prepare Save Data
                            if (strStatusID.equals("0")) {
                                Toast.makeText(MainActivityTableAbsence.this, "Not Updated", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivityTableAbsence.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                tabmat1=getList();
                                arrayAdapter = new MainActivityAbsenceAdapter(MainActivityTableAbsence.this, tabmat1);
                                listview.setAdapter(arrayAdapter);

                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }


                    }
                });



                mBuilder.setView(mView);
                AlertDialog dialog=mBuilder.create();

                dialog.show();
                dialog.getWindow().setLayout(500, 600);

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
    public ArrayList getList(){
            ArrayList<Matiere> tabmat=new ArrayList<>();
        int i;
        // Parse les données JSON
        try {
            result=getHttpPost(strURL,params);
            JSONArray jArray = new JSONArray(result);

            for (i = 0; i < jArray.length(); i++) {
                Matiere mat = new Matiere();
                JSONObject json_data = jArray.optJSONObject(i);
                idList.add(String.valueOf(json_data.optInt("id")));
                mat.setId(json_data.getInt("id"));
                mat.setNom(json_data.getString("nom"));
                mat.setPrenom(json_data.getString("prenom"));

                mat.setNbr_abs(json_data.optInt("nbr_abs"));

                mat.setNbr_lim_abs(json_data.optInt("nbr_lim_abs"));
                tabmat.add(mat);
            }
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }
        return tabmat;
    }
}
