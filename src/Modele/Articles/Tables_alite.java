package Modele.Articles;

import Modele.Article;

public class Tables_alite extends Article {

    //Specifications table alite

    private double longueur_plat;
    private double profondeur_plat;
    private String hauteur_var;
    private String dimension;
    private double poids_max;

    public Tables_alite(String ref,String marque,String modele, int prix_loc , double longueur_plat, double profondeur_plat, String hauteur_var, double poids_max) {

        super(ref,marque,modele,prix_loc);

        this.longueur_plat = longueur_plat;
        this.profondeur_plat = profondeur_plat;
        this.hauteur_var = hauteur_var;
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

    public String getHauteur_var() {
        return hauteur_var;
    }

    public void setHauteur_var(String hauteur_var) {
        this.hauteur_var = hauteur_var;
    }

    public String getDimension() {
        return this.getLongueur_plat() + " x " + this.getProfondeur_plat() + " x " + this.getHauteur_var();
    }

    public double getPoids_max() {
        return poids_max;
    }

    public void setPoids_max(double poids_max) {
        this.poids_max = poids_max;
    }


    @Override
    public String toString() {
        return "Tables_alite {" +
                super.toString()+
                "Longueur Plateau =" + longueur_plat + "cm"+
                "\n Profondeur Plateau=" + profondeur_plat +
                "\n Hauteur Variable = " + hauteur_var + "cm" +
                "\n Dimension= " + dimension + "cm" +
                "\n Poids Maximal =" + poids_max + "kg" +
                '}';
    }
}
