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

        for(int i =0;i<=base.selectAll().size()-1;i++){
            def.addElement(base.selectAll().get(i));
            def.addElement("_ ");
            System.out.println(base.selectAll().get(i));
        }

        this.panel1.add(list1);

        this.setContentPane(this.panel1);
        this.setName(nom);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(1000,700));
        this.pack();
        this.setVisible(true);
    };

    public static void main(String args[]){
        Window win  = new Window("Medical Location App");
    }

}
