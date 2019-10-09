package Modele;

public class User {

    private String identifiant;
    private String password;

    public User(String identifiant, String password) {
        this.identifiant = identifiant;
        this.password = password;
    }

    public String getNom() {
        return identifiant;
    }

    public void setNom(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getPrenom() {
        return password;
    }

    public void setPrenom(String password) {
        this.password =  password;
    }

}
