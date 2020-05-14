/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.ClientMenu;
import View.ListClients;
import View.MainMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nacho
 */
public class ListClientsController implements ActionListener{
    ListClients view;
    ClientMenu previousView;

    public ListClientsController(ClientMenu previousView,String personas,String organizaciones) {
        this.previousView = previousView;
        view = new ListClients();
        view.PersonajTextArea.append(personas);
        view.PersonajTextArea.append(organizaciones);
        view.setVisible(true);
        view.ExitButton.addActionListener(this);
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.ExitButton)){
            view.setVisible(false);
            previousView.setVisible(true);
        
        }
    }
    
}
