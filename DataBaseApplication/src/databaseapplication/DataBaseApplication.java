/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication;

import Model.ConnectionManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            
            ConnectionManager databaseConnection = new ConnectionManager(url,user,password);
            databaseConnection.connect();
            databaseConnection.getTable("Proveedor");
            String output = databaseConnection.deleteRow("1999", "Año", "Automóvil");
            System.out.println(output);
            databaseConnection.disconnect();
            
            /*queryString = "";
            queryString+="INSERT INTO Cliente (Estado) VALUES ('2');";
            queryString+="INSERT INTO Cliente (Estado) VALUES ('Activo');";
            queryString+="INSERT INTO Cliente (Estado) VALUES ('3');";
            
            sqlStatement.execute(queryString);*/
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
