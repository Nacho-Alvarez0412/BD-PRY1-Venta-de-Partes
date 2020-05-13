/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.DeletePartMenu;
import View.InsertClientMenu;
import View.InsertOrderMenu;
import View.InsertPartMenu;
import View.PartsByProviderMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nacho
 */
public class InsertOrderController implements ActionListener {
    private InsertOrderMenu view;
    private ConnectionManager dataBaseConnection;

    public InsertOrderController(ConnectionManager dataBaseConnection) {
        view = new InsertOrderMenu();
        this.dataBaseConnection = dataBaseConnection;
        init();
    }

    private void init() {
        
        view.InsertButton.addActionListener(this);
        view.ExitButton.addActionListener(this);
        view.setTitle("Menu de Ordenes");
        view.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.InsertButton)){
            System.out.println("Listing Parts");
            String cedula = view.CedulaText.getText();
            String fecha = "'"+view.Fecha.getText()+"'";
            
            System.out.println(dataBaseConnection.orderManager.insertOrder(cedula,fecha));
        }
        
        else if (e.getSource().equals(view.ExitButton)){
            System.out.println("Exiting");
            view.setVisible(false);    
        }
        
    }
    
    
    
}
