/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.InsertClientMenu;
import View.SuspendClientMenu;
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
public class SuspendClientController implements ActionListener {
    private SuspendClientMenu view;
    private ConnectionManager dataBaseConnection;

    public SuspendClientController(ConnectionManager dataBaseConnection) {
        view = new SuspendClientMenu();
        this.dataBaseConnection = dataBaseConnection;
        init();
    }

    private void init() {
        
        view.UpdateButton.addActionListener(this);
        view.ExitButton.addActionListener(this);
        view.setTitle("Menu de Clientes");
        view.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.UpdateButton)){
            System.out.println("Suspending Client");
            
            try {
                dataBaseConnection.clientManager.changeClientState(view.CedulaText.getText(), "Inactivo");
            } catch (SQLException ex) {
                Logger.getLogger(SuspendClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if (e.getSource().equals(view.ExitButton)){
            System.out.println("Exiting");
            view.setVisible(false);    
        }
        
    }
    
    
    
}
