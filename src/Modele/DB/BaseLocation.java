package Modele.DB;

import Modele.Article;
import Modele.Location;
import Modele.User;

import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;


public class BaseLocation {

    private BaseUser baseUser;
    private BaseArticle baseArticle;

    public BaseLocation(){
        this.baseUser = new BaseUser();
        this.baseArticle = new BaseArticle();
    }
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

    public void addLocation(int id_user , int id_article, Date date_debut, Date date_fin, int nb_jour) throws SQLException {

        Article art = baseArticle.selectArticleByID(id_article);
        double prix_loc_jrs = art.prix_loc_jrs(nb_jour);

        String insertion = "INSERT INTO locations (id_user, id_article,date_debut,date_fin,nombre_jour,montant_total) VALUES (?,?,?,?,?,?)";

        boolean verif_user = this.baseUser.verifyID_User(id_user);
        boolean verif_article = this.baseArticle.verifyID_Article(id_article);

        try (
                PreparedStatement preparedStatement = this.connect().prepareStatement(insertion)) {

            if( verif_user && verif_article ){

                preparedStatement.setInt(1, id_user);
                preparedStatement.setInt(2, id_article);
                preparedStatement.setString(3,date_debut.toString());
                preparedStatement.setString(4,date_fin.toString());
                preparedStatement.setInt(5,nb_jour);
                preparedStatement.setDouble(6,prix_loc_jrs);

                int row = preparedStatement.executeUpdate();
                if(row == 1){
                    System.out.println("La location a bien été ajoutée");
                }
            }
            else if(!verif_user){
                System.out.println("L'utilisateur renseigné n'existe pas");
            }
            else if(!verif_article){
                System.out.println("L'article renseigné n'existe pas ");
            }


        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Location> getAllLocations(){
        String sql = "SELECT * FROM locations;";

        List<Location> liste = new LinkedList<Location>();

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while(rs.next()){

                int id_user = rs.getInt("id_user");
                int id_article = rs.getInt("id_article");
                String date_debut = rs.getString("date_debut");
                String date_fin = rs.getString("date_fin");
                int nb_jour = rs.getInt("nombre_jour");

                User user = baseUser.getUserByID(id_user);
                Article art = baseArticle.selectArticleByID(id_article);

                String Date1= date_debut;
                Date date1 = Date.valueOf(date_debut);

                String Date2= date_fin;
                Date date2 = Date.valueOf(date_fin);

                Location loc = new Location(art,user,date1,date2,nb_jour,art.prix_loc_sem());
                liste.add(loc);
            }

            return liste;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public List<Location> getLocationByUserID(String identifiant){
       BaseLocation loc = new BaseLocation();

       List<Location> liste = loc.getAllLocations();
       List<Location> listeUser = new LinkedList<Location>();

       for(int i = 0; i <= liste.size()-1;i++){

           if(liste.get(i).getClient().getIdentifiant().equals(identifiant)){
               listeUser.add(liste.get(i));
               //System.out.println(liste.get(i).getArticle().getNom() + " " + liste.get(i).getClient().getIdentifiant() );

           }

       }

       return listeUser;
    }

    public static void main(String[] args) {
        BaseLocation loc = new BaseLocation();

        Date d1 = new Date(2019-1900,10,10);
        Date d2 = new Date(2019-1900,10,16);
        /*try {
            loc.addLocation(1,3,d1,d2,5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
        Date test = new Date(1234,12,23);

        List<Location> liste = loc.getAllLocations();

        for(int i = 0; i <= liste.size()-1;i++){
            System.out.println(liste.get(i).getArticle().getNom() + " - " + liste.get(i).getClient().getIdentifiant() );
        }

        System.out.println("\n");
        loc.getLocationByUserID("demo");
    }
}
