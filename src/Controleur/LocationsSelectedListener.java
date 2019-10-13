package Controleur;

import Modele.DB.BaseLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LocationsSelectedListener implements ActionListener {

   private JComboBox list_months;
   private JComboBox list_years;
   private JPanel centre;
   private JList<String> locations;
   private JButton save;
   private JLabel infos;
   private BaseLocation baseLocation = new BaseLocation();


   public LocationsSelectedListener(JPanel centre, JComboBox list_months, JComboBox list_years, JButton save,JLabel infos){
        this.list_months = list_months;
        this.list_years = list_years;
        this.centre = centre;
        this.save = save;
        this.infos = infos;
   }


    @Override
    public void actionPerformed(ActionEvent e) {

        DefaultListModel<String> def = new DefaultListModel<String>();
        this.locations = new JList<String>(def);
        int month = this.list_months.getSelectedIndex();

        if((month == 0) && (this.list_years.getSelectedIndex() == 0)){  // Cas ou Tous Mois et Toutes Année

            for (String item: baseLocation.selectAllLocations_string()) {
                def.addElement(item);
            }

            this.locations.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 10));
            this.locations.setLayoutOrientation(JList.VERTICAL_WRAP);
            this.locations.setVisibleRowCount(-1);
            this.locations.setFixedCellHeight(30);

            this.save.addActionListener(new SaveListener(this.locations,list_years.getSelectedIndex(),this.list_months.getSelectedIndex(),this.infos));

            this.centre.removeAll();
            this.centre.add(this.locations,BorderLayout.CENTER);
            this.centre.repaint();
            this.centre.revalidate();


        }
        else if((month > 0) && (this.list_years.getSelectedIndex() == 0) ){ //Cas ou Mois choisi et Toutes Années

            for (String item: baseLocation.selectAllLocationsByMonth(this.list_months.getSelectedIndex())) {
                def.addElement(item);
            }

            this.locations.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 10));

            if(def.isEmpty()){
                String empt = "Pas de Données sur le Mois Renseigné";
                def.addElement(empt);
                this.locations.setFont(new Font("Book Antiqua", Font.CENTER_BASELINE, 15));
            }

            this.locations.setLayoutOrientation(JList.VERTICAL_WRAP);
            this.locations.setVisibleRowCount(-1);
            this.locations.setFixedCellHeight(30);

            this.save.addActionListener(new SaveListener(this.locations,list_years.getSelectedIndex(),this.list_months.getSelectedIndex(),this.infos));

            this.centre.removeAll();
            this.centre.add(this.locations,BorderLayout.CENTER);
            this.centre.repaint();
            this.centre.revalidate();

        }
        else if((month == 0) && ((int) this.list_years.getSelectedItem()  >0)){ //Cas ou Tous Mois et Année Choisie

            for (String item: baseLocation.selectAllLocationsByYear((int) this.list_years.getSelectedItem() )) {
                def.addElement(item);
            }

            this.locations.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 10));

            if(def.isEmpty()){
                String empt = "Pas de Données sur l'Année Renseignée";
                def.addElement(empt);
                this.locations.setFont(new Font("Book Antiqua", Font.CENTER_BASELINE, 15));
            }

            this.locations.setLayoutOrientation(JList.VERTICAL_WRAP);
            this.locations.setVisibleRowCount(-1);
            this.locations.setFixedCellHeight(30);

            this.save.addActionListener(new SaveListener(this.locations,(int)list_years.getSelectedItem(),this.list_months.getSelectedIndex(),this.infos));

            this.centre.removeAll();
            this.centre.add(this.locations,BorderLayout.CENTER);
            this.centre.repaint();
            this.centre.revalidate();
        }
        else{ //Cas ou Mois et Année Choisis

            for (String item: baseLocation.selectLocationsByMonth_Years(month,(int) this.list_years.getSelectedItem() )) {
                def.addElement(item);
            }

            this.locations.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 10));

            if(def.isEmpty()){
                String empt = "Pas de Données sur le Mois Renseigné";
                def.addElement(empt);
                this.locations.setFont(new Font("Book Antiqua", Font.CENTER_BASELINE, 15));

            }

            this.locations.setLayoutOrientation(JList.VERTICAL_WRAP);
            this.locations.setVisibleRowCount(-1);
            this.locations.setFixedCellHeight(30);

            this.save.addActionListener(new SaveListener(this.locations,(int)list_years.getSelectedItem(),this.list_months.getSelectedIndex(),this.infos));

            this.centre.removeAll();
            this.centre.add(this.locations,BorderLayout.CENTER);
            this.centre.repaint();
            this.centre.revalidate();

        }

    }
}
