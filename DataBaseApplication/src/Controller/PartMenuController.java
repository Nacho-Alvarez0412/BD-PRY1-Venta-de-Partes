/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.PartMenu;
import View.UpdatePricesMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author nacho
 */
public class PartMenuController implements ActionListener {
    private PartMenu view;
    private ConnectionManager dataBaseConnection;
    private InsertPartController insertPart;
    private DeletePartController deletePart;
    private LinkPartWithProviderController linkProvider;
    private LinkPartWithVehicleController linkVehicle;
    private UpdatePricesController updatePrices;
    private ListPartController listParts;

    public PartMenuController(ConnectionManager dataBaseConnection) {
        view = new PartMenu();
        this.dataBaseConnection = dataBaseConnection;
        init();
    }

    private void init() {
        view.InsertButton.addActionListener(this);
        view.DeleteButton.addActionListener(this);
        view.ExitButton.addActionListener(this);
        view.ListPartButton.addActionListener(this);
        view.PreciosButton.addActionListener(this);
        view.ProviderButton.addActionListener(this);
        view.VehicleButton.addActionListener(this);
        view.setTitle("Menu de Partes");
        view.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.InsertButton)){
            System.out.println("Accediendo a ventana de Insertar Partes");
            insertPart = new InsertPartController(dataBaseConnection);
        }
        
        else if (e.getSource().equals(view.DeleteButton)){
            System.out.println("Accediendo a ventana de Borrar Partes");
            deletePart = new DeletePartController(dataBaseConnection);
        }
        
        else if (e.getSource().equals(view.ListPartButton)){
            System.out.println("Accediendo a ventana de Listar Partes");
            listParts = new ListPartController(dataBaseConnection);
        }
        else if (e.getSource().equals(view.PreciosButton)){
            System.out.println("Accediendo a ventana de Actualizar Precios");
            updatePrices = new UpdatePricesController(dataBaseConnection);
        } 
        else if (e.getSource().equals(view.ExitButton)){
            System.out.println("Exiting");
            view.setVisible(false);    
        }
        else if (e.getSource().equals(view.ProviderButton)){
             System.out.println("Accediendo a ventana de Asociar Proveedores con Partes"); 
             linkProvider = new LinkPartWithProviderController(dataBaseConnection);
        }
        else if (e.getSource().equals(view.VehicleButton)){
             System.out.println("Accediendo a ventana de Asociar Autos con Partes");
             linkVehicle = new LinkPartWithVehicleController(dataBaseConnection);
        }
        
    }
    
    
    
}
