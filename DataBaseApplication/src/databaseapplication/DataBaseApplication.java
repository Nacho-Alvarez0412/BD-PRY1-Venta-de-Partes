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
        
        
        String url = "jdbc:sqlserver://localhost:1433;databasename=VentaAutos";
        String user = "SA";
        String password = "<B4b0rsh162715>";
        
        try {
            
            //Connect
            ConnectionManager databaseConnection = new ConnectionManager(url,user,password);
            databaseConnection.connect();
            
            
            //databaseConnection.clientManager.changeClientState("1118090060", "Suspendido");
            
            /*
            ArrayList<String> persona = new ArrayList<>();
            persona.add("118090060");
            persona.add("'Ignacio Alvarez'");
            persona.add("'Heredia,Costa Rica'");
            persona.add("'Heredia'");
            persona.add("89288339");
            databaseConnection.clientManager.insertPerson(persona);
           */
            
            /*
            ArrayList<String> organizacion = new ArrayList<>();
            organizacion.add("1118090060");
            organizacion.add("'Gambobaby Music'");
            organizacion.add("'San Jose,Costa Rica'");
            organizacion.add("'San Jose'");
            databaseConnection.clientManager.insertOrganization(organizacion);
           */
           
            /*
            ArrayList<String> contacto = new ArrayList<>();
            contacto.add("'Gambobaby Music'");
            contacto.add("'Sebastian Gamboa'");
            contacto.add("'12345678'");
            contacto.add("'C.E.O'");
            databaseConnection.clientManager.insertContact(contacto);
            */
            
            /*
            ArrayList<String> cambios = new ArrayList<>();
            cambios.add("Nombre");
            cambios.add("'Juan Ignacio Alvarez'");
            cambios.add("Ciudad");
            cambios.add("'Cartago'");
            databaseConnection.clientManager.modifyClient("118090060", cambios);
            */
            
            //Pruebas clase PartManager
            
            //databaseConnection.partManager.insertPart("'Pistones'", "'International Spare Parts'", "'Fuller'");
            //databaseConnection.partManager.erasePart("Llanta");
            //databaseConnection.partManager.insertProvision("'J y A Autopartes'", "'Llanta'", "3000", "1000");
            //databaseConnection.partManager.linkPartWithVehicle("'Pistones'", "'Sentra'", "2007");
            //databaseConnection.partManager.updatePrices("'J y A Autopartes'", "'Llanta'", "4000", "1500");
            //databaseConnection.partManager.listPartsByVehicle("'Sentra'", "2007");
            
            
            //Pruebas clase OrderManager
            
           //databaseConnection.orderManager.listProvidersByPart("'Motor'");
           //databaseConnection.orderManager.insertOrder("1118090060", "'2020-05-09'", "6575", "1325");
           //databaseConnection.orderManager.linkDetailToOrder("1118090060",  "'2020-05-09'", "20", "'Llanta'", "'J y A Autopartes'");
            
            
            //Disconnect
            databaseConnection.disconnect();
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
}
