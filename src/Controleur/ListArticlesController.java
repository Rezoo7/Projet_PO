package Controleur;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Modele.DB.Base;

import javax.swing.*;

public class ListArticlesController implements ListSelectionListener {

    private JList<String> articles;
    private JPanel panel_desc;
    private Base b ;

    public ListArticlesController(JPanel pan, JList<String> liste){
        this.panel_desc = pan;
        this.articles = liste;
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {



        System.out.println(this.articles.getSelectedIndex());

        this.panel_desc.setVisible(true);

    }
}
