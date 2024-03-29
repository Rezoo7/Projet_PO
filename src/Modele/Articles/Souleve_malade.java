package Modele.Articles;

import Modele.Article;

public class Souleve_malade extends Article {

    private double cap_levage;
    private double degre_pivotage;


    public Souleve_malade(String nom,String ref, String marque, String type, double prix_loc, double capacite_lev, double degre_pivo) {
        super(nom,ref, marque, type, prix_loc);

        this.cap_levage = capacite_lev;
        this.degre_pivotage = degre_pivo;
    }


    public double getCap_levage() {
        return cap_levage;
    }

    public void setCap_levage(int cap_levage) {
        this.cap_levage = cap_levage;
    }

    public double getDegre_pivotage() {
        return degre_pivotage;
    }

    public void setDegre_pivotage(int degre_pivotage) {
        this.degre_pivotage = degre_pivotage;
    }

    @Override
    public String toString() {
        return "Souleve_malade {" +
                super.toString() +
                "Capacité de Levage = " + cap_levage + "kg" +
                "\n Degré  de Pivotage=" + degre_pivotage + "°" +
                '}';
    }
}
