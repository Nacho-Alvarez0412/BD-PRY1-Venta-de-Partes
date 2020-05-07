/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication;

import java.sql.Connection;
import java.sql.DriverManager;
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
        String url = "jdbc:sqlserver://localhost:1433;databasename=Venta de Partes";
        String user = "nacho1";
        String password = "1234";
        
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to Microsoft SQL Server");
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("An error has ocurred");
        }
        
        
    }
}
