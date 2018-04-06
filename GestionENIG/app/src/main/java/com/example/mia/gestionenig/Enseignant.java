package com.example.mia.gestionenig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
/**
 * Created by mia on 26/03/2018.
 */

public class Enseignant {
    int id;
    String nom;
    String prenom;
    String tel;
    String email;
    String mot_de_passe;
    String matiere;


    public Enseignant(int id, String nom, String prenom, String tel, String email, String mot_de_passe, String matiere) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.matiere = matiere;
    }

    public Enseignant(int id, String nom, String prenom, String email, String mot_de_passe, String matiere) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.matiere = matiere;
    }

    public Enseignant(String nom, String prenom, String tel, String email, String mot_de_passe, String matiere) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;

        this.mot_de_passe = mot_de_passe;
        this.matiere = matiere;
    }


    public Enseignant() {


    }

    public int getId() {
        return id;
    }




    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    @Override
    public String toString() {
        return "Enseignant{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", mot_de_passe='" + mot_de_passe + '\'' +
                ", matiere='" + matiere + '\'' +
                '}';}

}










