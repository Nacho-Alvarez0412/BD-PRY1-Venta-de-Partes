/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.PartMenu;
import View.UpdatePricesMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nacho
 */
public class UpdatePricesController implements ActionListener {
    private UpdatePricesMenu view;
    private ConnectionManager dataBaseConnection;
    private PartMenuController previousView;
    

    public UpdatePricesController(ConnectionManager dataBaseConnection,PartMenuController previousView) {
        view = new UpdatePricesMenu();
        this.dataBaseConnection = dataBaseConnection;
        this.previousView = previousView;
        init();
    }

    private void init() {
        
        view.LinkButton.addActionListener(this);
        view.ExitButton.addActionListener(this);
        view.BuscarPartesButton.addActionListener(this);
        view.setTitle("Menu de Partes");
        view.setVisible(true);
        fillProviders();
        
    }
    
    private void fillProviders() {
        view.ProveedorComboBox.removeAllItems();
        ArrayList<String> proveedores = dataBaseConnection.getColumnFromTable("Proveedor", "Nombre");
        for (int i = 0 ; i < proveedores.size() ; i++){
            view.ProveedorComboBox.addItem(proveedores.get(i));
        }
    }
    
    private void fillPartes(String proveedor){
        String providerID = dataBaseConnection.orderManager.getProviderID(proveedor);
        ArrayList<String> partes = dataBaseConnection.orderManager.listPartsByProvider(providerID);
        if(partes.isEmpty()){
            JOptionPane.showMessageDialog(view, "El proveedor indicado no provee partes actualmente");
            return;
        }
        for (int i = 0 ; i < partes.size() ; i++){
            view.ParteComboBox.addItem(dataBaseConnection.getRows1Variable("Parte", "ParteID", partes.get(i)).get(1));
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.LinkButton)){
            String parte = "'"+view.ParteComboBox.getSelectedItem()+"'";
            String proveedor = "'"+view.ProveedorComboBox.getSelectedItem()+"'";
            String precioCompra = String.valueOf(view.PrecioSpinner.getValue());
            String ganancia = String.valueOf(view.GananciaSpinner.getValue());
            if(view.ParteComboBox.getSelectedItem().equals(" ")){
                JOptionPane.showMessageDialog(view, "Debe indicar una parte válida");
                return;
            }
            if(dataBaseConnection.partManager.updatePrices(proveedor, parte, precioCompra,ganancia)){
                JOptionPane.showMessageDialog(view, "Precios actualizados con éxito");
                return;
            }
            JOptionPane.showMessageDialog(view, "Sucedio un error al ingresas el cambio, verifique los valores e intente de nuevo");
        }
        
        else if(e.getSource().equals(view.BuscarPartesButton)){
            fillPartes("'"+view.ProveedorComboBox.getSelectedItem()+"'");
        }
        
        else if (e.getSource().equals(view.ExitButton)){
            view.setVisible(false);   
            previousView.view.setVisible(true);
        }
        
    }
    
    
    
}
