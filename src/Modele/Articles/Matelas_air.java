package Modele.Articles;

import Modele.Article;

public class Matelas_air extends Article {

    private int poids_max;
    private int temps_gonflage;
    private int largeur;
    private int longueur;
    private int hauteur;
    private String dimension;

    public Matelas_air(String ref, String marque, String modele, int prix_loc, int poids_max, int temps_gonflage, int largeur, int longueur, int hauteur) {

        super(ref, marque, modele, prix_loc);

        this.poids_max = poids_max;
        this.temps_gonflage = temps_gonflage;
        this.largeur = largeur;
        this.longueur = longueur;
        this.hauteur = hauteur;
    }

    public int getPoids_max() {
        return poids_max;
    }

    public void setPoids_max(int poids_max) {
        this.poids_max = poids_max;
    }

    public int getTemps_gonflage() {
        return temps_gonflage;
    }

    public void setTemps_gonflage(int temps_gonflage) {
        this.temps_gonflage = temps_gonflage;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
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
