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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nacho
 */
public class MainMenuController implements ActionListener {
    private ConnectionManager dataBaseConnection;
    public MainMenu view;
    public ClientMenuController clientMenu;
    public PartMenuController partMenu;
    public OrderMenuController orderMenu;

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
        dataBaseConnection.connect();
        
        view.addWindowListener(new WindowAdapter() {
 
            @Override

            public void windowClosing(WindowEvent e) {

                try {
                    dataBaseConnection.disconnect();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);

            }
        });
                    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.ClientesButton)){
            System.out.println("Accediendo a ventana de Clientes");
            clientMenu = new ClientMenuController(dataBaseConnection,this);
            view.setVisible(false);
        }
        
        else if (e.getSource().equals(view.OrdenButton)){
            System.out.println("Accediendo a ventana de Ordenes");
            orderMenu = new OrderMenuController(dataBaseConnection);
        }
        
        else if (e.getSource().equals(view.PartesButton)){
            System.out.println("Accediendo a ventana de Partes");
            partMenu = new PartMenuController(dataBaseConnection);
        }
        
    }
    
    
    
}
