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
    public String selectAll(){
        String sql = "SELECT id, nom, reference FROM articles;";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){


            String Select = "";
            // loop through the result set
            while (rs.next()) {
                Select = Select + ("ID : " +rs.getInt("id") +  " |\t" +
                        "Nom : "+ rs.getString("nom") + " |\t" +
                        "Reference : "+rs.getString("reference")) + "\n";
            }
            return Select;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "error";

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
                        "Nom : "+ rs.getString("nom") + " |\t" +
                        "Reference : "+rs.getString("reference"));
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

    public Double selectArticleOption(String ref, String option){
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


    public static void main(String[] args)
    {
       Base app = new Base();
        System.out.println(app.selectAll());
        app.selectArticle("SM");
        System.out.println("Prix Journée : "+ app.selectArticlePrice("SM0001") + "€");
        System.out.println("Capacité levage SM : " + app.selectArticleOption("SM0001","capacite_levage") + " Kg");
    }
}
