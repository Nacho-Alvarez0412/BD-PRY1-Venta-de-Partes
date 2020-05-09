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
            
            //Get Table
            databaseConnection.getTable("Proveedor");
            //Get rows from a table
            databaseConnection.getColumnFromTable("Automóvil", "Modelo");
            
            //Get specific rows from a table from one and two columns
            
            databaseConnection.getRows2Variables("Automóvil", "Modelo", "'Fox'", "Año", "2013");
            databaseConnection.getRows1Variable("Automóvil", "Modelo", "'Sentra'");
            
            
            
            //Delete
            //String output = databaseConnection.deleteRow("hola", "Modelo", "Automóvil");
            //System.out.println(output);
            
            
            // En el insert, se le pasa una lista de los strings que son los datos que van en las columnas
            //El numero de la lista tiene que ser igual al de las columnas que se llenan
            //Si el dato son palabras, se la añaden '' al string, ejemplo de String: " ' Ejemplo ' ";
            //Si el dato es un numero, no se añade nada, ejemplo: " 1234 "
            
            ArrayList<String> listOfValues = new ArrayList<String>();
            listOfValues.add("'Lexus CT'");
            listOfValues.add("2019");
            listOfValues.add("'Full extras'");
            listOfValues.add("1");
            
            //Insert
           // databaseConnection.insertRow(listOfValues, "Automóvil");
           
           //Update Row
           //databaseConnection.updateRow("Cliente", "Estado", "ClienteID", "13", "'Activo'");
           
            //Disconnect
            databaseConnection.disconnect();
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }
}
