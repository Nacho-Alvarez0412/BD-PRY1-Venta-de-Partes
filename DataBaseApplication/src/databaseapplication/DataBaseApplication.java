/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication;

import Model.ConnectionManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author nacho
 */
public class DataBaseApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        String url = "jdbc:sqlserver://localhost:1433;databasename=Venta de Partes";
        String user = "Nacho1";
        String password = "1234";
        
        try {
            
            //Connect
            ConnectionManager databaseConnection = new ConnectionManager(url,user,password);
            databaseConnection.connect();
            
            //databaseConnection.getTable("Proveedor");
            
            
            //databaseConnection.clientManager.changeClientState("1118090060", "Inactivo");
            
            /*
            ArrayList<String> persona = new ArrayList<>();
            persona.add("118090060");
            persona.add("'Ignacio Alvarez'");
            persona.add("'Heredia,Costa Rica'");
            persona.add("'Heredia'");
            persona.add("89288339");
            System.out.println(databaseConnection.clientManager.insertPerson(persona));
            */
            
            /*
            ArrayList<String> organizacion = new ArrayList<>();
            organizacion.add("1118090061");
            organizacion.add("'Gambobaby Music'");
            organizacion.add("'San Jose,Costa Rica'");
            organizacion.add("'San Jose'");
            System.out.println(databaseConnection.clientManager.insertOrganization(organizacion));
            */
                   
            /*
            ArrayList<String> contacto = new ArrayList<>();
            contacto.add("'Gambobaby Music'");
            contacto.add("'Sebastian Gamboa'");
            contacto.add("'12345678'");
            contacto.add("'C.E.O'");
            System.out.println(databaseConnection.clientManager.insertContact(contacto));
            */
            
            /*
            ArrayList<String> cambios = new ArrayList<>();
            cambios.add("Nombre");
            cambios.add("'Juan Ignacio Alvarez'");
            cambios.add("Ciudad");
            cambios.add("'Cartago'");
            System.out.println(databaseConnection.clientManager.modifyClient("118090061", cambios));
            */ 
            
            //Pruebas clase PartManager
            
            
            //System.out.println(databaseConnection.partManager.insertPart("'Llanta'", "'Auto Spare Parts'", "'Falken'"));
            //System.out.println(databaseConnection.partManager.erasePart("Llanta"));
            //System.out.println(databaseConnection.partManager.insertProvision("'J y A Autopartes'", "'Llanta'", "3000", "1000"));
            //System.out.println(databaseConnection.partManager.linkPartWithVehicle("'Llanta'", "'Sentra'", "2007"));
            //System.out.println(databaseConnection.partManager.updatePrices("'J y A Autopartes'", "'Luces'", "4000", "1500"));
            //System.out.println(databaseConnection.partManager.listPartsByVehicle("'Sentra'", "2007"));
            
            
            
            //Pruebas clase OrderManager
            
            //System.out.println(databaseConnection.orderManager.listProvidersByPart("'Llanta'"));
            //System.out.println(databaseConnection.orderManager.insertOrder("118090060", "'2020-05-09'"));
            //System.out.println(databaseConnection.orderManager.linkDetailToOrder("118090060",  "'2020-05-09'", "2", "'Llanta'", "'J y A Autopartes'"));
            
            
            //Disconnect
            databaseConnection.disconnect();
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
}
