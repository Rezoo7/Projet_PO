package Modele;

import java.sql.Date;

public class Location {

    private Article article;
    private User user;
    private Date date_debut;
    private Date date_fin;
    private int nombre_jour;
    private double montant_tot;

    public Location(Article article, User user, Date date_debut, Date date_fin, int nombre_jours, double montant_tot) {

        this.article = article;
        this.user = user;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nombre_jour = nombre_jours;
        this.montant_tot = montant_tot;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getNombre_jour() {
        return nombre_jour;
    }

    public void setNombre_jour(int nombre_jour) {
        this.nombre_jour = nombre_jour;
    }

    public Article getarticle() {
        return article;
    }

    public User getClient() {
        return user;
    }

    public void setClient(User client) {
        this.user = client;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public double getMontant_tot() {
        return montant_tot;
    }

    public void setMontant_tot(int montant_tot) {
        this.montant_tot = montant_tot;
    }
}
