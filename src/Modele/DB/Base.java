package Modele.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Base {

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

    /**
     * @param type (exemple LM : Lit Médical...)
     */
    public String selectArticle(String type){

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

    public int selectArticlePrice(String ref){
        String sql = "SELECT id, nom, reference, prix_location FROM articles WHERE reference = '"+ ref + "';";

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
               params[0] = "ID :"+rs.getInt("ID");
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



    public static void main(String[] args)
    {
        Base app = new Base();
        System.out.println(app.selectAll());
        app.selectArticle("SM");
        System.out.println("Prix Journée : "+ app.selectArticlePrice("SM0001") + "€");
        System.out.println("Capacité levage SM : " + app.selectArticleOption("SM0001","capacite_levage") + " Kg");


        String[] tab = app.selectAllOptionsArticle(1);

        for(int i = 0; i <= tab.length-1;i++){
            if(!tab[i].contains(" 0.0") && !tab[i].contains(" null")){
                System.out.println(tab[i]);
            }
        }
    }
}

