package Vue_Run;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Window_Location extends JFrame {

    private JPanel panel1;
    private JPanel title;
    private JPanel centre;
    private JPanel list_locations;
    private JPanel description;


    private JList<String> articles;
    private JTextField name;

    public Window_Location(String nom,JList<String> articles, JTextField username){

        this.articles = articles;
        this.name = username;

        this.panel1 = new JPanel();

        this.title = new JPanel();
        this.centre = new JPanel();

        this.list_locations = new JPanel();
        this.description = new JPanel();

        this.panel1.setLayout(new BorderLayout());


        JLabel welcome = new JLabel("Bonjour "+ username.getText() + ", vos Locations : ");
        welcome.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 14));
        welcome.setHorizontalAlignment(JLabel.CENTER);


        this.centre.add(this.articles);
        this.centre.add(this.description);

        this.title.add(welcome);
        this.panel1.add(this.title, BorderLayout.NORTH);
        this.panel1.add(this.centre,BorderLayout.CENTER);

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
