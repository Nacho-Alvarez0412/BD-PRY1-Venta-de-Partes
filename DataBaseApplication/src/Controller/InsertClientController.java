/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.ClientMenu;
import View.InsertClientMenu;
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
public class InsertClientController implements ActionListener {
    private InsertClientMenu view;
    private ConnectionManager dataBaseConnection;
    private ClientMenuController previousView;

    public InsertClientController(ConnectionManager dataBaseConnection,ClientMenuController previousView) {
        view = new InsertClientMenu();
        this.dataBaseConnection = dataBaseConnection;
        this.previousView = previousView;
        init();
    }

    private void init() {
        
        view.InsertButton.addActionListener(this);
        view.ExitButton.addActionListener(this);
        view.setTitle("Menu de Clientes");
        view.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.InsertButton)){
            System.out.println("Inserting Client");
            int type = view.ClientType.getSelectedIndex();
             
            if(type == 0){
                ArrayList<String> persona = new ArrayList<>();
                persona.add(view.CedulaText.getText());
                persona.add("'"+view.NombreText.getText()+"'");
                persona.add("'"+view.DireccionText.getText()+"'");
                persona.add("'"+view.CiudadText.getText()+"'");
                persona.add(view.NumeroText.getText());
                
                try {
                    
                    System.out.println(this.dataBaseConnection.clientManager.insertPerson(persona));
                    
                } catch (SQLException ex) {
                    Logger.getLogger(InsertClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }else{
                ArrayList<String> organizacion = new ArrayList<>();
                organizacion.add(view.CedulaText.getText());
                organizacion.add("'"+view.NombreText.getText()+"'");
                organizacion.add("'"+view.DireccionText.getText()+"'");
                organizacion.add("'"+view.CiudadText.getText()+"'");
                
                try {
                    
                    System.out.println(this.dataBaseConnection.clientManager.insertOrganization(organizacion));
                    
                } catch (SQLException ex) {
                    Logger.getLogger(InsertClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        else if (e.getSource().equals(view.ExitButton)){
            System.out.println("Exiting");
            view.setVisible(false);
            previousView.view.setVisible(true);
        }
        
    }
    
    
    
}
