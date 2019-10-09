package Modele.DB;

import Modele.Article;
import Modele.Articles.*;

import java.sql.*;
import java.util.ArrayList;

public class BaseArticle {

    /**
     * Connect to the location.db database
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string (A modifié selon le chemin du projet !!)
        String url = "jdbc:sqlite:C:\\Users\\maxim\\Documents\\IUT - LP\\Annee3_LP\\PO\\Projet_PO\\location.db";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Select All the datas with id, name and reference
     */
    public ArrayList<String> selectAll(){
        String sql = "SELECT id, nom, reference, prix_location FROM articles;";

        ArrayList<String> liste = new ArrayList<String>();

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                liste.add("Nom : "+ rs.getString("nom") + "   -   " +
                        "Reference : "+rs.getString("reference"));

            }

            return liste;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public Article selectArticleByID(int id) {
        String sql = "SELECT * FROM articles WHERE id="+id+";";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            String nom = rs.getString("nom");
            String ref = rs.getString("reference");
            String marque = rs.getString("marque");
            double prix_loc = rs.getDouble("prix_location");
            double poids = rs.getDouble("poids_max");
            double longueur_plat = rs.getDouble("longueur_plat");
            double profondeur_plat = rs.getDouble("profondeur_plat");
            String type = rs.getString("type");
            double hauteur_min = rs.getDouble("hauteur_min");
            double hauteur_max = rs.getDouble("hauteur_max");
            double largeur = rs.getDouble("largeur");
            double longueur = rs.getDouble("longueur");
            double hauteur = rs.getDouble("hauteur");
            double temps_gonflage = rs.getDouble("temps_gonflage");
            double capacite_levage = rs.getDouble("capacite_levage");
            double degre = rs.getDouble("degre_pivotage");

            //Creation selon le Type d'article (Lit Mediaclisé, Souleve Malade...)

            if(ref.startsWith("LM")){
                Lit_medical article = new Lit_medical(nom,ref,marque,type,prix_loc,longueur,largeur,hauteur_min,hauteur_max,poids);
                article.toString();
                return article;
            }
            else if(ref.startsWith("SM")){
                Souleve_malade article = new Souleve_malade(nom,ref,marque,type,prix_loc,capacite_levage,degre);
                article.toString();
                return article;
            }
            else if(ref.startsWith("MA")){
                Matelas_air article = new Matelas_air(nom,ref,marque,type,prix_loc,poids,temps_gonflage,largeur,longueur,hauteur);
                article.toString();
                return article;
            }
            else if(ref.startsWith("FR")){
                Fauteuil_Roulant article = new Fauteuil_Roulant(nom,ref,marque,type,prix_loc,largeur,poids);
                article.toString();
                return article;
            }
            else if(ref.startsWith("TA")){
                Table_alite article = new Table_alite(nom,ref,marque,type,prix_loc,longueur_plat,profondeur_plat,hauteur_min,hauteur_max,poids);
                article.toString();
                return article;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    /**
     * @param type (exemple LM : Lit Médical...)
     */
    public String selectArticlesWithType(String type){

        String sql = "SELECT id, nom, reference FROM articles WHERE reference LIKE '"+ type+"%%%%';";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            String Select = "";
            // loop through the result set
            while (rs.next()) {
                Select = Select + ("ID : " +rs.getInt("id") +  " |\t" +
                        " Nom : "+ rs.getString("nom") + " |\t" +
                        " Reference : "+rs.getString("reference"));
            }
            return Select;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "error";
    }

    public int selectArticlePrice(int id){
        String sql = "SELECT id, nom, reference, prix_location FROM articles WHERE id = '"+ id + "';";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println("Le prix est de  : " + rs.getInt("prix_location") +
                        "€  Soit "+ rs.getInt("prix_location")*7 + "€ /Semaine");

                return rs.getInt("prix_location");
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public Number selectArticleOption(String ref, String option){
        String sql = "SELECT id, nom, reference,"+ option +" FROM articles WHERE reference = '"+ ref + "';";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            Double select= 0.0;
            // loop through the result set
            while (rs.next()) {
                select = rs.getDouble(option);
            }

            return select;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0.0;
    }

    public String[] selectAllOptionsArticle(int id){
        String sql = "SELECT * FROM articles WHERE id = '"+ id + "';";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            String[] params = new String[17];

            // loop through the result set
            while (rs.next()) {
               params[0] = ""; //"ID :"+rs.getInt("ID");
               params[1] = "Nom : "+rs.getString("nom");
               params[2] = "Référence : "+rs.getString("reference");
               params[3] = "Marque : "+ rs.getString("marque");
               params[4] = "Prix Location : "+rs.getInt("prix_location") + "€ /jour";
               params[5] = "Poids Maximal : "+rs.getDouble("poids_max") + " Kg";
               params[6] = "Longueur Plateau : "+rs.getDouble("longueur_plat")+" cm";
               params[7] = "Profondeur Plateau : "+rs.getDouble("profondeur_plat")+" cm";
               params[8] = "Type : "+rs.getString("type");
               params[9] = "Hauteur Maximale : "+rs.getDouble("hauteur_max")+" cm";
               params[10] = "Hauteur Minimale : "+rs.getDouble("hauteur_min") +" cm";
               params[11] = "Largeur : "+rs.getDouble("largeur")+" cm";
               params[12] = "Longueur : "+rs.getDouble("longueur")+" cm";
               params[13] = "Hauteur : "+rs.getDouble("hauteur")+ " cm";
               params[14] = "Temps Gonflage : "+rs.getDouble("temps_gonflage")+ "s";
               params[15] = "Capacité Levage : "+rs.getDouble("capacite_levage")+ "Kg";
               params[16] = "Degré de Pivotage : "+rs.getDouble("degre_pivotage")+"°";

            }



            return params;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public boolean verifyID_Article(int id){

        String sql = "SELECT id FROM articles WHERE id=" + id + ";";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            return rs.next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;

    }



    public static void main(String[] args)
    {
        BaseArticle app = new BaseArticle();
       /* System.out.println(app.selectAll());
        app.selectArticle("SM");
        System.out.println("Prix Journée : "+ app.selectArticlePrice("SM0001") + "€");
        System.out.println("Capacité levage SM : " + app.selectArticleOption("SM0001","capacite_levage") + " Kg");


        String[] tab = app.selectAllOptionsArticle(1);

        for(int i = 0; i <= tab.length-1;i++){
            if(!tab[i].contains(" 0.0") && !tab[i].contains(" null")){
                System.out.println(tab[i]);
            }
        }*/

        System.out.println(app.selectArticleByID(1));
    }
}

