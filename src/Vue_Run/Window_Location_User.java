package Vue_Run;

import Controleur.LocationsSelectedController_User;
import Modele.DB.BaseArticle;
import Modele.DB.BaseLocation;

import javax.swing.*;
import java.awt.*;

public class Window_Location_User extends JFrame {

    private JPanel panel1;
    private JPanel title;
    private JPanel centre;
    private JPanel montant;

    private JTextField name;

    private BaseLocation baseLocation = new BaseLocation();
    private BaseArticle baseArticle = new BaseArticle();


    public Window_Location_User(String nom,JTextField username){

        this.name = username;

        this.panel1 = new JPanel();
        this.title = new JPanel();
        this.centre = new JPanel();
        this.montant = new JPanel();

        this.panel1.setLayout(new BorderLayout());
        this.centre.setLayout(new BorderLayout());
        this.montant.setLayout(new GridLayout(1,3));
        this.title.setLayout(new GridLayout(1,5));

        JLabel welcome = new JLabel("Bonjour "+ username.getText() + ", Vos Locations : ");
        welcome.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 14));
        welcome.setHorizontalAlignment(JLabel.CENTER);

        JLabel infos = new JLabel();

        JComboBox months = new JComboBox();
        JComboBox years = new JComboBox();

        months.addItem("Tous");
        years.addItem("Toutes");
        for(int i = 1; i <= 12;i++){
            months.addItem(i);
        }

        for(int i = 2015; i<= 2030;i++){
            years.addItem(i);
        }


        JLabel text_my = new JLabel("Mois / Année :");
        text_my.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 13));
        months.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 13));
        years.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 13));



        years.addActionListener(new LocationsSelectedController_User(this.centre,months,years,username.getText(),infos));
        months.addActionListener(new LocationsSelectedController_User(this.centre,months,years,username.getText(),infos));



        this.title.add(welcome);
        this.title.add(text_my);
        this.title.add(months);
        this.title.add(years);

        this.panel1.add(this.title, BorderLayout.NORTH);
        this.panel1.add(this.centre,BorderLayout.CENTER);
        this.panel1.add(this.montant,BorderLayout.SOUTH);

        this.setContentPane(this.panel1);
        this.setName(nom);
        this.setPreferredSize(new Dimension(900,600));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }


    public static void main(String[] args) {

        //Window_Location win = new Window_Location("Locations");
    }
}
