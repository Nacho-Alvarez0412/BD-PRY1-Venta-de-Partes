/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.LinkPartWithVehicle;
import View.ListPartsMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author nacho
 */
public class ListPartController implements ActionListener {
    private ListPartsMenu view;
    private ConnectionManager dataBaseConnection;
    private PartMenuController previousView;

    public ListPartController(ConnectionManager dataBaseConnection,PartMenuController previousView) {
        view = new ListPartsMenu();
        this.dataBaseConnection = dataBaseConnection;
        this.previousView = previousView;
        init();
    }

    private void init() {
        
        view.InsertButton.addActionListener(this);
        view.ExitButton.addActionListener(this);
        view.BuscarAñoButton.addActionListener(this);
        view.setTitle("Menu de Partes");
        fillModelos();
        view.setVisible(true);
        
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
            view.PartesTextArea.setText("");
            String modelo = "'"+view.ModeloComboBox.getSelectedItem()+"'";
            String year = String.valueOf(view.AñoComboBox.getSelectedItem());
            view.PartesTextArea.append(dataBaseConnection.partManager.listPartsByVehicle(modelo, year));
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
