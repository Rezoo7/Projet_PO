package Vue_Run;

import Controleur.ConnexionController;
import Controleur.FormController;
import Controleur.ListArticlesController;
import Modele.DB.*;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private JList<String> list1;
    private JPanel panel1;
    private JPanel description;
    private JPanel location;
    private JPanel formulaire;
    private BaseArticle base_Art;

    private JTextArea desc_texte;


    public Window(String nom){

        this.panel1 = new JPanel();
        this.description = new JPanel();
        this.location = new JPanel();
        this.formulaire = new JPanel();
        this.base_Art = new BaseArticle();

        this.panel1.setLayout(new GridLayout(2,1) );
        this.description.setLayout(new GridLayout(1,1));
        this.location.setLayout(new BorderLayout());
        this.formulaire.setLayout(new GridLayout(3,2));

        DefaultListModel<String> def = new DefaultListModel<String>();
    	this.list1 = new JList<String>(def);

        for (String item: base_Art.selectAll()) {
            def.addElement(base_Art.selectAll().get(base_Art.selectAll().indexOf(item)));
        }

        JLabel text_id = new JLabel("Identifiant : ");      text_id.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        JTextField id = new JTextField("ID de Connexion");      id.setFont(new Font("Book Antiqua", Font.ITALIC, 13));
        id.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        id.addMouseListener(new FormController(id));

        JLabel text_mdp = new JLabel("Mot de Passe : ");      text_mdp.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        JPasswordField mdp = new JPasswordField("motdepass ");      mdp.setFont(new Font("Book Antiqua", Font.ITALIC, 13));
        mdp.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        mdp.addMouseListener(new FormController(mdp));
        mdp.setEchoChar('*');

        JPanel infos = new JPanel();
        JButton connexion = new JButton("Connexion (admin)");
        connexion.setBorderPainted(false);
        connexion.setFocusPainted(false);
        connexion.setContentAreaFilled(true);

        connexion.addActionListener(new ConnexionController(id,mdp,infos));

        this.list1.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        this.list1.addListSelectionListener(new ListArticlesController(this.panel1,this.description,this.location,this.desc_texte,this.list1,this.base_Art,id,mdp,infos));
        this.list1.setLayoutOrientation(JList.VERTICAL_WRAP);
        this.list1.setVisibleRowCount(-1);
        this.list1.setFixedCellHeight(40);

        this.panel1.add(list1);
        this.panel1.add(this.description);
        this.panel1.add(this.formulaire);
        this.panel1.add(this.location);



        this.formulaire.add(text_id);
        this.formulaire.add(id);
        this.formulaire.add(text_mdp);
        this.formulaire.add(mdp);
        this.formulaire.add(infos);
        this.formulaire.add(connexion);





        this.setContentPane(this.panel1);
        this.setName(nom);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(900,600));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    };

    public static void main(String args[]){
        Window win  = new Window("Medical Location App");
    }

}
