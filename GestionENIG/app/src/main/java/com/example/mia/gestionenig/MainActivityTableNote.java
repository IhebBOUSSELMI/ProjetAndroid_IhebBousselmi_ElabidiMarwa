package com.example.mia.gestionenig;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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


public class MainActivityTableNote extends AppCompatActivity {
    public static final String strURL = "http://192.168.1.33:80/miniprojet/liste.php";
    ArrayList<Matiere> tabmat1 ;
    ArrayList<String> idList ;
    NoteAdapterActivity arrayAdapter;
    ListView listview;
    String mytext;
    String modif;
    InputStream is = null;
TextView tvid,tvnom,tvprenom;
    ArrayList<NameValuePair> params;
    String result = "";
    Spinner spinner;
    NumberPicker picker1,picker2;
    Button btnenregistrer;
    String [] Values1;
    String [] Values2;
    String val1, val2;
    String matiere;
    Double note;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_table_note);
        Intent intent = getIntent();
        matiere = intent.getStringExtra("matiere");
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //it = getIntent();
        // enseignant = (Enseignant) it.getSerializableExtra("enseignant");
        listview = findViewById(R.id.listview);

        Values1 = new String[21];

        for (int i = 0; i < 21; i++) {
            Values1[i] = String.valueOf(i);
        }
        Values2 = new String[4];
        Values2[0] = String.valueOf(0);
        Values2[1] = String.valueOf(25);
        Values2[2] = String.valueOf(50);
        Values2[3] = String.valueOf(75);

        idList = new ArrayList<>();
        params = new ArrayList<>();
        tabmat1 = new ArrayList<>();

        tabmat1=getList();
        arrayAdapter = new NoteAdapterActivity(this, tabmat1);
        listview.setAdapter(arrayAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivityTableNote.this);
                View mView = getLayoutInflater().inflate(R.layout.modifier_note, null);
                spinner = mView.findViewById(R.id.spinner);
                picker1 = mView.findViewById(R.id.picker1);
                picker2 = mView.findViewById(R.id.picker2);
                btnenregistrer = mView.findViewById(R.id.btnenregistrer);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivityTableNote.this,
                                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.champs_note));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                picker1.setMinValue(0);
                picker1.setMaxValue(20);
                picker1.setDisplayedValues(Values1);
                picker2.setMinValue(0);
                picker2.setMaxValue(3);
                picker2.setDisplayedValues(Values2);
                tvid=mView.findViewById(R.id.tvid);
                tvnom=mView.findViewById(R.id.tvnom);
                tvprenom=mView.findViewById(R.id.tvprenom);
                tvid.setText(String.valueOf(tabmat1.get(position).getId()));
                tvnom.setText(tabmat1.get(position).getNom());
                tvprenom.setText(tabmat1.get(position).getPrenom());

                picker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        val1 = String.valueOf(i1);
                        val2="0";
                    }
                });
                picker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        if(val1.equals("20")){
                            val2="0";
                        }else if(i1==1){
                            val2 = String.valueOf(25);
                        }
                        else if(i1==2){
                            val2 = String.valueOf(50);
                        }
                        else if(i1==3){
                            val2 = String.valueOf(75);
                        }
                    }
                });


                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View selecteditemview, int i, long l) {
                        mytext = spinner.getSelectedItem().toString();
                        Toast.makeText(MainActivityTableNote.this, mytext , Toast.LENGTH_SHORT).show();
                    }
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });

                btnenregistrer.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (mytext.equals("DC") || mytext.equals("DS") || mytext.equals("TP")) {
                            modif = val1 + "." + val2;

                                        try {
                                            String url = "http://192.168.1.33:80/miniprojet/modifier.php";
                                            ArrayList<NameValuePair> params = new ArrayList<>();
                                            params.add(new BasicNameValuePair("tablename", matiere));
                                            params.add(new BasicNameValuePair("id", idList.get(position)));
                                            params.add(new BasicNameValuePair("note_", modif));
                                            if (mytext.equals("DC")) {
                                                params.add(new BasicNameValuePair("note", "note_dc"));

                                            } else if (mytext.equals("DS")) {
                                                params.add(new BasicNameValuePair("note", "note_ds"));
                                            } else if (mytext.equals("TP")) {
                                                params.add(new BasicNameValuePair("note", "note_tp"));
                                            } else {
                                                Toast.makeText(MainActivityTableNote.this, "veuillez choisir un paramètre valide", Toast.LENGTH_SHORT).show();
                                            }
                                            String resultServer = getHttpPost(url, params);
                                            Log.d(null, "resultServer - updateData:: " + resultServer);
                                            Log.d(null, modif);
                                            Log.d(null, mytext);
                                            Log.d(null, matiere);

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
                                                Toast.makeText(MainActivityTableNote.this, "Not Updated", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(MainActivityTableNote.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                                tabmat1 = getList();
                                                arrayAdapter = new NoteAdapterActivity(MainActivityTableNote.this, tabmat1);
                                                listview.setAdapter(arrayAdapter);

                                            }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                        }
                        else{
                            Toast.makeText(MainActivityTableNote.this, "-- n'est pas une note!", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog=mBuilder.create();
                dialog.show();
                dialog.getWindow().setLayout(500, 800);
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
        ArrayList<Matiere>tabmat=new ArrayList<>();

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
                mat.setNote_dc(json_data.optDouble("note_dc"));
                mat.setNote_ds(json_data.optDouble("note_ds"));
                mat.setNote_tp(json_data.optDouble("note_tp"));

                tabmat.add(mat);
            }
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }
    return tabmat;}
}


