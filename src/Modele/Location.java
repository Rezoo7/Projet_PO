package Modele;
import java.util.List;

public class Location {

    private List<Article> articles;
    private Client client;
    private String date_debut;
    private String date_fin;
    private int montant_tot;

    public Location(List<Article> articles, Client client, String date_debut, String date_fin, int montant_tot) {
        this.articles = articles;
        this.client = client;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.montant_tot = montant_tot;
    }

    @Override
    public String toString() {
        return "Location{" +
                "articles=" + this.getarticles().toString() +
                "\n client=" + this.getClient().getNom() + " " + this.getClient().getPrenom() +
                "\n date_debut='" + date_debut +
                "\n date_fin='" + date_fin +
                "\n montant_tot=" + montant_tot + "€" +
                '}';
    }

    public List<Article> getarticles() {
        return articles;
    }

    public void addarticles(Article articles) {
        this.articles.add(articles);
    }

    public void delarticles(Article articles){
        this.articles.remove(articles);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public int getMontant_tot() {
        return montant_tot;
    }

    public void setMontant_tot(int montant_tot) {
        this.montant_tot = montant_tot;
    }
}
