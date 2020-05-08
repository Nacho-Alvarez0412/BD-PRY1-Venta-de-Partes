/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSetMetaData;

/**
 *
 * @author sebasgamboa
 */
public class ConnectionManager {
    
    public Connection connection;
    public String url;
    public String user;
    public String password;
    
    
    public ConnectionManager(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }
    
    public void connect(){
        try {
            
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to Microsoft SQL Server");
            
        } catch (SQLException ex) {
            
            System.out.println("error connecting to database");
            
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void disconnect() throws SQLException{
        connection.close();
    }
    
    public String getTable(String table){
        
        try {
            
            Statement sqlStatement = connection.createStatement();
            
            ResultSet rs = null;
            
            String queryString = "";
            queryString+="SELECT * FROM "+table;
            
            System.out.println("\nQuery string:");
            System.out.println(queryString);
            
            rs=sqlStatement.executeQuery(queryString);
            ResultSetMetaData rsmd = rs.getMetaData();
            
            int columnsNumber = rsmd.getColumnCount();       

            while (rs.next()) {
                
                for(int i = 1 ; i <= columnsNumber; i++){

                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println();
            }

            rs.close();
            
            return table;
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String deleteRow(String row, String column, String table){
        
        try {
            Statement sqlStatement = connection.createStatement();
            
            String queryString = "";
            queryString+="DELETE FROM "+table+ " WHERE "+column+" = "+row+ ";";
            
            System.out.println("\nQuery string:");
            System.out.println(queryString);
            
            sqlStatement.execute(queryString);
            
            return "Row deleted from "+ table;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
