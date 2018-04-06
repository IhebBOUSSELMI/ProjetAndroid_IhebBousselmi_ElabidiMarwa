package com.example.mia.gestionenig;

/**
 * Created by mia on 27/03/2018.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class MYSQLManager {

    private String Adresse=null;

    public MYSQLManager (String Adresse) {
        this.Adresse =Adresse ;

    }

    String HTTPExecute(boolean Mode, ArrayList<NameValuePair> nameValuePairs){
        HttpGet httpGet =null;
        HttpResponse httpResponse =null;
        StringBuilder sb  =null;
        InputStream is = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            if (Mode ==true) {
                httpGet = new HttpGet(Adresse);    //GET
                httpResponse = httpClient.execute(httpGet);
            }
            else {
                HttpPost httppost = new HttpPost(Adresse);       				//POST
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                httpResponse = httpClient.execute(httppost);

            }


            HttpEntity entity = httpResponse.getEntity();

            if ( entity != null) {
                is = entity.getContent();
                // insert to database
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();


            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return sb.toString();
    }

    void SQLExecute(ArrayList<NameValuePair> nameValuePairs) {

        HTTPExecute(  false,  nameValuePairs);

    }


}