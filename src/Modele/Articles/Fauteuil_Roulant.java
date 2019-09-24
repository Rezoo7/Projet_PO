package Modele.Articles;

import Modele.Article;

public class Fauteuil_Roulant extends Article {

    private double largeur_assise;
    private int poids_max;

    public Fauteuil_Roulant(String ref, String marque, String modele, int prix_loc, double largeur, int poids_max) {
        super(ref, marque, modele, prix_loc);

        this.largeur_assise = largeur;
        this.poids_max = poids_max;
    }


    public double getLargeur_assise() {
        return largeur_assise;
    }

    public void setLargeur_assise(double largeur_assise) {
        this.largeur_assise = largeur_assise;
    }

    public int getPoids_max() {
        return poids_max;
    }

    public void setPoids_max(int poids_max) {
        this.poids_max = poids_max;
    }

    @Override
    public String toString() {
        return "Fauteuil_Roulant{" +
                super.toString() +
                "Largeur d'Assise=" + largeur_assise + "cm" +
                "\n Poids Max = " + poids_max + "kg" +
                '}';
    }
}
