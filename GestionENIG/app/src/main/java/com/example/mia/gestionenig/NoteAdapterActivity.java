package com.example.mia.gestionenig;
import android.content.Context;


import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NoteAdapterActivity extends ArrayAdapter<Matiere> {

    private List<Matiere> tabmat;
    private Context context;



    public NoteAdapterActivity( Context context, List<Matiere> objects) {
        super(context, R.layout.activity_note_adapter, objects);
        this.context = context;
        tabmat = objects;
    }


    public static class ViewHolder {
        TextView tvid;
        TextView tvnom;
        TextView tvprenom;
        TextView tvnote_dc;
        TextView tvnote_ds;
        TextView tvnote_tp;


    }

    public int getCount() {
        return tabmat.size();
    }

    public Matiere getItem(int position) {
        if (position < getCount()) {
            return this.tabmat.get(position);
        }
        return null;
    }



    public View getView(int position, View convertView, ViewGroup parent) {
        //Add library 'com.android.support:recyclerview-v7-26.1.0' to classpath
        // ça aurait etre Add library 'com.android.support:recyclerview-v7:26.1.0' to classpath
        //complile app
        //never mind
        final ViewHolder holder=new ViewHolder();
        // Get the data item for this position
        Matiere mat = getItem(position);
        // Check if an existing view is being reused, otherwise inflate
        // the view


        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

            rowView = inflater.inflate(R.layout.activity_note_adapter,null);

            holder.tvid = rowView.findViewById(R.id.tvid);
            holder.tvnom = rowView.findViewById(R.id.tvnom);
            holder.tvprenom= rowView.findViewById(R.id.tvprenom);
            holder.tvnote_dc =  rowView.findViewById(R.id.tvnote_dc);
            holder.tvnote_ds =  rowView.findViewById(R.id.tvnote_ds);
            holder.tvnote_tp =  rowView.findViewById(R.id.tvnote_tp);



            rowView.setTag(holder);


        }
        // Lookup view for data population


        // Populate the data into the template view using the data object
        try{
            holder.tvid.setText(String.valueOf(mat.getId()));
            holder.tvnom.setText(mat.getNom());
            holder.tvprenom.setText(mat.getPrenom());


            holder.tvnote_dc.setText(String.valueOf(mat.getNote_dc()));

            holder.tvnote_ds.setText(String.valueOf(mat.getNote_ds()));


            holder.tvnote_tp.setText(String.valueOf(mat.getNote_tp()));

        }catch(NullPointerException ie){
            System.out.print(ie.toString());

        }





        // Return the completed view to render on screen
        return rowView;

    }
}
