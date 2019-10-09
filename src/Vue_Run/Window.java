package Vue_Run;

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
    private Base b;

    private JTextArea desc_texte;


    public Window(String nom){

        this.panel1 = new JPanel();
        this.description = new JPanel();
        this.location = new JPanel();
        this.formulaire = new JPanel();
        this.b = new Base();

        this.panel1.setLayout(new GridLayout(2,1) );
        this.description.setLayout(new GridLayout(1,1));
        this.location.setLayout(new BorderLayout());
        this.formulaire.setLayout(new GridLayout(3,2));



        DefaultListModel<String> def = new DefaultListModel<String>();
    	this.list1 = new JList<String>(def);

        for (String item: b.selectAll()) {
            def.addElement(b.selectAll().get(b.selectAll().indexOf(item)));
        }

        this.list1.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        this.list1.addListSelectionListener(new ListArticlesController(this.panel1,this.description,this.location,this.desc_texte,this.list1,b));
        this.list1.setLayoutOrientation(JList.VERTICAL_WRAP);
        this.list1.setVisibleRowCount(-1);
        this.list1.setFixedCellHeight(40);

        this.panel1.add(list1);
        this.panel1.add(this.description);
        this.panel1.add(this.formulaire);
        this.panel1.add(this.location);

        JLabel text_mail = new JLabel("Email : ");      text_mail.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        JTextField mail = new JTextField("Email de Connexion");      mail.setFont(new Font("Book Antiqua", Font.ITALIC, 13));
        mail.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        mail.addMouseListener(new FormController(mail));

        JLabel text_mdp = new JLabel("Mot de Passe : ");      text_mdp.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 12));
        JTextField mdp = new JTextField("mot de passe ");      mdp.setFont(new Font("Book Antiqua", Font.ITALIC, 13));
        mdp.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        mdp.addMouseListener(new FormController(mdp));

        JPanel infos = new JPanel();

        this.formulaire.add(text_mail);
        this.formulaire.add(mail);
        this.formulaire.add(text_mdp);
        this.formulaire.add(mdp);
        this.formulaire.add(infos);





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
