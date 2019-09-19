package Modele.Articles;

import Modele.Article;

public class Souleve_malade extends Article {

    private int cap_levage;
    private int degre_pivotage;


    public Souleve_malade(String ref, String marque, String modele, int prix_loc, int capacite_lev, int degre_pivo) {
        super(ref, marque, modele, prix_loc);

        this.cap_levage = capacite_lev;
        this.degre_pivotage = degre_pivo;
    }


    public int getCap_levage() {
        return cap_levage;
    }

    public void setCap_levage(int cap_levage) {
        this.cap_levage = cap_levage;
    }

    public int getDegre_pivotage() {
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
