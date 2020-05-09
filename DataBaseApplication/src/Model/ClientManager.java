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
public class ClientManager {
    private ConnectionManager databaseConnection;

    ClientManager(ConnectionManager aThis) {
        this.databaseConnection = aThis;
    }
    
    
    public int insertClient() throws SQLException{
        
        //databaseConnection.connect();
        
        ArrayList<String> values = new ArrayList<String>();
        values.add("'Activo'");
        
        int clientID = databaseConnection.insertRow(values, "Cliente");
        
        System.out.println("Client added");
        
        //databaseConnection.disconnect();
        return clientID;
    }
    
    public void insertPerson(ArrayList<String> parameters) throws SQLException{
        ArrayList<String> telefono = new ArrayList<>();
        telefono.add(parameters.get(parameters.size()-1));
        telefono.add(parameters.get(0));
        parameters.remove(parameters.size()-1);
        
        int clientID = insertClient();
        
        parameters.add(String.valueOf(clientID));
        
        databaseConnection.insertRow(parameters, "Persona");
        databaseConnection.insertRow(telefono, "TelefonoPersona");
    }
    
    public void insertOrganization(ArrayList<String> parameters) throws SQLException{
        
        int clientID = insertClient();
        
        parameters.add(String.valueOf(clientID));
        
        databaseConnection.insertRow(parameters, "Organización");
        
    }
    
    public void insertContact(ArrayList<String> parameters){
        ArrayList<String> organizationInfo = databaseConnection.getRows1Variable("Organización", "Nombre", parameters.get(0));
        parameters.remove(0);
        parameters.add(organizationInfo.get(0));
        databaseConnection.insertRow(parameters, "Contacto");
    }
    
    public void changeClientState(String ID,String newState) throws SQLException{
        newState = "'"+newState+"'";
        
        databaseConnection.updateRow("Cliente", "Estado", "ClienteID", ID, newState);
        
        System.out.println("Client state Change");
        
    }
    
    public void modifyClient(String clientID,ArrayList<String> newInfo){
        ArrayList<String> tableInfo;
        if(clientID.length() == 9){
            tableInfo = databaseConnection.getRows1Variable("Persona", "Cedula", clientID);
            
            for (int i = 0 ; i < newInfo.size() ; i++){
                databaseConnection.updateRow("Persona", newInfo.get(i), "Cedula", tableInfo.get(0), newInfo.get(++i));
            }
        }
        
        else{
            tableInfo = databaseConnection.getRows1Variable("Organización", "Cedula", clientID);
        
            for (int i = 0 ; i < newInfo.size() ; i++){
                databaseConnection.updateRow("Organizacion", newInfo.get(i), "Cedula", tableInfo.get(0), newInfo.get(++i));
            }
        }
        
        
    }
    
    public void listClients() throws SQLException{
        
        databaseConnection.getTable("Cliente");
    }
}
