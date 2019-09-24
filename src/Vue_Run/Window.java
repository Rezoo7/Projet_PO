package Vue_Run;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Window {
    private JButton Bouton;
    private JPanel panel1;

    public Window(){
    	
        Bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("tst");

            }
        });
    };

    public static void main(String args[]){
        JFrame frame = new JFrame("Medical Location App");
        frame.setContentPane(new Window().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
