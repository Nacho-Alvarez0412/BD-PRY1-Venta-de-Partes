/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.LinkPartsWithProvider;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nacho
 */
public class LinkPartWithProviderController implements ActionListener {
    private LinkPartsWithProvider view;
    private ConnectionManager dataBaseConnection;
    private PartMenuController previousView;
    

    public LinkPartWithProviderController(ConnectionManager dataBaseConnection,PartMenuController previousView) {
        view = new LinkPartsWithProvider();
        this.dataBaseConnection = dataBaseConnection;
        this.previousView = previousView;
        init();
    }

    private void init() {
        
        view.LinkButton.addActionListener(this);
        view.ExitButton.addActionListener(this);
        view.setTitle("Menu de Partes");
        fillPartes();
        fillProveedores();
        view.setVisible(true);
        
        
    }
    
    private void fillPartes() {
        view.ParteComboBox.removeAllItems();
        ArrayList<String> partes = dataBaseConnection.getColumnFromTable("Parte", "NombreParte");
        for (int i = 0 ; i < partes.size() ; i++){
            view.ParteComboBox.addItem(partes.get(i));
        }
    }
    
    private void fillProveedores() {
        view.ProveedorComboBox.removeAllItems();
        ArrayList<String> partes = dataBaseConnection.getColumnFromTable("Proveedor", "Nombre");
        for (int i = 0 ; i < partes.size() ; i++){
            view.ProveedorComboBox.addItem(partes.get(i));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.LinkButton)){
            System.out.println("Linking Part with Provider");
            
            String parte = "'"+view.ParteComboBox.getSelectedItem()+"'";
            String proveedor = "'"+view.ProveedorComboBox.getSelectedItem()+"'";
            String precioCompra = String.valueOf(view.PrecioSpinner.getValue());
            String ganancia = String.valueOf(view.GananciaSpinner.getValue());
            if(dataBaseConnection.partManager.insertProvision(proveedor, parte, precioCompra,ganancia)){
                JOptionPane.showMessageDialog(view, "Asociación creada con éxito");
                return;
            }
            JOptionPane.showMessageDialog(view, "Revise los valores e intente de nuevo");
                return;
        }
        
        else if (e.getSource().equals(view.ExitButton)){
            System.out.println("Exiting");
            view.setVisible(false);
            previousView.view.setVisible(true);
        }
        
    }
    
    
    
}
