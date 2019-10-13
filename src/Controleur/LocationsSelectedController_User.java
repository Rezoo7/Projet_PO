package Controleur;

import Modele.DB.BaseLocation;
import Modele.DB.BaseUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LocationsSelectedController_User implements ActionListener {

    private JComboBox list_months;
    private JComboBox list_years;
    private JPanel centre;
    private JList<String> locations;
    private JLabel infos;
    private BaseLocation baseLocation = new BaseLocation();
    private BaseUser baseUser = new BaseUser();

    private String username;


    public LocationsSelectedController_User(JPanel centre, JComboBox list_months, JComboBox list_years, String username, JLabel infos){
        this.list_months = list_months;
        this.list_years = list_years;
        this.centre = centre;
        this.infos = infos;
        this.username = username;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int id_user = this.baseUser.getIdUserByident(username);

        DefaultListModel<String> def = new DefaultListModel<String>();
        this.locations = new JList<String>(def);
        int month = this.list_months.getSelectedIndex();

        if((month == 0) && (this.list_years.getSelectedIndex() == 0)){  // Cas ou Tous Mois et Toutes Année

            for (String item: baseLocation.selectAllLocations_string_User(id_user)) {
                def.addElement(item);
            }

            this.locations.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 10));
            this.locations.setLayoutOrientation(JList.VERTICAL_WRAP);
            this.locations.setVisibleRowCount(-1);
            this.locations.setFixedCellHeight(30);


            this.centre.removeAll();
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(this.locations);
            this.locations.setLayoutOrientation(JList.VERTICAL);

            this.centre.add(scrollPane,BorderLayout.CENTER);
            this.centre.repaint();
            this.centre.revalidate();


        }
        else if((month > 0) && (this.list_years.getSelectedIndex() == 0) ){ //Cas ou Mois choisi et Toutes Années

            for (String item: baseLocation.selectAllLocationsByMonth_User(id_user,this.list_months.getSelectedIndex())) {
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


            this.centre.removeAll();
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(this.locations);
            this.locations.setLayoutOrientation(JList.VERTICAL);

            this.centre.add(scrollPane,BorderLayout.CENTER);
            this.centre.repaint();
            this.centre.revalidate();

        }
        else if((month == 0) && ((int) this.list_years.getSelectedItem()  >0)){ //Cas ou Tous Mois et Année Choisie

            for (String item: baseLocation.selectAllLocationsByYear_User(id_user,(int) this.list_years.getSelectedItem() )) {
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


            this.centre.removeAll();
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(this.locations);
            this.locations.setLayoutOrientation(JList.VERTICAL);

            this.centre.add(scrollPane,BorderLayout.CENTER);
            this.centre.repaint();
            this.centre.revalidate();
        }
        else{ //Cas ou Mois et Année Choisis

            for (String item: baseLocation.selectLocationsByMonth_Years_User(id_user,month,(int) this.list_years.getSelectedItem() )) {
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


            this.centre.removeAll();
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(this.locations);
            this.locations.setLayoutOrientation(JList.VERTICAL);

            this.centre.add(scrollPane,BorderLayout.CENTER);
            this.centre.repaint();
            this.centre.revalidate();
        }

    }
}
