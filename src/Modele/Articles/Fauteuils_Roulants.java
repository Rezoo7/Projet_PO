package Modele.Articles;

import Modele.Article;

public class Fauteuils_Roulants extends Article {

    private double largeur_assise;
    private int poids_max;

    public Fauteuils_Roulants(String ref, String marque, String modele, int prix_loc, int stock, double largeur,int poids_max) {
        super(ref, marque, modele, prix_loc, stock);

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
}
