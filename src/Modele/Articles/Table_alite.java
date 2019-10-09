package Modele.Articles;

import Modele.Article;

public class Table_alite extends Article {

    //Specifications table alite

    private double longueur_plat;
    private double profondeur_plat;
    private double hauteur_min;
    private double hauteur_max;
    private String dimension;
    private double poids_max;

    public Table_alite(String nom,String ref, String marque, String type, double prix_loc , double longueur_plat, double profondeur_plat, double hauteur_min,double hauteur_max, double poids_max) {

        super(nom,ref,marque,type,prix_loc);

        this.longueur_plat = longueur_plat;
        this.profondeur_plat = profondeur_plat;
        this.hauteur_min = hauteur_min;
        this.hauteur_max = hauteur_max;
        this.poids_max = poids_max;
    }


    public double getLongueur_plat() {
        return longueur_plat;
    }

    public void setLongueur_plat(double longueur_plat) {
        this.longueur_plat = longueur_plat;
    }

    public double getProfondeur_plat() {
        return profondeur_plat;
    }

    public void setProfondeur_plat(double profondeur_plat) {
        this.profondeur_plat = profondeur_plat;
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

    public String getDimension() {
        return this.getLongueur_plat() + " x " + this.getProfondeur_plat() + " x (" + this.getHauteur_min() + " - " + this.getHauteur_max() + ")";
    }

    public double getPoids_max() {
        return poids_max;
    }

    public void setPoids_max(double poids_max) {
        this.poids_max = poids_max;
    }


    @Override
    public String toString() {
        return "Table_alite {" +
                super.toString()+
                "Longueur Plateau =" + longueur_plat + "cm"+
                "\n Profondeur Plateau=" + profondeur_plat +
                "\n Hauteur min = " + hauteur_min + "cm" +
                "\n Hauteur max = " + hauteur_max + "cm" +
                "\n Dimension= " + dimension + "cm" +
                "\n Poids Maximal =" + poids_max + "kg" +
                '}';
    }
}
