/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.OrderMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author nacho
 */
public class OrderMenuController implements ActionListener {
    private OrderMenu view;
    private ConnectionManager dataBaseConnection;

    public OrderMenuController(ConnectionManager dataBaseConnection) {
        view = new OrderMenu();
        this.dataBaseConnection = dataBaseConnection;
        init();
    }

    private void init() {
        view.InsertButton.addActionListener(this);
        view.BuyPartsButton.addActionListener(this);
        view.ProviderButton.addActionListener(this);
        view.ExitButton.addActionListener(this);
        view.setTitle("Menu de Ordenes");
        view.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.InsertButton)){
            System.out.println("Accediendo a ventana de Insertar Ordenes");
        }
        
        else if (e.getSource().equals(view.BuyPartsButton)){
            System.out.println("Accediendo a ventana de Comprar Partes");
        }
        
        else if (e.getSource().equals(view.ProviderButton)){
            System.out.println("Accediendo a ventana de Listar Proveedores");
        }
        else if (e.getSource().equals(view.ExitButton)){
            System.out.println("Exiting");
            view.setVisible(false);    
        }
        
    }
    
    
    
}
