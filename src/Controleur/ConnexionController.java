package Controleur;

import Modele.DB.BaseUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnexionController implements ActionListener {

    private String id;
    private String mdp;
    private JPanel info;

    public ConnexionController(JTextField id, JTextField mdp, JPanel info){
        this.id = id.getText();
        this.mdp = mdp.getText();
        this.info = info;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        BaseUser baseUser = new BaseUser();
        System.out.println(id);
        System.out.println(mdp);

        if(baseUser.verifyAdmin(this.id,this.mdp)){
            JLabel info_connexion = new JLabel("Conneceter en tant qu'Admin");
            this.info.add(info_connexion);
            System.out.println("admin valide");
        }
        else{
            JLabel info_connexion = new JLabel("Non Admin");
            this.info.add(info_connexion);
            System.out.println("admin non valide");
        }

    }
}
