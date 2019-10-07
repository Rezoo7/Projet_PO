package Controleur;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Modele.DB.Base;

import javax.swing.*;
import java.awt.*;

public class ListArticlesController implements ListSelectionListener {

    private JList<String> articles;
    private JTextArea texte_desc;
    private Base b ;
    private JPanel panel;
    private JPanel prin;

    public ListArticlesController(JPanel pan,JPanel principale,JTextArea desc, JList<String> liste, Base base){
        this.texte_desc = desc;
        this.articles = liste;
        this.b = base;
        this.panel = pan;
        this.prin = principale;
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        this.texte_desc = new JTextArea();
        String texte ="";

        if (!e.getValueIsAdjusting()) {//This line prevents double events
            String[] options = this.b.selectAllOptionsArticle(this.articles.getSelectedIndex());

            for (int i = 0; i <= options.length-1; i++) {
                if (!options[i].contains(" 0.0") && !options[i].contains(" null")) {
                    texte = texte + options[i] + "\n";
                }
            }


            this.texte_desc.setText(texte);
            this.texte_desc.setEnabled(false);
            this.texte_desc.setDisabledTextColor(Color.BLACK);

            this.panel.removeAll();
            this.prin.repaint();
            this.panel.add(this.texte_desc);
            this.texte_desc.setVisible(true);

        }
    }
}
