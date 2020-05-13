/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.ClientMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nacho
 */
public class ClientMenuController implements ActionListener {
    public ClientMenu view;
    private MainMenuController previousView;
    private ConnectionManager dataBaseConnection;
    private InsertClientController insertView;
    private SuspendClientController suspendClient;

    public ClientMenuController(ConnectionManager dataBaseConnection,MainMenuController previousView) {
        view = new ClientMenu();
        this.dataBaseConnection = dataBaseConnection;
        this.previousView = previousView; 
        init();
    }

    private void init() {
        view.InsertButton.addActionListener(this);
        view.ModifyButton.addActionListener(this);
        view.SuspendButton.addActionListener(this);
        view.ListButton.addActionListener(this);
        view.ExitButton.addActionListener(this);
        view.setTitle("Menu de Clientes");
        view.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.InsertButton)){
            System.out.println("Accediendo a ventana de Insertar Clientes");
            insertView = new InsertClientController(dataBaseConnection,this);
        }
        
        else if (e.getSource().equals(view.ListButton)){
            System.out.println("Accediendo a ventana de Listar Clientes");
            try {
                dataBaseConnection.clientManager.listClients();
            } catch (SQLException ex) {
                Logger.getLogger(ClientMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if (e.getSource().equals(view.ModifyButton)){
            System.out.println("Accediendo a ventana de Modificar Clientes");
        }
        else if (e.getSource().equals(view.SuspendButton)){
            System.out.println("Accediendo a ventana de Suspender Clientes");
            suspendClient = new SuspendClientController(dataBaseConnection);
        } 
        else if (e.getSource().equals(view.ExitButton)){
            System.out.println("Exiting");
            view.setVisible(false); 
            previousView.view.setVisible(true);
        }
        
    }
    
    
    
}
