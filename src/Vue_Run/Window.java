package Vue_Run;

import Controleur.ListArticlesController;
import Modele.DB.*;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private JList<String> list1;
    private JPanel panel1;
    private JPanel description;
    private Base b;

    private JTextArea desc_texte;

    public Window(String nom){

        this.panel1 = new JPanel();
        this.description = new JPanel();
        this.b = new Base();

        this.panel1.setLayout(new GridLayout(1,1) );
        this.description.setLayout(new GridLayout(2,1));



        DefaultListModel<String> def = new DefaultListModel<String>();
    	this.list1 = new JList<String>(def);

        for (String item: b.selectAll()) {
            def.addElement(b.selectAll().get(b.selectAll().indexOf(item)));
        }


        this.list1.addListSelectionListener(new ListArticlesController(this.description,this.panel1,this.desc_texte,this.list1,b));
        this.list1.setLayoutOrientation(JList.VERTICAL_WRAP);
        this.list1.setVisibleRowCount(-1);


        this.panel1.add(list1);
        this.panel1.add(this.description);

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
