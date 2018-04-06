package com.example.mia.gestionenig;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivityProf extends AppCompatActivity {

    String nom,prenom,matiere,email,numtel,id;
    android.support.v7.widget.CardView abs,profil,note,temps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_etud);
        Intent intent = getIntent();
        nom = intent.getStringExtra("nom");
        prenom = intent.getStringExtra("prenom");
        matiere = intent.getStringExtra("mat");
        email = intent.getStringExtra("email");
        numtel = intent.getStringExtra("numtel");
        id = intent.getStringExtra("id");
        abs = (android.support.v7.widget.CardView)findViewById(R.id.abs);
        profil = (android.support.v7.widget.CardView)findViewById(R.id.profil);
        note = (android.support.v7.widget.CardView)findViewById(R.id.note);
        temps = (android.support.v7.widget.CardView)findViewById(R.id.temps);



        abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivityProf.this, MainActivityTableAbsence.class);
                myIntent.putExtra("nom", nom);
                myIntent.putExtra("prenom", prenom);
                myIntent.putExtra("matiere", matiere);
                MainActivityProf.this.startActivity(myIntent);
            }
        });
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivityProf.this, MainActivityProfilProf.class);
                myIntent.putExtra("nom", nom);
                myIntent.putExtra("email",email);
                myIntent.putExtra("prenom", prenom);
                myIntent.putExtra("numtel", numtel);
                myIntent.putExtra("matiere", matiere);
                myIntent.putExtra("id", id);
                MainActivityProf.this.startActivity(myIntent);
            }
        });
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivityProf.this, MainActivityTableNote.class);
                myIntent.putExtra("nom", nom);
                myIntent.putExtra("prenom", prenom);
                myIntent.putExtra("matiere", matiere);
                MainActivityProf.this.startActivity(myIntent);
            }
        });
        temps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }
}
