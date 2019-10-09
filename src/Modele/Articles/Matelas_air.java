package Modele.Articles;

import Modele.Article;

public class Matelas_air extends Article {

    private double poids_max;
    private double temps_gonflage;
    private double largeur;
    private double longueur;
    private double hauteur;
    private String dimension;

    public Matelas_air(String nom, String ref, String marque, String type, double prix_loc, double poids_max, double temps_gonflage, double largeur, double longueur, double hauteur) {

        super(nom,ref, marque, type, prix_loc);

        this.poids_max = poids_max;
        this.temps_gonflage = temps_gonflage;
        this.largeur = largeur;
        this.longueur = longueur;
        this.hauteur = hauteur;
    }

    public double getPoids_max() {
        return poids_max;
    }

    public void setPoids_max(double poids_max) {
        this.poids_max = poids_max;
    }

    public double getTemps_gonflage() {
        return temps_gonflage;
    }

    public void setTemps_gonflage(double temps_gonflage) {
        this.temps_gonflage = temps_gonflage;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public double getHauteur() {
        return hauteur;
    }

    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }

    public String getDimension(){
        return this.getLargeur() + " x " + this.getLongueur() + " x " + this.getHauteur();
    }


    @Override
    public String toString() {
        return "Matelas_air {" +
                super.toString() +
                "Poids_max=" + poids_max + "kg" +
                "\n Temps Gonflage=" + temps_gonflage + "s" +
                "\n Dimension='" + dimension + '\'' +
                '}';
    }
}
