package Controleur;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FormController implements MouseListener {

    private JTextField champ;

    public FormController(JTextField ch){
        this.champ = ch;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.champ.setText("");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
