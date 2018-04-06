package com.example.mia.gestionenig;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MainActivityMatieresAdapter extends ArrayAdapter<Notes> {


    private List<Notes> tabnot;
    private Context context;



    public MainActivityMatieresAdapter( Context context, List<Notes> objects) {
        super(context, R.layout.activity_main_liste_matieres, objects);
        this.context = context;
        tabnot = objects;
    }


    public static class ViewHolder {
        TextView id;
        TextView name;
    }

    public int getCount() {
        return tabnot.size();
    }

    public Notes getItem(int position) {
        if (position < getCount()) {
            return this.tabnot.get(position);
        }
        return null;
    }



    public View getView(int position, View convertView, ViewGroup parent) {
        //Add library 'com.android.support:recyclerview-v7-26.1.0' to classpath
        // ça aurait etre Add library 'com.android.support:recyclerview-v7:26.1.0' to classpath
        //complile app
        //never mind
        final MainActivityMatieresAdapter.ViewHolder holder=new MainActivityMatieresAdapter.ViewHolder();
        // Get the data item for this position
        Notes not = getItem(position);
        // Check if an existing view is being reused, otherwise inflate
        // the view


        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

            rowView = inflater.inflate(R.layout.activity_main_matieres_adapter,null);

            holder.id = rowView.findViewById(R.id.id);
            rowView.setTag(holder);


        }
        // Lookup view for data population


        // Populate the data into the template view using the data object
        try{
            holder.id.setText(String.valueOf(not.getId()));
            holder.name.setText(not.getName());

        }catch(NullPointerException ie){
            System.out.print(ie.toString());

        }





        // Return the completed view to render on screen
        return rowView;

    }


}