package Controleur;

import Modele.DB.BaseLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LocationsMonthsListener implements ActionListener {

   private JComboBox list_months;
   private JComboBox list_years;
   private JPanel centre;
   private JList<String> locations;
   private BaseLocation baseLocation = new BaseLocation();


   public LocationsMonthsListener(JPanel centre,JComboBox list_months,JComboBox list_years){
        this.list_months = list_months;
        this.list_years = list_years;
        this.centre = centre;
   }


    @Override
    public void actionPerformed(ActionEvent e) {

        int month = this.list_months.getSelectedIndex();

        if((month == 0) && (this.list_years.getSelectedIndex() == 0)){
            DefaultListModel<String> def = new DefaultListModel<String>();
            this.locations = new JList<String>(def);

            for (String item: baseLocation.selectAllLocations_string()) {
                System.out.println(item);
                def.addElement(item);
            }

            this.locations.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 10));
            this.locations.setLayoutOrientation(JList.VERTICAL_WRAP);
            this.locations.setVisibleRowCount(-1);
            this.locations.setFixedCellHeight(30);

            this.centre.removeAll();
            this.centre.add(this.locations,BorderLayout.CENTER);
            this.centre.repaint();
            this.centre.revalidate();


        }
        else if((month > 0) && (this.list_years.getSelectedIndex() == 0) ){
            DefaultListModel<String> def = new DefaultListModel<String>();
            this.locations = new JList<String>(def);
            System.out.println("mois choisi / All années");

            for (String item: baseLocation.selectAllLocationsByMonth(this.list_months.getSelectedIndex())) {
                System.out.println(item);
                def.addElement(item);
            }

            this.locations.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 10));
            this.locations.setLayoutOrientation(JList.VERTICAL_WRAP);
            this.locations.setVisibleRowCount(-1);
            this.locations.setFixedCellHeight(30);

            this.centre.removeAll();
            this.centre.add(this.locations,BorderLayout.CENTER);
            this.centre.repaint();
            this.centre.revalidate();

        }
        else if((month == 0) && ((int) this.list_years.getSelectedItem()  >0)){
            System.out.println(this.list_years.getSelectedIndex());
            DefaultListModel<String> def = new DefaultListModel<String>();
            this.locations = new JList<String>(def);
            System.out.println("All mois / année choisi ");

            for (String item: baseLocation.selectAllLocationsByYear((int) this.list_years.getSelectedItem() )) {
                System.out.println(item);
                def.addElement(item);
            }

            this.locations.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 10));
            this.locations.setLayoutOrientation(JList.VERTICAL_WRAP);
            this.locations.setVisibleRowCount(-1);
            this.locations.setFixedCellHeight(30);

            this.centre.removeAll();
            this.centre.add(this.locations,BorderLayout.CENTER);
            this.centre.repaint();
            this.centre.revalidate();
        }
        else{
            DefaultListModel<String> def = new DefaultListModel<String>();
            this.locations = new JList<String>(def);
            System.out.println("mois choisi / année choisie");

            for (String item: baseLocation.selectLocationsByMonth_Years(month,(int) this.list_years.getSelectedItem() )) {
                System.out.println(item);
                def.addElement(item);
            }

            this.locations.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 10));
            this.locations.setLayoutOrientation(JList.VERTICAL_WRAP);
            this.locations.setVisibleRowCount(-1);
            this.locations.setFixedCellHeight(30);

            this.centre.removeAll();
            this.centre.add(this.locations,BorderLayout.CENTER);
            this.centre.repaint();
            this.centre.revalidate();

        }




    }
}
