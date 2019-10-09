package Modele;

public class User {

    private String identifiant;
    private String password;
    private String adress;

    public User(String identifiant, String password,String adress) {
        this.identifiant = identifiant;
        this.password = password;
        this.adress = adress;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public void setPassword(String password) {
        this.password =  password;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
