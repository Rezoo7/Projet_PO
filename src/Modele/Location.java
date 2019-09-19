package Modele;
import java.util.List;

public class Location {

    private List<Article> article;
    private Client client;
    private String date_debut;
    private String date_fin;
    private int montant_tot;

    public Location(List<Article> article, Client client, String date_debut, String date_fin, int montant_tot) {
        this.article = article;
        this.client = client;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.montant_tot = montant_tot;
    }

    @Override
    public String toString() {
        return "Location{" +
                "article=" + article +
                "\n client=" + client +
                "\n date_debut='" + date_debut + '\'' +
                "\n date_fin='" + date_fin + '\'' +
                "\n montant_tot=" + montant_tot + "€" +
                '}';
    }

    public List<Article> getArticle() {
        return article;
    }

    public void setArticle(List<Article> article) {
        this.article = article;
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
