package Controleur;

import Modele.DB.BaseUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnexionController implements ActionListener {

    private JTextField id;
    private JTextField mdp;
    private JPanel info;
    private JPanel principale;

    public ConnexionController(JTextField id, JTextField mdp, JPanel info, JPanel prin){
        this.id = id;
        this.mdp = mdp;
        this.info = info;
        this.principale = prin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        BaseUser baseUser = new BaseUser();

        if(baseUser.verifyAdmin(this.id.getText(),this.mdp.getText())){
            JLabel info_connexion = new JLabel("Connecté en tant qu'Admin");
            info_connexion.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 12));

            this.info.removeAll();
            this.info.add(info_connexion);
            System.out.println("admin valide");
        }
        else{
            JLabel info_connexion = new JLabel("Idetifiant ou Mot de pass Admin Incorrect");
            info_connexion.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 12));

            this.info.removeAll();
            this.info.add(info_connexion);
        }

        this.principale.revalidate();
        this.principale.repaint();
    }
}
