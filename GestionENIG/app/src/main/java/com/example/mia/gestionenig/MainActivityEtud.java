package com.example.mia.gestionenig;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivityEtud extends AppCompatActivity {
String nom,prenom,cin,mailetd,phoneetd;

    android.support.v7.widget.CardView abs,profil,note,temps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_etud);
        Intent intent = getIntent();
        nom = intent.getStringExtra("nom");
        prenom = intent.getStringExtra("prenom");
        cin = intent.getStringExtra("cin");
        phoneetd = intent.getStringExtra("numtel");
        mailetd = intent.getStringExtra("email");

        abs = (android.support.v7.widget.CardView)findViewById(R.id.abs);
        profil = (android.support.v7.widget.CardView)findViewById(R.id.profil);
        note = (android.support.v7.widget.CardView)findViewById(R.id.note);
        temps = (android.support.v7.widget.CardView)findViewById(R.id.temps);

        temps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivityEtud.this, MainActivityTimeEtud.class);
                MainActivityEtud.this.startActivity(it);
            }
        });


        abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivityEtud.this, MainActivityListeMatieres.class);
                myIntent.putExtra("nom", nom);
                myIntent.putExtra("prenom", prenom);
                myIntent.putExtra("cin", cin);
                MainActivityEtud.this.startActivity(myIntent);
            }
        });
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivityEtud.this, MainActivityProfilEtud.class);
                myIntent.putExtra("nom", nom);
                myIntent.putExtra("prenom", prenom);
                myIntent.putExtra("cin", cin);
                myIntent.putExtra("mailetd", mailetd);
                myIntent.putExtra("phoneetd", phoneetd);

                MainActivityEtud.this.startActivity(myIntent);
            }
        });
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivityEtud.this, MainActivityListeMatieres.class);
                myIntent.putExtra("nom", nom);
                myIntent.putExtra("prenom", prenom);
                myIntent.putExtra("cin",cin);
                MainActivityEtud.this.startActivity(myIntent);
            }
        });




    }
}
