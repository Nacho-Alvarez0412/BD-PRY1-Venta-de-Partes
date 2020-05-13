/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.UpdatePricesMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author nacho
 */
public class UpdatePricesController implements ActionListener {
    private UpdatePricesMenu view;
    private ConnectionManager dataBaseConnection;
    

    public UpdatePricesController(ConnectionManager dataBaseConnection) {
        view = new UpdatePricesMenu();
        this.dataBaseConnection = dataBaseConnection;
        init();
    }

    private void init() {
        
        view.LinkButton.addActionListener(this);
        view.ExitButton.addActionListener(this);
        view.setTitle("Menu de Partes");
        view.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.LinkButton)){
            System.out.println("Updating Prices");
            
            String parte = "'"+view.NombreText.getText()+"'";
            String proveedor = "'"+view.ProveedorText.getText()+"'";
            String precioCompra = view.PrecioCompra.getText();
            String ganancia = view.Ganancia.getText();
            System.out.println(dataBaseConnection.partManager.updatePrices(proveedor, parte, precioCompra,ganancia));
        }
        
        else if (e.getSource().equals(view.ExitButton)){
            System.out.println("Exiting");
            view.setVisible(false);    
        }
        
    }
    
    
    
}
