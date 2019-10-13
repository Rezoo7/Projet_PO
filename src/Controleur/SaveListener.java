package Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class SaveListener implements ActionListener {

    private JList<String> list_locations;
    private int year;
    private int month;
    private JLabel infos;

    public SaveListener(JList<String> list_locations,int year,int month,JLabel infos){
        this.list_locations = list_locations;
        this.month = month;
        this.year = year;
        this.infos = infos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

         try {
             String fileName="";
             Random id = new Random();

             //Génération du nom de fichier
             if(this.year == 0 && this.month == 0){
                fileName  =  "Saves/AllYears_AllMonths_id"+ id.nextInt(10000) + ".loc";
             }
             else if(this.year == 0 && this.month >0){
                 fileName = "Saves/AllYears_" + this.month +"_id" +id.nextInt(10000) + ".loc";
             }
             else if(this.year > 0 && this.month ==0){
                 fileName = "Saves/"+this.year + "_AllMonths_id"+ id.nextInt(10000)+".loc";

             }

             BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

             for (int i = 0; i<= this.list_locations.getModel().getSize()-1; i++){

                 String item = this.list_locations.getModel().getElementAt(i);
                 writer.write(item +"\n");
                 System.out.println(item);
             }
             writer.close();

             this.infos.setText("Fichier Sauvegardé");
             this.infos.setFont(new Font("Book Antiqua", Font.LAYOUT_LEFT_TO_RIGHT, 14));

         }catch (IOException j){
             j.printStackTrace();
         }
    }
}
