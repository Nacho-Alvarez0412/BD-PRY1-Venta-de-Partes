/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.MainMenu;
import View.PartMenu;
import View.UpdatePricesMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author nacho
 */
public class PartMenuController implements ActionListener {
    private MainMenuController previousView;
    public PartMenu view;
    private ConnectionManager dataBaseConnection;
    private InsertPartController insertPart;
    private DeletePartController deletePart;
    private LinkPartWithProviderController linkProvider;
    private LinkPartWithVehicleController linkVehicle;
    private UpdatePricesController updatePrices;
    private ListPartController listParts;

    public PartMenuController(ConnectionManager dataBaseConnection,MainMenuController previousView) {
        view = new PartMenu();
        this.dataBaseConnection = dataBaseConnection;
        this.previousView = previousView;
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
            insertPart = new InsertPartController(dataBaseConnection,this);
            this.view.setVisible(false);
        }
        
        else if (e.getSource().equals(view.DeleteButton)){
            deletePart = new DeletePartController(dataBaseConnection,this);
            this.view.setVisible(false);
        }
        
        else if (e.getSource().equals(view.ListPartButton)){
            listParts = new ListPartController(dataBaseConnection,this);
            this.view.setVisible(false);
        }
        else if (e.getSource().equals(view.PreciosButton)){
            updatePrices = new UpdatePricesController(dataBaseConnection,this);
            this.view.setVisible(false);
        } 
        else if (e.getSource().equals(view.ExitButton)){
            view.setVisible(false); 
            previousView.view.setVisible(true);
        }
        else if (e.getSource().equals(view.ProviderButton)){
             linkProvider = new LinkPartWithProviderController(dataBaseConnection,this);
             this.view.setVisible(false);
        }
        else if (e.getSource().equals(view.VehicleButton)){
             linkVehicle = new LinkPartWithVehicleController(dataBaseConnection,this);
             this.view.setVisible(false);
        }
        
    }
    
    
    
}
