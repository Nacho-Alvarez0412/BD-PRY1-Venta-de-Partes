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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nacho
 */
public class ListClientsController implements ActionListener{
    ListClients view;
    ClientMenu previousView;
    DefaultTableModel tabla;

    public ListClientsController(ClientMenu previousView,ArrayList<ArrayList<String>> clients) {
        this.previousView = previousView;
        view = new ListClients();
        tabla = (DefaultTableModel) view.ClientsTable.getModel();
        fillTable(clients);
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

    private void fillTable(ArrayList<ArrayList<String>> personas) {
        for (int cliente = 0 ; cliente<personas.size() ; cliente++){
            ArrayList<Object> row = new ArrayList<>();
            
            for(int dato = 0 ; dato<personas.get(cliente).size() ; dato++){
                row.add(personas.get(cliente).get(dato));
            }
            tabla.addRow(row.toArray());
        }
    }
    
}
