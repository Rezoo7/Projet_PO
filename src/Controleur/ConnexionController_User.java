package Controleur;

import Modele.DB.BaseUser;
import Vue_Run.Window_Location_Admin;
import Vue_Run.Window_Location_User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnexionController_User implements ActionListener {

    private JTextField id;
    private JTextField mdp;
    private JPanel info;
    private JPanel principale;

    public ConnexionController_User(JTextField id, JTextField mdp, JPanel info, JPanel prin){
        this.id = id;
        this.mdp = mdp;
        this.info = info;
        this.principale = prin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        BaseUser baseUser = new BaseUser();

        if(baseUser.verifyUser(this.id.getText(),this.mdp.getText())){
            JLabel info_connexion = new JLabel("Connecté en tant que User");
            info_connexion.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 12));

            this.info.removeAll();
            this.info.add(info_connexion);

            Window_Location_User window = new Window_Location_User("Locations",this.id);
        }
        else{
            JLabel info_connexion = new JLabel("Idetifiant ou Mot de pass User Incorrect");
            info_connexion.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 12));

            this.info.removeAll();
            this.info.add(info_connexion);
        }

        this.principale.revalidate();
        this.principale.repaint();
    }
}
