package Modele.DB;

import Modele.User;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class BaseUser {

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


    public boolean verifyUser(String identifiant, String mdp){

        String sql = "SELECT identifiant, password FROM users WHERE identifiant='" + identifiant + "' AND password='"+ mdp+"';";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            return rs.next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;

    }

    public boolean verifyID_User(int id){

        String sql = "SELECT id FROM users WHERE id=" + id + ";";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            return rs.next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;

    }

    public boolean verifyIdent_User(String identifiant){

        String sql = "SELECT id FROM users WHERE identifiant='" + identifiant + "';";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            return rs.next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;

    }

    public boolean verifyAdmin(String id , String mdp){
        if(id.equals("demo") && mdp.equals("demo")){
            return true;
        }
        return false;
    }


    public User getUserByID(int id){
        String sql = "SELECT * FROM users WHERE id="+id+";";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            String identifiant = rs.getString("identifiant");
            String password = rs.getString("password");
            String adress = rs.getString("adress");

            User user = new User(identifiant,password,adress);
            return user;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int getIdUserByident(String identifiant){
        String sql = "SELECT id FROM users WHERE identifiant='"+identifiant+"';";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            if(rs.next()){
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public void addUser(String id , String mdp) throws SQLException {

        String insertion = "INSERT INTO users (identifiant, password) VALUES (?,?)";

        try (
             PreparedStatement preparedStatement = this.connect().prepareStatement(insertion)) {

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, mdp);

            int row = preparedStatement.executeUpdate();

            // rows affected
            System.out.println(row); //1

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void deleteUser(String id , String mdp) throws SQLException {

        try (
            Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE identifiant= ? AND password = ?")) {

            stmt.setString(1,id);
            stmt.setString(2,mdp);
            stmt.executeUpdate();

            System.out.println("Record deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        BaseUser base = new BaseUser();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        String d1 = "11-10-2019";
        String d2 = "18-10-2019";

        try {
            java.util.Date udob = df.parse(d1);
            long ms = udob.getTime();
            java.sql.Date dnew = new java.sql.Date(ms);

            System.out.println(dnew);



        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
