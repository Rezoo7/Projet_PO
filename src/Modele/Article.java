package Modele;

public abstract class Article {

    private String ref;
    private String marque;
    private String modele;
    private int prix_loc;

    public Article(String ref, String marque, String modele, int prix_loc) {
        this.ref = ref;
        this.marque = marque;
        this.modele = modele;
        this.prix_loc = prix_loc;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }
//
    public int getPrix_loc() {
        return prix_loc;
    }

    public void setPrix_loc(int prix_loc) {
        this.prix_loc = prix_loc;
    }

    public double prix_loc_jrs(int nbjrs){ return getPrix_loc()*nbjrs ;}

    public double prix_loc_sem(){return getPrix_loc()*7;}



    @Override
    public String toString() {
        return "Réference :'" + ref +
                "\n Marque='" + marque +
                "\n Modele='" + modele +
                "\n Prix Location =" + prix_loc + "€ /jour" + "\n";
    }
}
