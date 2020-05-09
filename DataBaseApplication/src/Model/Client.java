/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sebasgamboa
 */
public class Client {
    public ConnectionManager databaseConnection;
    
    public void insertClient(String Estado) throws SQLException{
        
        databaseConnection.connect();
        
        ArrayList<String> values = new ArrayList<String>();
        values.add(Estado);
        
        databaseConnection.insertRow(values, "Cliente");
        
        System.out.println("Client added");
        
        databaseConnection.disconnect();
    }
    
    public void suspendClient(String ID) throws SQLException{
        
        databaseConnection.connect();
        
        databaseConnection.updateRow("Cliente", "Estado", "ClienteID", ID, "'Suspendido'");
        
        System.out.println("Client suspended");
        
        databaseConnection.disconnect();
    }
    
    public void listClients() throws SQLException{
        
        databaseConnection.connect();
        databaseConnection.getTable("Cliente");
        databaseConnection.disconnect();
    }
}
