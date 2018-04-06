package com.example.mia.gestionenig;

/**
 * Created by mia on 26/03/2018.
 */

public class Matiere {
    private String matiere;
    int id;
    private String nom;
    private String prenom;
    //Double class is the wrapper class for the primitive type double class
    private Double note_dc;
    private Double note_ds;
    private Double note_tp;
    private int nbr_abs;
    private int nbr_lim_abs;

    Matiere() {
    }

    public Matiere(int id, String nom, String prenom, Double note_dc, Double note_ds, Double note_tp, int nbr_abs, int nbr_lim_abs) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.note_dc = note_dc;
        this.note_ds = note_ds;

        this.note_tp = note_tp;
        this.nbr_abs = nbr_abs;
        this.nbr_lim_abs = nbr_lim_abs;

    }



    Matiere(int id,String nom, String prenom, Double note_dc, Double note_ds, Double note_tp ) {
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.note_dc = note_dc;
        this.note_ds = note_ds;
        this.note_tp = note_tp;

    }

    public Matiere(int cin,String nom, String prenom, Double note_dc, Double note_ds, Double note_tp, String matiere) {
        this.id = cin;
        this.nom=nom;
        this.prenom=prenom;
        this.note_dc = note_dc;
        this.note_ds = note_ds;
        this.note_tp = note_tp;
        this.matiere= matiere;
    }
    Matiere(int cin,String nom, String prenom,Double note_dc, Double note_ds, Double note_tp, int nbr_abs, int nbr_lim_abs,String matiere) {
        this.id=cin;
        this.nom=nom;
        this.prenom=prenom;
        this.note_dc = note_dc;
        this.note_ds = note_ds;
        this.note_tp = note_tp;
        this.nbr_abs = nbr_abs;
        this.nbr_lim_abs = nbr_lim_abs;
        this.matiere = matiere;
    }


    public Matiere(int cin,String nom, String prenom, int nbr_abs, String matiere) {
        this.id = cin;
        this.nom=nom;
        this.prenom=prenom;
        this.nbr_abs = nbr_abs;
        this.matiere = matiere;
    }

    public Matiere(int cin,String nom, String prenom, int nbr_abs) {
        this.id = cin;
        this.nom=nom;
        this.prenom=prenom;
        this.nbr_abs = nbr_abs;

    }
    Matiere(int cin,String nom, String prenom, int nbr_abs,int nbr_lim_abs ,String matiere) {
        this.id = cin;
        this.nom=nom;
        this.prenom=prenom;
        this.nbr_abs = nbr_abs;
        this.nbr_lim_abs=nbr_lim_abs;
        this.matiere = matiere;
    }

    Matiere(int cin,String nom, String prenom, int nbr_abs,int nbr_lim_abs ) {
        this.id = cin;
        this.nom=nom;
        this.prenom=prenom;
        this.nbr_abs = nbr_abs;
        this.nbr_lim_abs=nbr_lim_abs;

    }

    int getId() {
        return id;
    }

    void setId(int cin) {
        this.id = cin;
    }

    String getNom() {
        return nom;
    }

    void setNom(String nom) {
        this.nom = nom;
    }

    String getPrenom() {
        return prenom;
    }



    void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    Double getNote_dc() {
        return note_dc;
    }

    void setNote_dc(Double note_dc) {
        this.note_dc = note_dc;
    }

    Double getNote_ds() {
        return note_ds;
    }

    void setNote_ds(Double note_ds) {
        this.note_ds = note_ds;
    }

    Double getNote_tp() {
        return note_tp;
    }

    void setNote_tp(Double note_tp) {
        this.note_tp = note_tp;
    }

    int getNbr_abs() {
        return nbr_abs;
    }

    void setNbr_abs(int nbr_abs) {
        this.nbr_abs = nbr_abs;
    }

    public String getMatiere() {
        return matiere;
    }

    void setMatiere(String matiere) {
        this.matiere = matiere;
    }
    int getNbr_lim_abs() {
        return nbr_lim_abs;
    }
    void setNbr_lim_abs(int nbr_lim_abs) {
        this.nbr_lim_abs = nbr_lim_abs;
    }



}


