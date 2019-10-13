package Modele.DB;

import Modele.Article;
import Modele.Location;
import Modele.User;

import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    /**
     * Ajout d'une location à la base de donnée
     * @param id_user
     * @param id_article
     * @param date_debut
     * @param date_fin
     * @param nb_jour
     * @throws SQLException
     */
    public void addLocation(int id_user , int id_article, Date date_debut, Date date_fin, int nb_jour) throws SQLException {

        Article art = baseArticle.getArticleByID(id_article);
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

    /**
     * @return double Montant gagné total
     */
    public double getEarningsAllTime(){
        String sql = "SELECT  montant_total FROM locations;";

        double montant = 0.0;

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                montant = montant + rs.getDouble("montant_total");
            }

            return montant;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /**
     *
     * @return Toutes locations ADMIN en string
     */
    public ArrayList<String> selectAllLocations_string(){
        String sql = "SELECT id_user, id_article, date_debut, date_fin, nombre_jour, montant_total FROM locations;";

        BaseUser baseUser = new BaseUser();
        BaseArticle baseArticle = new BaseArticle();

        ArrayList<String> liste = new ArrayList<String>();

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                String location = "Client : "+ baseUser.getUserByID(rs.getInt("id_user")).getIdentifiant() + "  -  " +
                        "Article  : "+ baseArticle.getArticleByID(rs.getInt("id_article")).getNom();

                String type = baseArticle.getArticleByID(rs.getInt("id_article")).getModele();

                if((type != null && !type.isEmpty())){
                    location = location + " -  Type : "+ baseArticle.getArticleByID(rs.getInt("id_article")).getModele();
                }

                location = location + "  -  Date de Location : du "+ rs.getString("date_debut") + "  au  "+
                        rs.getString("date_fin") + "   -   Nombre Jours : " + rs.getInt("nombre_jour") +
                        "  -  Montant: "+ rs.getDouble("montant_total") +" €" ;

                liste.add(location);
            }

            return liste;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    /**
     *
     * @return Toutes locations en string
     */
    public ArrayList<String> selectAllLocations_string_User(int id_user){
        String sql = "SELECT id_user, id_article, date_debut, date_fin, nombre_jour, montant_total FROM locations WHERE id_user = (?);";

        BaseUser baseUser = new BaseUser();
        BaseArticle baseArticle = new BaseArticle();

        ArrayList<String> liste = new ArrayList<String>();

        try{
             PreparedStatement prep  = this.connect().prepareStatement(sql);
             prep.setInt(1,id_user);
             ResultSet rs    = prep.executeQuery();

            while (rs.next()) {
                String location = "Client : "+ baseUser.getUserByID(rs.getInt("id_user")).getIdentifiant() + "  -  " +
                        "Article  : "+ baseArticle.getArticleByID(rs.getInt("id_article")).getNom();

                String type = baseArticle.getArticleByID(rs.getInt("id_article")).getModele();

                if((type != null && !type.isEmpty())){
                    location = location + " -  Type : "+ baseArticle.getArticleByID(rs.getInt("id_article")).getModele();
                }

                location = location + "  -  Date de Location : du "+ rs.getString("date_debut") + "  au  "+
                        rs.getString("date_fin") + "   -   Nombre Jours : " + rs.getInt("nombre_jour") +
                        "  -  Montant: "+ rs.getDouble("montant_total") +" €" ;

                liste.add(location);
            }

            return liste;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    /**
     *
     * @return Toutes location Object Location
     */
    public LinkedList<Location> selectAllLocations_object() {
        String sql = "SELECT * FROM locations ORDER BY id_user,id_article;";
        LinkedList<Location> liste = new LinkedList<Location>();

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Article art = baseArticle.getArticleByID(rs.getInt("id_article"));
                User user = baseUser.getUserByID(rs.getInt("id_user"));

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                java.util.Date det1 = df.parse(rs.getString("date_debut"));
                java.util.Date det2 = df.parse(rs.getString("date_fin"));

                long ms1 = det1.getTime();
                long ms2 = det2.getTime();

                java.sql.Date date_deb = new java.sql.Date(ms1);
                java.sql.Date date_fin = new java.sql.Date(ms2);

                int nb_jour = rs.getInt("nombre_jour");

                Location loc = new Location(art, user, date_deb, date_fin, nb_jour, art.prix_loc_jrs(nb_jour));

                liste.add(loc);

            }
            return liste;

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @param month : Start Location Month
     * @param year : Start Year Location
     * @return Locations selon le mois et l'année ADMIN
     */
    public ArrayList<String> selectLocationsByMonth_Years(int month, int year){

        String sql = "SELECT id_user, id_article, date_debut, date_fin, nombre_jour, montant_total FROM locations ORDER BY id_user,id_article;";

        BaseUser baseUser = new BaseUser();
        BaseArticle baseArticle = new BaseArticle();

        ArrayList<String> liste = new ArrayList<String>();

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                String dat_deb = rs.getString("date_debut");
                String[] m = dat_deb.split("-"); //m[1] permet de récupérer le mois (ex 209-(11)-16)


                int mth = Integer.parseInt(m[1]);
                int yrs = Integer.parseInt(m[0]);

                if((mth == month) && (yrs == year)){

                    String location = "Client : " + baseUser.getUserByID(rs.getInt("id_user")).getIdentifiant() + "  -  " +
                            "Article  : " + baseArticle.getArticleByID(rs.getInt("id_article")).getNom();

                    String type = baseArticle.getArticleByID(rs.getInt("id_article")).getModele();

                    if ((type != null && !type.isEmpty())) {
                        location = location + " -  Type : " + baseArticle.getArticleByID(rs.getInt("id_article")).getModele();
                    }

                    location = location + "  -  Date de Location : du " + rs.getString("date_debut") + "  au  " +
                            rs.getString("date_fin") + "   -   Nombre Jours : " + rs.getInt("nombre_jour") +
                            "  -  Montant: " + rs.getDouble("montant_total") + " €";

                    liste.add(location);
                }
            }

            return liste;

        } catch (SQLException  e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     *
     * @param month : Start Location Month
     * @param year : Start Year Location
     * @param id_user
     * @return Locations selon le mois et l'année USER
     */
    public ArrayList<String> selectLocationsByMonth_Years_User(int id_user,int month, int year){

        BaseUser baseUser = new BaseUser();
        BaseArticle baseArticle = new BaseArticle();

        ArrayList<String> liste = new ArrayList<String>();

        String sql = "SELECT id_user, id_article, date_debut, date_fin, nombre_jour, montant_total FROM locations WHERE id_user = (?) ORDER BY id_user,id_article;";
        try {
             PreparedStatement prep = this.connect().prepareStatement(sql);
             prep.setInt(1,id_user);
             ResultSet rs = prep.executeQuery();

            while (rs.next()) {
                String dat_deb = rs.getString("date_debut");
                String[] m = dat_deb.split("-"); //m[1] permet de récupérer le mois (ex 209-(11)-16)


                int mth = Integer.parseInt(m[1]);
                int yrs = Integer.parseInt(m[0]);

                if((mth == month) && (yrs == year)){

                    String location = "Article  : " + baseArticle.getArticleByID(rs.getInt("id_article")).getNom();

                    String type = baseArticle.getArticleByID(rs.getInt("id_article")).getModele();

                    if ((type != null && !type.isEmpty())) {
                        location = location + " -  Type : " + baseArticle.getArticleByID(rs.getInt("id_article")).getModele();
                    }

                    location = location + "  -  Date de Location : du " + rs.getString("date_debut") + "  au  " +
                            rs.getString("date_fin") + "   -   Nombre Jours : " + rs.getInt("nombre_jour") +
                            "  -  Montant: " + rs.getDouble("montant_total") + " €";

                    liste.add(location);
                }
            }
            return liste;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * @param year : Start Year Location
     * @return Locations selon le mois et l'année ADMIN
     */
    public ArrayList<String> selectAllLocationsByYear(int year){

        String sql = "SELECT id_user, id_article, date_debut, date_fin, nombre_jour, montant_total FROM locations ORDER BY id_user,id_article;";

        BaseUser baseUser = new BaseUser();
        BaseArticle baseArticle = new BaseArticle();

        ArrayList<String> liste = new ArrayList<String>();

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                String dat_deb = rs.getString("date_debut");
                String[] m = dat_deb.split("-"); //m[0] permet de récupérer l'année (ex 209-(11)-16)

                int yrs = Integer.parseInt(m[0]);

                if( yrs == year){

                    String location = "Client : " + baseUser.getUserByID(rs.getInt("id_user")).getIdentifiant() + "  -  " +
                            "Article  : " + baseArticle.getArticleByID(rs.getInt("id_article")).getNom();

                    String type = baseArticle.getArticleByID(rs.getInt("id_article")).getModele();

                    if ((type != null && !type.isEmpty())) {
                        location = location + " -  Type : " + baseArticle.getArticleByID(rs.getInt("id_article")).getModele();
                    }

                    location = location + "  -  Date de Location : du " + rs.getString("date_debut") + "  au  " +
                            rs.getString("date_fin") + "   -   Nombre Jours : " + rs.getInt("nombre_jour") +
                            "  -  Montant: " + rs.getDouble("montant_total") + " €";

                    liste.add(location);
                }
            }

            return liste;

        } catch (SQLException  e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * @param year : Start Year Location
     * @return Locations selon le mois et l'année ADMIN
     */
    public ArrayList<String> selectAllLocationsByYear_User(int id_user ,int year){

        String sql = "SELECT id_user, id_article, date_debut, date_fin, nombre_jour, montant_total FROM locations WHERE id_user = (?) ORDER BY id_user,id_article;";

        BaseUser baseUser = new BaseUser();
        BaseArticle baseArticle = new BaseArticle();

        ArrayList<String> liste = new ArrayList<String>();

        try{
             PreparedStatement prep  = this.connect().prepareStatement(sql);
             prep.setInt(1,id_user);
             ResultSet rs    = prep.executeQuery();

                while (rs.next()) {
                    String dat_deb = rs.getString("date_debut");
                    String[] m = dat_deb.split("-"); //m[0] permet de récupérer l'année (ex 209-(11)-16)

                    int yrs = Integer.parseInt(m[0]);

                    if (yrs == year) {

                        String location = "Article  : " + baseArticle.getArticleByID(rs.getInt("id_article")).getNom();

                        String type = baseArticle.getArticleByID(rs.getInt("id_article")).getModele();

                        if ((type != null && !type.isEmpty())) {
                            location = location + " -  Type : " + baseArticle.getArticleByID(rs.getInt("id_article")).getModele();
                        }

                        location = location + "  -  Date de Location : du " + rs.getString("date_debut") + "  au  " +
                                rs.getString("date_fin") + "   -   Nombre Jours : " + rs.getInt("nombre_jour") +
                                "  -  Montant: " + rs.getDouble("montant_total") + " €";

                        liste.add(location);
                    }
                }
                return liste;

            }catch (SQLException  e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


    /**
     * @param month : Start Year Location
     * @return Locations selon le mois et l'année ADMIN
     */
    public ArrayList<String> selectAllLocationsByMonth(int month){

        String sql = "SELECT id_user, id_article, date_debut, date_fin, nombre_jour, montant_total FROM locations ORDER BY id_user,id_article;";

        BaseUser baseUser = new BaseUser();
        BaseArticle baseArticle = new BaseArticle();

        ArrayList<String> liste = new ArrayList<String>();

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                String dat_deb = rs.getString("date_debut");
                String[] m = dat_deb.split("-"); //m[0] permet de récupérer l'année (ex 209-(11)-16)

                int mth = Integer.parseInt(m[1]);

                if( mth == month){

                    String location = "Client : " + baseUser.getUserByID(rs.getInt("id_user")).getIdentifiant() + "  -  " +
                            "Article  : " + baseArticle.getArticleByID(rs.getInt("id_article")).getNom();

                    String type = baseArticle.getArticleByID(rs.getInt("id_article")).getModele();

                    if ((type != null && !type.isEmpty())) {
                        location = location + " -  Type : " + baseArticle.getArticleByID(rs.getInt("id_article")).getModele();
                    }

                    location = location + "  -  Date de Location : du " + rs.getString("date_debut") + "  au  " +
                            rs.getString("date_fin") + "   -   Nombre Jours : " + rs.getInt("nombre_jour") +
                            "  -  Montant: " + rs.getDouble("montant_total") + " €";

                    liste.add(location);
                }
            }

            return liste;

        } catch (SQLException  e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * @param month : Start Year Location
     * @return Locations selon le mois et l'année ADMIN
     */
    public ArrayList<String> selectAllLocationsByMonth_User(int id_user,int month){

        String sql = "SELECT id_user, id_article, date_debut, date_fin, nombre_jour, montant_total FROM locations WHERE id_user = (?) ORDER BY id_user,id_article;";

        BaseUser baseUser = new BaseUser();
        BaseArticle baseArticle = new BaseArticle();

        ArrayList<String> liste = new ArrayList<String>();

        try {
             PreparedStatement prep  = this.connect().prepareStatement(sql);
             prep.setInt(1,id_user);
             ResultSet rs = prep.executeQuery();

                while (rs.next()) {
                    String dat_deb = rs.getString("date_debut");
                    String[] m = dat_deb.split("-"); //m[0] permet de récupérer l'année (ex 209-(11)-16)

                    int mth = Integer.parseInt(m[1]);

                    if (mth == month) {

                        String location = "Article  : " + baseArticle.getArticleByID(rs.getInt("id_article")).getNom();

                        String type = baseArticle.getArticleByID(rs.getInt("id_article")).getModele();

                        if ((type != null && !type.isEmpty())) {
                            location = location + " -  Type : " + baseArticle.getArticleByID(rs.getInt("id_article")).getModele();
                        }

                        location = location + "  -  Date de Location : du " + rs.getString("date_debut") + "  au  " +
                                rs.getString("date_fin") + "   -   Nombre Jours : " + rs.getInt("nombre_jour") +
                                "  -  Montant: " + rs.getDouble("montant_total") + " €";

                        liste.add(location);
                    }
                }
                return liste;

            } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Location> getLocationByUserID(String identifiant){
       BaseLocation loc = new BaseLocation();

       List<Location> liste = loc.selectAllLocations_object();
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

        int i = 0;
        for (String location : loc.selectAllLocations_string_User(1)) {
            System.out.println(i +" " + location);
            i++;
        }



    }
}
