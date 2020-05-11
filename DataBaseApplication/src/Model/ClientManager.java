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
    
    public String insertPerson(ArrayList<String> parameters) throws SQLException{
        if(databaseConnection.errorManager.checkClientIntegrity(parameters.get(0))){
            ArrayList<String> telefono = new ArrayList<>();
            telefono.add(parameters.get(parameters.size()-1));
            telefono.add(parameters.get(0));
            parameters.remove(parameters.size()-1);

            int clientID = insertClient();

            parameters.add(String.valueOf(clientID));

            databaseConnection.insertRow(parameters, "Persona");
            databaseConnection.insertRow(telefono, "TelefonoPersona");
            return "Cliente agregado con éxito";
        }
        return "Cliente digitado ya existe en la base de datos, o no cumple con los parametros adecuados";
    }
    
    public String insertOrganization(ArrayList<String> parameters) throws SQLException{
        if(databaseConnection.errorManager.checkClientIntegrity(parameters.get(0)) && databaseConnection.errorManager.checkOrgIntegrity(parameters.get(1)) ){
            int clientID = insertClient();

            parameters.add(String.valueOf(clientID));

            databaseConnection.insertRow(parameters, "Organización");
            return "Cliente agregado con éxito";
        }
        return "Cliente digitado ya existe en la base de datos, o no cumple con los parametros adecuados";
    }
    
    public String insertContact(ArrayList<String> parameters){
        
        if(!databaseConnection.errorManager.checkOrgIntegrity(parameters.get(0)) && databaseConnection.errorManager.checkContactIntegrity(parameters.get(0)) ){
            ArrayList<String> organizationInfo = databaseConnection.getRows1Variable("Organización", "Nombre", parameters.get(0));
            parameters.remove(0);
            parameters.add(organizationInfo.get(0));
            databaseConnection.insertRow(parameters, "Contacto");
            return "Contacto agregado con éxito a la base de datos";
        }
        return "Organización indicada no existe en la base de datos o ya posee un contacto asignado";
    }
    
    public void changeClientState(String clientID,String newState) throws SQLException{
        
        newState = "'"+newState+"'";
        ArrayList<String> rowInfo;
        
        if(clientID.length() == 9){
            rowInfo = databaseConnection.getRows1Variable("Persona", "Cedula", clientID);
        }
        else{
            rowInfo = databaseConnection.getRows1Variable("Organización", "Cedula", clientID);
        }
        
        databaseConnection.updateRow("Cliente", "Estado", "ClienteID", rowInfo.get(4), newState);
        
        System.out.println("Client state Change");
        
    }
    
    public String modifyClient(String clientID,ArrayList<String> newInfo){
        ArrayList<String> tableInfo;
        if(clientID.length() == 9){
            tableInfo = databaseConnection.getRows1Variable("Persona", "Cedula", clientID);
            
            if(!tableInfo.isEmpty()){
            
                for (int i = 0 ; i < newInfo.size() ; i++){
                    databaseConnection.updateRow("Persona", newInfo.get(i), "Cedula", tableInfo.get(0), newInfo.get(++i));
                }
            }
            else 
                return "El cliente ingresado no se encuentra en la base de datos";
        }
        
        else{
            tableInfo = databaseConnection.getRows1Variable("Organización", "Cedula", clientID);
            
            if(!tableInfo.isEmpty()){
            
                for (int i = 0 ; i < newInfo.size() ; i++){
                    databaseConnection.updateRow("Organizacion", newInfo.get(i), "Cedula", tableInfo.get(0), newInfo.get(++i));
                }
            }
            else 
                return "El cliente ingresado no se encuentra en la base de datos";
        } 
        return "Cliente modificado con éxito";
    }
    
    public void listClients() throws SQLException{
        
        databaseConnection.getTable("Cliente");
    }

}
