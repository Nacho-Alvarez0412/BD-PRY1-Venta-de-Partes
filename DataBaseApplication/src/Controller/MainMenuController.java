/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.MainMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author nacho
 */
public class MainMenuController implements ActionListener {
    private ConnectionManager dataBaseConnection;
    private MainMenu view;

    public MainMenuController(String url, String user, String password) {
        dataBaseConnection = new ConnectionManager(url, user, password);
        view = new MainMenu();
        init();
    }

    private void init() {
        view.ClientesButton.addActionListener(this);
        view.OrdenButton.addActionListener(this);
        view.PartesButton.addActionListener(this);
        view.setTitle("Manejo de Datos Venta de Partes");
        view.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.ClientesButton)){
            System.out.println("Accediendo a ventana de Clientes");
        }
        
        else if (e.getSource().equals(view.OrdenButton)){
            System.out.println("Accediendo a ventana de Ordenes");
        }
        
        else if (e.getSource().equals(view.PartesButton)){
            System.out.println("Accediendo a ventana de Partes");
        }
    }
    
    
    
}
