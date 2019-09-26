package Vue_Run;

import Modele.DB.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Window {

    private JPanel panel1;
    private JList<String> list1;
    private JTextField textField1;
    private JButton button1;
    private JPanel panel2;


    public Window(){
    	Base base = new Base();
        DefaultListModel<String> def = new DefaultListModel<String>();
    	this.list1 = new JList<String>(def);

        for(int i =0;i<=base.selectAll().size()-1;i++){
            def.addElement(base.selectAll().get(i));
            System.out.println(base.selectAll().get(i));
        }

        panel1.add(list1);
    };

    public static void main(String args[]){
        JFrame frame = new JFrame("Medical Location App");
        frame.setContentPane(new Window().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
