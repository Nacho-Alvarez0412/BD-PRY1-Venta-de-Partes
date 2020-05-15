/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConnectionManager;
import View.DeletePartMenu;
import View.InsertClientMenu;
import View.InsertPartMenu;
import View.PartsByProviderMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author nacho
 */
public class PartsByProviderController implements ActionListener {
    private PartsByProviderMenu view;
    private ConnectionManager dataBaseConnection;
    private OrderMenuController previousView;

    public PartsByProviderController(ConnectionManager dataBaseConnection,OrderMenuController previousView) {
        view = new PartsByProviderMenu();
        this.dataBaseConnection = dataBaseConnection;
        this.previousView = previousView;
        init();
    }

    private void init() {
        
        view.ListButton.addActionListener(this);
        view.ExitButton.addActionListener(this);
        view.setTitle("Menu de Ordenes");
        view.setVisible(true);
        fillPartes();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.ListButton)){
            System.out.println("Listing Parts");
            String parte = "'"+view.ParteComboBox.getSelectedItem()+"'";
            ArrayList<String> proveedores = dataBaseConnection.orderManager.listProvidersByPart(parte);
            ArrayList<ArrayList<String>> data = matchIDs(proveedores);
            fillTable(data);
            
        }
        
        else if (e.getSource().equals(view.ExitButton)){
            System.out.println("Exiting");
            view.setVisible(false);    
            previousView.view.setVisible(true);
        }
        
    }
    
    private void fillPartes() {
        ArrayList<String> partes = dataBaseConnection.getColumnFromTable("Parte", "NombreParte");
        
        for (int i = 0 ; i < partes.size() ; i++){
            view.ParteComboBox.addItem(partes.get(i));
        }
    }
    
    private void fillTable(ArrayList<ArrayList<String>> data){
        TableModel model = view.ProveedorTable.getModel();
        
        for(int i = 0 ; i< data.size() ; i++){
            model.setValueAt(data.get(i).get(0), i, 0);
            model.setValueAt(data.get(i).get(1), i, 1);
        }
    }

    private ArrayList<ArrayList<String>> matchIDs(ArrayList<String> proveedores) {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        
        for (int i = 0 ; i<proveedores.size() ; i++){
            ArrayList<String> temp = new ArrayList<>();
            temp.add(dataBaseConnection.orderManager.getProviderID("'"+proveedores.get(i)+"'"));
            temp.add(proveedores.get(i));
            data.add(temp);
        }
        return data;
    }
    
    
    
}
