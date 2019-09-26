package Vue_Run;

import Modele.DB.*;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private JList<String> list1;
    private JPanel panel1;
    private JPanel description;

    public Window(String nom){

        this.panel1 = new JPanel();
        this.description = new JPanel();

        this.panel1.setLayout(new GridLayout(1,1) );
        this.description.setLayout(new GridLayout(2,1));


        Base base = new Base();
        DefaultListModel<String> def = new DefaultListModel<String>();
    	this.list1 = new JList<String>(def);

        for (String item: base.selectAll()) {
            def.addElement(base.selectAll().get(base.selectAll().indexOf(item)));
        }

        this.list1.setLayoutOrientation(JList.VERTICAL_WRAP);
        this.list1.setVisibleRowCount(-1);


        this.panel1.add(list1);
        this.panel1.add(this.description);

        this.setContentPane(this.panel1);
        this.setName(nom);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(1000,900));
        this.pack();
        this.setVisible(true);
    };

    public static void main(String args[]){
        Window win  = new Window("Medical Location App");
    }

}
