/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.AddBuyToOrder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author nacho
 */
public class AddBuyToOrderController implements ActionListener {
    private AddBuyToOrder view;
    private ConnectionManager dataBaseConnection;
    private OrderMenuController previousView;
    

    public AddBuyToOrderController(ConnectionManager dataBaseConnection,OrderMenuController previousView) {
        view = new AddBuyToOrder();
        this.dataBaseConnection = dataBaseConnection;
        this.previousView = previousView;
        init();
    }

    private void init() {
        
        view.AddButton.addActionListener(this);
        view.ExitButton.addActionListener(this);
        view.buscarClienteButton.addActionListener(this);
        view.buscarProveedor.addActionListener(this);
        view.setTitle("Menu de Ordenes");
        view.setVisible(true);
        fillPartes();
        
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.AddButton)){
            
            
            String cedula = view.CedulaText.getText();
            String ordenID = (String) view.OrdenComboBox.getSelectedItem();
            String cantidad = String.valueOf(view.cantidadSpinner.getValue());
            String parte = "'"+view.ParteComboBox.getSelectedItem()+"'";
            String proveedor = "'"+view.ProveedorComboBox.getSelectedItem()+"'";
            if(dataBaseConnection.orderManager.linkDetailToOrder(cedula, ordenID, cantidad,parte,proveedor)){
                JOptionPane.showMessageDialog(view, "Nueva parte agregada con éxito");
                return;
            }
            JOptionPane.showMessageDialog(view, "Error en la información suministrada favor igresar nuevamente");
                
        }
        
        else if (e.getSource().equals(view.buscarClienteButton)){
            String cedula = view.CedulaText.getText();
            try{
                Integer.valueOf(cedula);
            } catch(java.lang.NumberFormatException a){
                JOptionPane.showMessageDialog(view, cedula + ", no es un formato valido de cédula");
                return;
            }
            
            String clientID = dataBaseConnection.clientManager.getClientID(cedula);
            ArrayList<ArrayList<String>> rows = dataBaseConnection.getRows1VariableArray("Orden", "ClienteID",clientID);
            fillOrders(rows);
        }
        
        else if (e.getSource().equals(view.buscarProveedor)){
            String parte = "'"+view.ParteComboBox.getSelectedItem()+"'";
            fillProviders(dataBaseConnection.orderManager.listProvidersByPart(parte));
            
        }
        
        else if (e.getSource().equals(view.ExitButton)){
            System.out.println("Exiting");
            view.setVisible(false);  
            previousView.view.setVisible(true);
        }
        
    }

    private void fillPartes() {
        ArrayList<String> partes = dataBaseConnection.getColumnFromTable("Parte", "NombreParte");
        
        for (int i = 0 ; i < partes.size() ; i++){
            view.ParteComboBox.addItem(partes.get(i));
        }
    }
    
    private void fillOrders(ArrayList<ArrayList<String>> info){
        view.OrdenComboBox.removeAllItems();
        for (int i = 0 ; i < info.size() ; i++){
            view.OrdenComboBox.addItem(info.get(i).get(0));
        }
    }

    private void fillProviders(ArrayList<String> listProvidersByPart) {
        view.ProveedorComboBox.removeAllItems();
        for (int i = 0 ; i < listProvidersByPart.size() ; i++){
            view.ProveedorComboBox.addItem(listProvidersByPart.get(i));
        }
    }
        
    
    
    
}
