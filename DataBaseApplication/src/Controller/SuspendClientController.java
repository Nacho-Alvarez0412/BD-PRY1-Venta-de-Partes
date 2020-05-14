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
import javax.swing.JOptionPane;

/**
 *
 * @author nacho
 */
public class SuspendClientController implements ActionListener {
    private SuspendClientMenu view;
    private ConnectionManager dataBaseConnection;
    private ClientMenuController previousView;

    public SuspendClientController(ConnectionManager dataBaseConnection, ClientMenuController previousView) {
        view = new SuspendClientMenu();
        this.dataBaseConnection = dataBaseConnection;
        this.previousView = previousView;
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
            
            try{
                Integer.valueOf(view.CedulaText.getText());
            }
            catch(java.lang.NumberFormatException ex){
                JOptionPane.showMessageDialog(view,"El valor del campo 'Cédula' debe ser un valor numérico");
                return;
            }
            
            try {
                if(dataBaseConnection.clientManager.changeClientState(view.CedulaText.getText(), (String)view.StateType.getSelectedItem()))
                    JOptionPane.showMessageDialog(view,"Estado actualizado con éxito");
                else
                    JOptionPane.showMessageDialog(view,"El cliente indicado no se encuentra en la base de datos");
            } catch (SQLException ex) {
                Logger.getLogger(SuspendClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if (e.getSource().equals(view.ExitButton)){
            System.out.println("Exiting");
            view.setVisible(false);  
            previousView.view.setVisible(true);
        }
        
    }
    
    
    
}
