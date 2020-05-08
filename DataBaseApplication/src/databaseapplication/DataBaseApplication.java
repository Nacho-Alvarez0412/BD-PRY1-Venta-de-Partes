/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to Microsoft SQL Server");
            
            Statement sqlStatement = connection.createStatement();
 
            ResultSet rs = null;

            //Lista de comandos
            
            String queryString = "";/*
            queryString+="SELECT Modelo FROM Automóvil;";
            
            
            System.out.println("\nQuery string:");
            System.out.println(queryString);

            //ejecuta los comandos
            rs=sqlStatement.executeQuery(queryString);


            while (rs.next()) {
                String lastName = rs.getString("Modelo");
                System.out.println(lastName + "\n");
            }

            rs.close();
            
            rs = null;*/
            
            queryString = "";
            queryString+="INSERT INTO Cliente (Estado) VALUES ('2');";
            queryString+="INSERT INTO Cliente (Estado) VALUES ('Activo');";
            queryString+="INSERT INTO Cliente (Estado) VALUES ('3');";
            
            queryString+="DELETE FROM Cliente WHERE Estado = '2';";
            
            System.out.println("\nQuery string:");
            System.out.println(queryString);
            
            sqlStatement.execute(queryString);

            System.out.println("Closing database connection");

            
            connection.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseApplication.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("An error has ocurred");
        }
          
        
        
    }
}
