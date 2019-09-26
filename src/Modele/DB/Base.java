package Modele.DB;

import java.sql.*;

public class Base {

    /**
     * Connect to the location.db database
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string (A modifié selon le chemin du projet !!)
        String url = "jdbc:sqlite:C://users/maxim/Documents/IUT - LP/Annee3_LP/PO/Projet_PO/location.db";

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
    public void selectAll(){
        String sql = "SELECT id, nom, reference FROM articles;";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println("ID : " +rs.getInt("id") +  " |\t" +
                        "Nom : "+ rs.getString("nom") + " |\t" +
                        "Reference : "+rs.getString("reference"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * @param type (exemple LM : Lit Médical...)
     */
    public void selectArticle(String type){

        String sql = "SELECT id, nom, reference FROM articles WHERE reference LIKE '"+ type+"%%%%';";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println("ID : " +rs.getInt("id") +  " |\t" +
                        "Nom : "+ rs.getString("nom") + " |\t" +
                        "Reference : "+rs.getString("reference"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectArticlePrice(String ref){
        String sql = "SELECT id, nom, reference, prix_location FROM articles WHERE reference = '"+ ref + "';";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println("Le prix est de  : " + rs.getInt("prix_location") +
                        "€  Soit "+ rs.getInt("prix_location")*7 + "€ /Semaine");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args)
    {
       Base app = new Base();
       //app.selectAll();
       //app.SelectArticle("SM");
        app.selectArticlePrice("SM0001");
    }
}
