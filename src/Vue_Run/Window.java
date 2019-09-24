package Vue_Run;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Window {
    private JPanel panel1;
    private JList Liste_Articles;
    private JTextArea Details;
    private JButton Louer;

    public Window(){
    	

    };

    public static void main(String args[]){
        JFrame frame = new JFrame("Medical Location App");
        frame.setContentPane(new Window().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
