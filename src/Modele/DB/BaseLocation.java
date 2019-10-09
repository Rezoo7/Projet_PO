package Modele.DB;

import Modele.Article;

import java.sql.*;


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



    public static void main(String[] args) {
        BaseLocation loc = new BaseLocation();

        Date d1 = new Date(2019-1900,10,10);
        Date d2 = new Date(2019-1900,10,16);
        try {
            loc.addLocation(1,0,d1,d2,5);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Date test = new Date(1234,12,23);

    }
}
