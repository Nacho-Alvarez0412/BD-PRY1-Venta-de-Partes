/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.LinkPartWithVehicle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nacho
 */
public class LinkPartWithVehicleController implements ActionListener {
    private LinkPartWithVehicle view;
    private ConnectionManager dataBaseConnection;
    private PartMenuController previousView;

    public LinkPartWithVehicleController(ConnectionManager dataBaseConnection,PartMenuController previousView) {
        view = new LinkPartWithVehicle();
        this.dataBaseConnection = dataBaseConnection;
        this.previousView = previousView;
        init();
    }

    private void init() {
        
        view.InsertButton.addActionListener(this);
        view.ExitButton.addActionListener(this);
        view.BuscarAñoButton.addActionListener(this);
        view.setTitle("Menu de Partes");
        view.setVisible(true);
        fillPartes();
        fillModelos();
        
    }
    
    private void fillPartes() {
        view.ParteComboBox.removeAllItems();
        ArrayList<String> partes = dataBaseConnection.getColumnFromTable("Parte", "NombreParte");
        for (int i = 0 ; i < partes.size() ; i++){
            view.ParteComboBox.addItem(partes.get(i));
        }
    }
    
    private void fillModelos() {
        view.ModeloComboBox.removeAllItems();
        ArrayList<String> modelos = dataBaseConnection.getColumnFromTable("Automóvil", "Modelo");
        for (int i = 0 ; i < modelos.size() ; i++){
            view.ModeloComboBox.addItem(modelos.get(i));
        }
    }
    
    private void fillAños(String modelo){
        view.AñoComboBox.removeAllItems();
        ArrayList<String> modelos = dataBaseConnection.getRows1Variable("Automóvil", "Modelo", "'"+modelo+"'");
        System.out.println(modelos);
        for (int i = 2 ; i < modelos.size() ; i+=5){
            view.AñoComboBox.addItem(modelos.get(i));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.InsertButton)){
            System.out.println("Linking part with vehicle");
            String parte = "'"+view.ParteComboBox.getSelectedItem()+"'";
            String modelo = "'"+view.ModeloComboBox.getSelectedItem()+"'";
            String year = (String) view.AñoComboBox.getSelectedItem();
            if(dataBaseConnection.partManager.linkPartWithVehicle(parte, modelo, year)){
                JOptionPane.showMessageDialog(view, "Parte asociada con éxito");
                return;
            }
            JOptionPane.showMessageDialog(view, "Hubo un error al intentar asociar las partes verifique valores e intente nuevamente");
        }
        
        else if(e.getSource().equals(view.BuscarAñoButton)){
            String modelo = (String)view.ModeloComboBox.getSelectedItem();
            fillAños(modelo);
        }
        
        else if (e.getSource().equals(view.ExitButton)){
            System.out.println("Exiting");
            view.setVisible(false); 
            previousView.view.setVisible(true);
        }
        
    }
    
    
    
}
