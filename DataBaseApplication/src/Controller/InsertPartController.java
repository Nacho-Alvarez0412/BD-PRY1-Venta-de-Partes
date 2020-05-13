/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.InsertPartMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author nacho
 */
public class InsertPartController implements ActionListener {
    private InsertPartMenu view;
    private ConnectionManager dataBaseConnection;

    public InsertPartController(ConnectionManager dataBaseConnection) {
        view = new InsertPartMenu();
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
            System.out.println("Inserting Part");
            String parte = "'"+view.NombreText.getText()+"'";
            String fabricante = "'"+view.FabricanteText.getText()+"'";
            String marca = "'"+view.MarcaText.getText()+"'";
            System.out.println(dataBaseConnection.partManager.insertPart(parte, fabricante, marca));
        }
        
        else if (e.getSource().equals(view.ExitButton)){
            System.out.println("Exiting");
            view.setVisible(false);    
        }
        
    }
    
    
    
}
