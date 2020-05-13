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

/**
 *
 * @author nacho
 */
public class LinkPartWithProviderController implements ActionListener {
    private LinkPartsWithProvider view;
    private ConnectionManager dataBaseConnection;
    

    public LinkPartWithProviderController(ConnectionManager dataBaseConnection) {
        view = new LinkPartsWithProvider();
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
            System.out.println("Linking Part with Provider");
            
            String parte = "'"+view.NombreText.getText()+"'";
            String proveedor = "'"+view.ProveedorText.getText()+"'";
            String precioCompra = view.PrecioCompra.getText();
            String ganancia = view.Ganancia.getText();
            System.out.println(dataBaseConnection.partManager.insertProvision(proveedor, parte, precioCompra,ganancia));
        }
        
        else if (e.getSource().equals(view.ExitButton)){
            System.out.println("Exiting");
            view.setVisible(false);    
        }
        
    }
    
    
    
}
