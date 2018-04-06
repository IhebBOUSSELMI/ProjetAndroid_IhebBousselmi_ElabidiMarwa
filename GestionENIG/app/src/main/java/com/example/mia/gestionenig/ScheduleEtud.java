package com.example.mia.gestionenig;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ScheduleEtud extends AppCompatActivity {
    ListView semaines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_etud);

        semaines = (ListView)findViewById(R.id.semaines);

        List<String> values = new ArrayList<>();
        values.add("Semaine-1");
        values.add("Semaine-2");
        values.add("Semaine-3");
        values.add("Semaine-4");
        values.add("Semaine-5");
        values.add("Semaine-6");
        values.add("Semaine-7");
        values.add("Semaine-8");
        values.add("Semaine-9");
        values.add("Semaine-10");
        values.add("Semaine-11");
        values.add("Semaine-12");
        values.add("Semaine-13");
        values.add("Semaine-14");
        values.add("Semaine-15");
        semaines.setAdapter(new ArrayAdapter<String>(ScheduleEtud.this, R.layout.simlpe_list_item_1, values));

        semaines.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(ScheduleEtud.this, "" + position, 2).show();

            }
        });

    }
}
