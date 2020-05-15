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
    public OrderMenu view;
    private ConnectionManager dataBaseConnection;
    private PartsByProviderController partsByProvider;
    private InsertOrderController insertOrder;
    private AddBuyToOrderController addBuy;
    private MainMenuController previousView;

    public OrderMenuController(ConnectionManager dataBaseConnection,MainMenuController previousView) {
        view = new OrderMenu();
        this.dataBaseConnection = dataBaseConnection;
        this.previousView = previousView;
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
             insertOrder = new InsertOrderController(dataBaseConnection,this);
             view.setVisible(false);
        }
        
        else if (e.getSource().equals(view.BuyPartsButton)){
            addBuy = new AddBuyToOrderController(dataBaseConnection,this);
            view.setVisible(false);
        }
        
        else if (e.getSource().equals(view.ProviderButton)){
            partsByProvider = new PartsByProviderController(dataBaseConnection,this);
            view.setVisible(false);
        }
        else if (e.getSource().equals(view.ExitButton)){
            view.setVisible(false);
            previousView.view.setVisible(true);
        }
        
    }
    
    
    
}
