package com.example.mia.gestionenig;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mia on 26/03/2018.
 */



    public class MainActivityAbsenceAdapter extends ArrayAdapter<Matiere> {
        List<Matiere> tabmat;
        Context context;
        View.OnClickListener listener;

        public MainActivityAbsenceAdapter(Context context, List<Matiere> objects) {
            super(context, R.layout.activity_main_absence_adapter, objects);
            this.context = context;
            tabmat = objects;
        }



    public static class ViewHolder {
            TextView tvid;
            TextView tvnom;
            TextView tvprenom;
            TextView tvnbr_abs;
            TextView tvnbr_lim_abs;
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
            final MainActivityAbsenceAdapter.ViewHolder holder = new MainActivityAbsenceAdapter.ViewHolder();
            // Get the data item for this position
            Matiere mat = getItem(position);
            // Check if an existing view is being reused, otherwise inflate
            // the view


            View rowView = convertView;

            if (rowView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                rowView = inflater.inflate(R.layout.activity_main_absence_adapter, null);

                holder.tvid = rowView.findViewById(R.id.tvid);
                holder.tvnom = rowView.findViewById(R.id.tvnom);
                holder.tvprenom = rowView.findViewById(R.id.tvprenom);
                holder.tvnbr_abs = rowView.findViewById(R.id.tvnbr_abs);
                holder.tvnbr_lim_abs = rowView.findViewById(R.id.tvnbr_lim_abs);

                rowView.setTag(holder);


            }

            try {
                holder.tvid.setText(String.valueOf(mat.getId()));
                holder.tvnom.setText(mat.getNom());
                holder.tvprenom.setText(mat.getPrenom());
                holder.tvnbr_abs.setText(String.valueOf(mat.getNbr_abs()));
                holder.tvnbr_lim_abs.setText(String.valueOf(mat.getNbr_lim_abs()));


            } catch (NullPointerException ie) {
                System.out.print(ie.toString());

            }

            // Return the completed view to render on screen
            return rowView;

        }

    }




