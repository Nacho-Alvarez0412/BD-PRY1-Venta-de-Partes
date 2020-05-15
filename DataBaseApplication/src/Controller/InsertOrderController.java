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
import javax.swing.JOptionPane;

/**
 *
 * @author nacho
 */
public class InsertOrderController implements ActionListener {
    private InsertOrderMenu view;
    private ConnectionManager dataBaseConnection;
    private OrderMenuController previousView;

    public InsertOrderController(ConnectionManager dataBaseConnection,OrderMenuController previousView) {
        view = new InsertOrderMenu();
        this.dataBaseConnection = dataBaseConnection;
        this.previousView = previousView;
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
            
            if(dataBaseConnection.orderManager.insertOrder(cedula,fecha)){
                JOptionPane.showMessageDialog(view, "Orden agregada con éxito");
                return;
            }
            JOptionPane.showMessageDialog(view, "Verifique los valores ingresado e intente nuevamente");
        }
        
        else if (e.getSource().equals(view.ExitButton)){
            System.out.println("Exiting");
            view.setVisible(false); 
            previousView.view.setVisible(true);
            
        }
        
    }
    
}
