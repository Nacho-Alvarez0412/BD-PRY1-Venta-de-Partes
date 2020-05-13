/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.LinkPartWithVehicle;
import View.ListPartsMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author nacho
 */
public class ListPartController implements ActionListener {
    private ListPartsMenu view;
    private ConnectionManager dataBaseConnection;

    public ListPartController(ConnectionManager dataBaseConnection) {
        view = new ListPartsMenu();
        this.dataBaseConnection = dataBaseConnection;
        init();
    }

    private void init() {
        
        view.InsertButton.addActionListener(this);
        view.ExitButton.addActionListener(this);
        view.setTitle("Menu de Partes");
        view.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.InsertButton)){
            System.out.println("Linking part with vehicle");
            
            String modelo = "'"+view.ModeloText.getText()+"'";
            String year = view.YearText.getText();
            System.out.println(dataBaseConnection.partManager.listPartsByVehicle(modelo, year));
        }
        
        else if (e.getSource().equals(view.ExitButton)){
            System.out.println("Exiting");
            view.setVisible(false);    
        }
        
    }
    
    
    
}
