package Controleur;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

public class ListArticlesController implements ListSelectionListener {

    private List<String> articles;
    private JPanel panel_desc;

    public ListArticlesController(JPanel pan,List<String> liste){
        this.panel_desc = pan;
        this.articles = liste;
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {

        this.panel_desc.setVisible(true);



    }
}
