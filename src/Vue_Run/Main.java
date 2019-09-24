package Vue_Run;

import Modele.Articles.Lit_medical;
import java.sql.*;

public class Main {

    public static void main(String[] args)
    {
        int stock_litMed = 0;

        Lit_medical lit = new Lit_medical("Lit Med 1","LucasTechnologie"
                ,"Quali1",3,
                120.0,60.0,49.0,75.0,195,"Standard");

        Connection c = null;

        try{
            Class.forName("org.sqlite.JDBC");
            c= DriverManager.getConnection("jdbc:sqlite:location.db");
            System.out.println("SQLite base Connected");



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
