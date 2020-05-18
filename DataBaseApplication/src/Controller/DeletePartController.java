/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.DeletePartMenu;
import View.InsertClientMenu;
import View.InsertPartMenu;
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
public class DeletePartController implements ActionListener {
    private DeletePartMenu view;
    private ConnectionManager dataBaseConnection;
    private PartMenuController previousView;

    public DeletePartController(ConnectionManager dataBaseConnection,PartMenuController previousView) {
        view = new DeletePartMenu();
        this.dataBaseConnection = dataBaseConnection;
        this.previousView = previousView;
        init();
    }

    private void init() {
        
        view.InsertButton.addActionListener(this);
        view.ExitButton.addActionListener(this);
        view.setTitle("Menu de Partes");
        view.setVisible(true);
        fillPartes();
        
    }
    
    private void fillPartes() {
        ArrayList<String> partes = dataBaseConnection.getColumnFromTable("Parte", "NombreParte");
        
        for (int i = 0 ; i < partes.size() ; i++){
            view.ParteComboBox.addItem(partes.get(i));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.InsertButton)){
            String parte = (String) view.ParteComboBox.getSelectedItem();
            
            if(dataBaseConnection.partManager.erasePart(parte))
                JOptionPane.showMessageDialog(view, "Parte eliminada con éxito");
            else
                JOptionPane.showMessageDialog(view, "La parte no puede ser borrada ya que forma parte de una orden");
        }
        
        else if (e.getSource().equals(view.ExitButton)){
            view.setVisible(false);   
            previousView.view.setVisible(true);
        }
        
    }
    
    
    
}
