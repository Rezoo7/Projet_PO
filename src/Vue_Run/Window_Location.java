package Vue_Run;

import Modele.DB.BaseArticle;
import Modele.DB.BaseLocation;
import Modele.Location;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Window_Location extends JFrame {

    private JPanel panel1;
    private JPanel title;
    private JPanel centre;
    private JPanel montant;


    private JList<String> locations;
    private JTextField name;

    private BaseLocation baseLocation = new BaseLocation();
    private BaseArticle baseArticle = new BaseArticle();


    public Window_Location(String nom,JTextField username){

        this.name = username;

        this.panel1 = new JPanel();
        this.title = new JPanel();
        this.centre = new JPanel();
        this.montant = new JPanel();

        this.panel1.setLayout(new BorderLayout());
        this.centre.setLayout(new BorderLayout());
        this.montant.setLayout(new BorderLayout());

        JLabel welcome = new JLabel("Bonjour "+ username.getText() + ", Les Locations : ");
        welcome.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 14));
        welcome.setHorizontalAlignment(JLabel.CENTER);


        DefaultListModel<String> def = new DefaultListModel<String>();
        this.locations = new JList<String>(def);

        for (String item: baseLocation.selectAllLocations_string()) {
            System.out.println(item);
            def.addElement(item);
        }

        this.locations.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 10));
        this.locations.setLayoutOrientation(JList.VERTICAL_WRAP);
        this.locations.setVisibleRowCount(-1);
        this.locations.setFixedCellHeight(30);

        JLabel earnings = new JLabel("Montant Total : "+this.baseLocation.getEarningsAllTime() + " €");
        earnings.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 15));
        earnings.setHorizontalAlignment(JLabel.CENTER);

        this.montant.add(earnings,BorderLayout.CENTER);
        this.centre.add(this.locations,BorderLayout.CENTER);


        this.title.add(welcome);
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
