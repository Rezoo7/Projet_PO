package Modele;

public abstract class Article {

    private String nom;
    private String ref;
    private String marque;
    private String type;
    private double prix_loc;

    public Article(String nom ,String ref, String marque, String type, double prix_loc) {
        this.nom = nom;
        this.ref = ref;
        this.marque = marque;
        this.type = type;
        this.prix_loc = prix_loc;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
        return type;
    }

    public void setModele(String modele) {
        this.type = modele;
    }
//
    public double getPrix_loc() {
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
                "\n Modele='" + type +
                "\n Prix Location =" + prix_loc + "€ /j" + "\n";
    }
}
