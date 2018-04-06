package com.example.mia.gestionenig;

/**
 * Created by mia on 27/03/2018.
 */

public class Abs {
    String matiere;
    String nb_abs;

    public Abs() {
    }

    public Abs(String matiere, String nb_abs) {
        this.matiere = matiere;
        this.nb_abs = nb_abs;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getNb_abs() {
        return nb_abs;
    }

    public void setNb_abs(String nb_abs) {
        this.nb_abs = nb_abs;
    }
}
