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
import java.util.ArrayList;
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
            insertView = new InsertClientController(dataBaseConnection,this);
            view.setVisible(false); 
        }
        
        else if (e.getSource().equals(view.ListButton)){
            ArrayList<ArrayList<String>> personas = dataBaseConnection.getTable("Persona");
            ArrayList<ArrayList<String>> organizaciones = dataBaseConnection.getTable("Organización");
            String personasString = dataBaseConnection.clientManager.PersonToString(personas);
            String organizacionesString = dataBaseConnection.clientManager.OrganizationToString(organizaciones);
            ListClientsController listClient = new ListClientsController(view, personasString, organizacionesString);
            view.setVisible(false); 
        }
        
        else if (e.getSource().equals(view.ModifyButton)){
            ModifyClientController moddifyClient = new ModifyClientController(dataBaseConnection, this);
            this.view.setVisible(false);
        }
        else if (e.getSource().equals(view.SuspendButton)){
            suspendClient = new SuspendClientController(dataBaseConnection,this);
            view.setVisible(false); 
        } 
        else if (e.getSource().equals(view.ExitButton)){
            view.setVisible(false); 
            previousView.view.setVisible(true);
        }
        
    }
    
    
    
}
