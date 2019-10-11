package Controleur;

import Modele.DB.BaseLocation;
import Modele.DB.BaseUser;
import Vue_Run.Window_Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LouerController implements ActionListener {

    private JTextField id;
    private JTextField mdp;
    private JTextField datedebut;
    private JTextField datefin;

    private JPanel info;
    private JPanel principale;
    private JList<String> liste_art;


    private BaseUser base_user = new BaseUser();
    private BaseLocation base_location = new BaseLocation();

    public final static long SECOND_MILLIS = 1000;
    public final static long MINUTE_MILLIS = SECOND_MILLIS*60;
    public final static long HOUR_MILLIS = MINUTE_MILLIS*60;
    public final static long DAY_MILLIS = HOUR_MILLIS*24;
    public final static long YEAR_MILLIS = DAY_MILLIS*365;

    public LouerController(JTextField id, JTextField mdp, JPanel info,JPanel prin,JList<String> articles,JTextField datedebut,JTextField datefin){
        this.id = id;
        this.mdp = mdp;
        this.info = info;
        this.principale = prin;
        this.liste_art = articles;
        this.datedebut =datedebut;
        this.datefin = datefin;



    }

    @Override
    public void actionPerformed(ActionEvent e) {

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

       /* System.out.println(id.getText());
        System.out.println(mdp.getText());*/
       String date1 = this.datedebut.getText();
       String date2 = this.datefin.getText();

        try {
            java.util.Date det1 = df.parse(date1);
            java.util.Date det2 = df.parse(date2);

            long ms1 = det1.getTime();
            long ms2 = det2.getTime();

            java.sql.Date date_deb = new java.sql.Date(ms1);
            java.sql.Date date_fin = new java.sql.Date(ms2);

            System.out.println(date_deb + "  " + date_fin);
            int nb_jours = (int)((date_fin.getTime()/DAY_MILLIS) - (date_deb.getTime()/DAY_MILLIS));
            System.out.println(nb_jours);


            if(this.base_user.verifyUser(this.id.getText(),this.mdp.getText())){

                //this.base_location.addLocation(this.base_user.getIdUserByident(this.id.getText()),this.liste_art.getSelectedIndex(),date_deb,date_fin,nb_jours);

                JLabel info_connexion = new JLabel("<html> Connecté <br> La Location a bien été ajoutée ! </html>");
                info_connexion.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 14));
                info_connexion.setHorizontalAlignment(JLabel.CENTER);
                this.info.removeAll();
                this.info.add(info_connexion);

                Window_Location window = new Window_Location("Locations",this.liste_art,this.id);

            }
            else if(this.base_user.verifyIdent_User(this.id.getText())){

                JLabel info_connexion = new JLabel("Mot de Passe Incorrect");
                info_connexion.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 14));
                info_connexion.setHorizontalAlignment(JLabel.CENTER);

                this.info.removeAll();
                this.info.add(info_connexion);
            }
            else{
                JLabel info_connexion = new JLabel("Identifiant et Mot de Passe Incorrect");
                info_connexion.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 14));
                info_connexion.setHorizontalAlignment(JLabel.CENTER);

                this.info.removeAll();
                this.info.add(info_connexion);
            }

            this.principale.revalidate();
            this.principale.repaint();



        } catch (ParseException ex) {
            ex.printStackTrace();
        } /*catch (SQLException ex) {
            ex.printStackTrace();
        }*/

    }
}
