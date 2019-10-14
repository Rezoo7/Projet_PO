package Vue_Run;

import Controleur.ConnexionController_Admin;
import Controleur.ConnexionController_User;
import Controleur.FormController;
import Controleur.ListArticlesController;
import Modele.DB.*;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Window_Accueil extends JFrame {

    private JList<String> list1;
    private JPanel panel1;
    private JPanel description;
    private JPanel location;
    private JPanel formulaire;
    private BaseArticle base_Art = new BaseArticle();

    private JTextArea desc_texte;


    public Window_Accueil(String nom){
        this.panel1 = new JPanel();
        this.description = new JPanel();
        this.location = new JPanel();
        this.formulaire = new JPanel();


        this.panel1.setLayout(new GridLayout(2,1) );
        this.description.setLayout(new GridLayout(1,1));
        this.location.setLayout(new BorderLayout());
        this.formulaire.setLayout(new GridLayout(6,2));

        DefaultListModel<String> def = new DefaultListModel<String>();
    	this.list1 = new JList<String>(def);

        for (String item: base_Art.selectAllArticles()) {
            def.addElement(base_Art.selectAllArticles().get(base_Art.selectAllArticles().indexOf(item)));
        }

        /* Champs pour le formulaire ( Connexion et location ) */

        JLabel text_id = new JLabel("Identifiant : ");      text_id.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 14));
        JTextField id = new JTextField("ID de Connexion");      id.setFont(new Font("Book Antiqua", Font.ITALIC, 13));
        text_id.setHorizontalAlignment(JLabel.CENTER);

        id.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        id.addMouseListener(new FormController(id));

        JLabel text_mdp = new JLabel("Mot de Passe : ");      text_mdp.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 14));
        text_mdp.setHorizontalAlignment(JLabel.CENTER);
        JPasswordField mdp = new JPasswordField("motdepass ");      mdp.setFont(new Font("Book Antiqua", Font.ITALIC, 13));
        mdp.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        mdp.addMouseListener(new FormController(mdp));
        mdp.setEchoChar('*');


        DateFormat df = new SimpleDateFormat("dd-mm-yyyy");

        JLabel text_datedebut = new JLabel("Date début de Location : ");      text_datedebut.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 14));
        text_datedebut.setHorizontalAlignment(JLabel.CENTER);
        JFormattedTextField datedebut = new JFormattedTextField(df);      datedebut.setFont(new Font("Book Antiqua", Font.ITALIC, 13));
        datedebut.setText("11-10-2019");

        datedebut.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        datedebut.addMouseListener(new FormController(datedebut));

        JLabel text_datefin = new JLabel("Date fin de Location ");      text_datefin.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 14));
        text_datefin.setHorizontalAlignment(JLabel.CENTER);
        JTextField datefin = new JFormattedTextField(df);      datefin.setFont(new Font("Book Antiqua", Font.ITALIC, 13));
        datefin.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        datefin.addMouseListener(new FormController(datefin));
        datefin.setText("16-10-2019");

        JPanel infos = new JPanel();
        JButton connexion_admin = new JButton("Connexion Admin");
        connexion_admin.setBorderPainted(true);
        connexion_admin.setFocusPainted(false);
        connexion_admin.setContentAreaFilled(true);
        connexion_admin.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 15));
        connexion_admin.setBackground(Color.white);

        JButton connexion_user = new JButton("Connexion Utilisateur");
        connexion_user.setBorderPainted(true);
        connexion_user.setFocusPainted(false);
        connexion_user.setContentAreaFilled(true);
        connexion_user.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 15));
        connexion_user.setBackground(Color.white);

        connexion_admin.addActionListener(new ConnexionController_Admin(id,mdp,infos,this.panel1));
        connexion_user.addActionListener((new ConnexionController_User(id,mdp,infos,this.panel1)));

        this.list1.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        this.list1.addListSelectionListener(new ListArticlesController(this.panel1,this.description,this.location,this.desc_texte,this.list1,this.base_Art,id,mdp,infos,datedebut,datefin));
        this.list1.setLayoutOrientation(JList.VERTICAL_WRAP);
        this.list1.setVisibleRowCount(-1);
        this.list1.setFixedCellHeight(40);

        this.panel1.add(list1);
        this.panel1.add(this.description);
        this.panel1.add(this.formulaire);
        this.panel1.add(this.location);

        JPanel empty = new JPanel();
        empty.setBackground(Color.white);

        this.formulaire.add(text_id);
        this.formulaire.add(id);
        this.formulaire.add(text_mdp);
        this.formulaire.add(mdp);
        this.formulaire.add(text_datedebut);
        this.formulaire.add(datedebut);
        this.formulaire.add(text_datefin);
        this.formulaire.add(datefin);
        this.formulaire.add(infos);
        this.formulaire.add(empty);
        this.formulaire.add(connexion_admin);
        this.formulaire.add(connexion_user);




        Image icone = Toolkit.getDefaultToolkit().getImage("/icone/medecine.png");
        this.setIconImage(icone);
        this.setContentPane(this.panel1);
        this.setName(nom);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(900,600));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    };

    public static void main(String args[]){
        Window_Accueil win  = new Window_Accueil("Medical Location App");
    }

}
