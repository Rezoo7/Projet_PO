package Modele.Articles;

import Modele.Article;

public class Lits_medical extends Article {

    private double longueur;
    private double largeur;
    private double hauteur_min;
    private double hauteur_max;

    private int poids_max;
    private String type;
    private String hauteur_var;
    private String dimensions;


    public Lits_medical(String ref, String marque, String modele, int prix_loc, int stock, double longueur, double largeur, double hauteur_min, double hauteur_max, int poids_max, String type) {
        super(ref, marque, modele, prix_loc, stock);

        this.longueur = longueur;
        this.largeur = largeur;
        this.hauteur_min = hauteur_min;
        this.hauteur_max = hauteur_max;
        this.poids_max = poids_max;
        this.type = type;
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

    public int getPoids_max() {
        return poids_max;
    }

    public void setPoids_max(int poids_max) {
        this.poids_max = poids_max;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
                "\n Type = " + type +
                "\n Dimensions = " + dimensions +
                '}';
    }
}
