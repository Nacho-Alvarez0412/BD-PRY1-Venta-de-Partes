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
    
    public boolean insertPerson(ArrayList<String> parameters) throws SQLException{
        if(databaseConnection.errorManager.checkClientIntegrity(parameters.get(0))){
            ArrayList<String> telefono = new ArrayList<>();
            telefono.add(parameters.get(parameters.size()-1));
            telefono.add(parameters.get(0));
            parameters.remove(parameters.size()-1);

            int clientID = insertClient();

            parameters.add(String.valueOf(clientID));

            databaseConnection.insertRow(parameters, "Persona");
            databaseConnection.insertRow(telefono, "TelefonoPersona");
            return true;
        }
        return false;
    }
    
    public boolean insertOrganization(ArrayList<String> parameters) throws SQLException{
        if(databaseConnection.errorManager.checkClientIntegrity(parameters.get(0)) && databaseConnection.errorManager.checkOrgIntegrity(parameters.get(1)) ){
            int clientID = insertClient();

            parameters.add(String.valueOf(clientID));

            databaseConnection.insertRow(parameters, "Organización");
            return true;
        }
        return false;
    }
    
    public boolean insertContact(ArrayList<String> parameters){
        
        if(!databaseConnection.errorManager.checkOrgIntegrity(parameters.get(0)) && databaseConnection.errorManager.checkContactIntegrity(parameters.get(0)) ){
            ArrayList<String> organizationInfo = databaseConnection.getRows1Variable("Organización", "Nombre", parameters.get(0));
            parameters.remove(0);
            parameters.add(organizationInfo.get(0));
            databaseConnection.insertRow(parameters, "Contacto");
            return true;
        }
        return false;
    }
    
    public boolean changeClientState(String clientID,String newState) throws SQLException{
        
        newState = "'"+newState+"'";
        ArrayList<String> rowInfo;
        
        if(clientID.length() == 9){
            rowInfo = databaseConnection.getRows1Variable("Persona", "Cedula", clientID);
        }
        else{
            rowInfo = databaseConnection.getRows1Variable("Organización", "Cedula", clientID);
        }
        if(rowInfo.isEmpty())
            return false;
        else{
            databaseConnection.updateRow("Cliente", "Estado", "ClienteID", rowInfo.get(4), newState);
            return true;
        }
                
    }
    
    public boolean modifyClient(String clientID,ArrayList<String> newInfo){
        ArrayList<String> tableInfo;
        if(clientID.length() == 9){
            tableInfo = databaseConnection.getRows1Variable("Persona", "Cedula", clientID);
            
            if(!tableInfo.isEmpty()){
            
                for (int i = 0 ; i < newInfo.size() ; i++){
                    databaseConnection.updateRow("Persona", newInfo.get(i), "Cedula", tableInfo.get(0), newInfo.get(++i));
                }
            }
            else 
                return false;
        }
        
        else{
            tableInfo = databaseConnection.getRows1Variable("Organización", "Cedula", clientID);
            System.out.println(tableInfo);
            
            if(!tableInfo.isEmpty()){
            
                for (int i = 0 ; i < newInfo.size() ; i++){
                    databaseConnection.updateRow("Organización", newInfo.get(i), "Cedula", tableInfo.get(0), newInfo.get(++i));
                }
            }
            else 
                return false;
        } 
        return true;
    }
    
    public String getPersonNumber(String cedula){
        
        String phone = databaseConnection.getRows1Variable("TelefonoPersona", "Cliente", cedula).get(0);
        return phone;
    }

    public String OrganizationToString(ArrayList<ArrayList<String>> data) {
        String result = "";
        
        for(int i = 0 ; i < data.size() ; i++){
            for(int j = 0 ; j < data.get(i).size()-1 ; j++){
                result += data.get(i).get(j);
                
                if(j == 0)
                    result += "\t";
                else
                    result += "\t\t";
            }
            result+= getOrgNumber(data.get(i).get(0)) + "\n";
        }
        return result;
    }

    private String getOrgNumber(String cedula) {
        ArrayList<String> data = databaseConnection.getRows1Variable("Contacto", "Organizacion", cedula);
        String contact = "";
        if(!data.isEmpty())
            contact = data.get(0)+","+data.get(2)+","+data.get(1);
        return contact;
    }
    
    public String getClientID(String cedula){
        if(cedula.length() == 9){
            return databaseConnection.getRows1Variable("Persona", "Cedula", cedula).get(4);
        }
        return databaseConnection.getRows1Variable("Organización", "Cedula", cedula).get(4);
    }

    public ArrayList<ArrayList<String>> formatClients(ArrayList<ArrayList<String>> personas, ArrayList<ArrayList<String>> organizaciones) {
        ArrayList<ArrayList<String>> clientes = new ArrayList<>();
        
        for(int i = 0 ; i < personas.size(); i++){
            ArrayList<String> persona = personas.get(i);
            persona.set(4, getPersonNumber(persona.get(0)));
            clientes.add(persona);
        }
        
        for(int i = 0 ; i < organizaciones.size(); i++){
            ArrayList<String> organizacion = organizaciones.get(i);
            organizacion.set(4, getOrgNumber(organizacion.get(0)));
            clientes.add(organizacion);
        }
        
        return clientes;
    }

}
