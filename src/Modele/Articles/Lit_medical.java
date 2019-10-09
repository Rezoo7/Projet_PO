package Modele.Articles;

import Modele.Article;

public class Lit_medical extends Article {

    private double longueur;
    private double largeur;
    private double hauteur_min;
    private double hauteur_max;

    private double poids_max;
    private String hauteur_var;
    private String dimensions;


    public Lit_medical(String nom,String ref, String marque, String type, double prix_loc, double longueur, double largeur, double hauteur_min, double hauteur_max, double poids_max) {
        super(nom,ref, marque, type, prix_loc);

        this.longueur = longueur;
        this.largeur = largeur;
        this.hauteur_min = hauteur_min;
        this.hauteur_max = hauteur_max;
        this.poids_max = poids_max;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public double getHauteur_min() {
        return hauteur_min;
    }

    public void setHauteur_min(double hauteur_min) {
        this.hauteur_min = hauteur_min;
    }

    public double getHauteur_max() {
        return hauteur_max;
    }

    public void setHauteur_max(double hauteur_max) {
        this.hauteur_max = hauteur_max;
    }

    public double getPoids_max() {
        return poids_max;
    }

    public void setPoids_max(int poids_max) {
        this.poids_max = poids_max;
    }

    public String getHauteurVar(){
        return this.getHauteur_min() + "-" + this.getHauteur_max();
    }

    public String getDimensions(){
        return this.getLongueur() + " x " + this.getLargeur() + " x " + this.getHauteurVar() + "cm";
    }

    @Override
    public String toString() {
        return "Lits_medical {" +
                super.toString() +
                "Longueur =" + longueur + "cm" +
                "\n Largeur =" + largeur + "cm" +
                "\n Poids Max =" + poids_max + "kg" +
                "\n Dimensions = " + dimensions +
                '}';
    }
}
