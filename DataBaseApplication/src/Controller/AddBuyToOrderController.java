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

/**
 *
 * @author nacho
 */
public class AddBuyToOrderController implements ActionListener {
    private AddBuyToOrder view;
    private ConnectionManager dataBaseConnection;
    

    public AddBuyToOrderController(ConnectionManager dataBaseConnection) {
        view = new AddBuyToOrder();
        this.dataBaseConnection = dataBaseConnection;
        init();
    }

    private void init() {
        
        view.AddButton.addActionListener(this);
        view.ExitButton.addActionListener(this);
        view.setTitle("Menu de Ordenes");
        view.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.AddButton)){
            System.out.println("Linking Part with Provider");
            
            String cedula = view.CedulaText.getText();
            String fecha = "'"+view.FechaText.getText()+"'";
            String cantidad = view.Cantidad.getText();
            String parte = "'"+view.PartText.getText()+"'";
            String proveedor = "'"+view.Proveedor.getText()+"'";
            System.out.println(dataBaseConnection.orderManager.linkDetailToOrder(cedula, fecha, cantidad,parte,proveedor));
        }
        
        else if (e.getSource().equals(view.ExitButton)){
            System.out.println("Exiting");
            view.setVisible(false);    
        }
        
    }
    
    
    
}
