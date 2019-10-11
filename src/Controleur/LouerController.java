package Controleur;

import Modele.DB.BaseUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LouerController implements ActionListener {

    private JTextField id;
    private JTextField mdp;
    private JPanel info;

    public LouerController(JTextField id, JTextField mdp, JPanel info){
        this.id = id;
        this.mdp = mdp;
        this.info = info;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        BaseUser baseUser = new BaseUser();
        System.out.println(id.getText());
        System.out.println(mdp.getText());

        if(baseUser.verifyUser(this.id.getText(),this.mdp.getText())){
            JLabel info_connexion = new JLabel("Connecté en tant que User");
            info_connexion.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 12));

            this.info.removeAll();
            this.info.add(info_connexion);
        }
        else if(baseUser.verifyIdent_User(this.id.getText())){

            JLabel info_connexion = new JLabel("Mot de Passe Incorrect");
            info_connexion.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 12));

            this.info.removeAll();
            this.info.add(info_connexion);
        }
        else{
            JLabel info_connexion = new JLabel("Identifiant et Mot de Passe Incorrect");
            info_connexion.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 12));

            this.info.removeAll();
            this.info.add(info_connexion);
        }

    }
}
