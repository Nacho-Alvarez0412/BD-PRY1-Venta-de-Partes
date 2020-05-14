/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.InsertClientMenu;
import View.ModifyClient;
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
public class ModifyClientController implements ActionListener{
    private ModifyClient view;
    private ConnectionManager dataBaseConnection;
    private ClientMenuController previousView;

    public ModifyClientController(ConnectionManager dataBaseConnection,ClientMenuController previousView) {
        view = new ModifyClient();
        this.dataBaseConnection = dataBaseConnection;
        this.previousView = previousView;
        init();
    }

    private void init() {
        
        view.ModifyButton.addActionListener(this);
        view.ExitButton.addActionListener(this);
        view.setTitle("Menu de Clientes");
        view.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.ModifyButton)){
            if(view.CedulaText.getText().length()<9 || view.CedulaText.getText().length()>10){
                JOptionPane.showMessageDialog(view,"Cédula ingresada no es válida");
                return;
            }
            String nombre = view.NombreText.getText();
            String ciudad = view.CiudadText.getText();
            String direccion = view.DireccionText.getText();
            String telefono = view.NumeroText.getText();
            
            ArrayList<String> parameters = new ArrayList<String>();
            
            if(nombre.length() != 0){
                parameters.add("Nombre");
                parameters.add("'"+nombre+"'");
            }
            
            if(ciudad.length() != 0){
                parameters.add("Ciudad");
                parameters.add("'"+ciudad+"'");
            }
            
            if(direccion.length() != 0){
                parameters.add("Direccion");
                parameters.add("'"+direccion+"'");
            }
            
            if(dataBaseConnection.clientManager.modifyClient(view.CedulaText.getText(), parameters)){
                JOptionPane.showMessageDialog(view,"El cliente fue actualizado con éxito");
                return;
            }
            JOptionPane.showMessageDialog(view,"El cliente ingresado no se encuentra en la base de datos");
            
        }
        
        if(e.getSource().equals(view.ExitButton)){
            view.setVisible(false);
            previousView.view.setVisible(true);
        }
    }
}
