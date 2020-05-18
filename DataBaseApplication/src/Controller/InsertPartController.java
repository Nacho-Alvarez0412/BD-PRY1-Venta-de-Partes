/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.InsertPartMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nacho
 */
public class InsertPartController implements ActionListener {
    private InsertPartMenu view;
    private ConnectionManager dataBaseConnection;
    private PartMenuController previousView;

    public InsertPartController(ConnectionManager dataBaseConnection,PartMenuController previousView) {
        view = new InsertPartMenu();
        this.dataBaseConnection = dataBaseConnection;
        this.previousView = previousView;
        init();
    }

    private void init() {
        
        view.InsertButton.addActionListener(this);
        view.ExitButton.addActionListener(this);
        view.setTitle("Menu de Partes");
        fillMarca();
        fillFabricante();
        view.setVisible(true);
        
    }
    
    private void fillMarca() {
        ArrayList<String> partes = dataBaseConnection.getColumnFromTable("MarcaParte", "Marca");
        
        for (int i = 0 ; i < partes.size() ; i++){
            view.MarcaComboBox.addItem(partes.get(i));
        }
    }
    
    private void fillFabricante() {
        ArrayList<String> partes = dataBaseConnection.getColumnFromTable("FabricantePartes", "NombreFabricante");
        
        for (int i = 0 ; i < partes.size() ; i++){
            view.FabricanteComboBox.addItem(partes.get(i));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.InsertButton)){
            System.out.println("Inserting Part");
            String parte = "'"+view.NombreText.getText()+"'";
            String fabricante = "'"+view.FabricanteComboBox.getSelectedItem()+"'";
            String marca = "'"+view.MarcaComboBox.getSelectedItem()+"'";
            if(dataBaseConnection.partManager.insertPart(parte, fabricante, marca))
                JOptionPane.showMessageDialog(view, "Parte ingresada con éxito");
            else
                JOptionPane.showMessageDialog(view, "Parte ya fue registrada en la base de datos");
                
        }
        
        else if (e.getSource().equals(view.ExitButton)){
            view.setVisible(false);  
            previousView.view.setVisible(true);
        }
        
    }
    
    
    
}
