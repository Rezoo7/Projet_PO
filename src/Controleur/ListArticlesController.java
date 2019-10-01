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

    public ListArticlesController(JPanel pan, JList<String> liste, Base base){
        this.panel_desc = pan;
        this.articles = liste;
        this.b = base;
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {

        System.out.println(this.articles.getSelectedIndex()); //OK
        System.out.println("test");
        String[] options = this.b.selectAllOptionsArticle(this.articles.getSelectedIndex());

        for (int i =0; i<= options.length-1;i++){
            if(!options[i].contains(" 0.0") && !options[i].contains(" null")){
                System.out.println(options[i]);
            }
        }

        this.panel_desc.setVisible(true);

    }
}
