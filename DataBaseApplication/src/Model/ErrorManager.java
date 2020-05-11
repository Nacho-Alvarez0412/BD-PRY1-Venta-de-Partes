/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nacho
 */
public class ErrorManager {
    public ConnectionManager dataBase;
    
    
    public ErrorManager(ConnectionManager database){
        this.dataBase = database;
    }
    
    public boolean checkClientIntegrity(String clientID){
        ArrayList<String> check;
        if(clientID.length() == 9){
            check = dataBase.getRows1Variable("Persona", "Cedula", clientID);
        }
        else{
            
            check = dataBase.getRows1Variable("Organización", "Cedula", clientID);
            
            return check.isEmpty();
        }
        
        return check.isEmpty();
    }
    
    public boolean checkOrgIntegrity(String orgName){
        ArrayList<String> check = dataBase.getRows1Variable("Organización", "Nombre", orgName);
        return check.isEmpty();
    }
    
    public boolean checkContactIntegrity(String orgName){
        ArrayList<String> check = dataBase.getRows1Variable("Contacto", "Organizacion", dataBase.linkOrgName2ID(orgName));
        return check.isEmpty();
    }
    
    public boolean checkPartIntegrity(String Marca, String Fabricante){
        ArrayList<String> marca = dataBase.getRows1Variable("MarcaParte", "Marca", Marca);
        ArrayList<String> fabricante = dataBase.getRows1Variable("FabricantePartes", "NombreFabricante", Fabricante);
        
        return !marca.isEmpty() && !fabricante.isEmpty();
    }

    boolean checkPartExistence(String partName) {
        ArrayList<String> parte = dataBase.getRows1Variable("Parte", "NombreParte", partName);
        return parte.isEmpty();
    }
    
    boolean checkPartDependencies(String partName) {
        ArrayList<String> parte = dataBase.getRows1Variable("Parte", "NombreParte", "'"+partName+"'");
        if(!parte.isEmpty()){
            String partID = parte.get(0);
            ArrayList<String> orden = dataBase.getRows1Variable("Detalle", "ParteID", partID);
        
        return orden.isEmpty();
        }
        return true;
    }
    
    boolean checkProvisionIntegrity(String nombreProveedor,String nombreParte){
        ArrayList<String> proveedor = dataBase.getRows1Variable("Proveedor", "Nombre", nombreProveedor);
        ArrayList<String> parte = dataBase.getRows1Variable("Parte", "NombreParte", nombreParte);
        
        return proveedor.isEmpty() && parte.isEmpty();
    }
    
    boolean checkComposeIntegrity(String modelo,String año, String nombreParte){
        ArrayList<String> car = dataBase.getRows2Variables("Automóvil", "Año", "'"+año+"'", "Modelo", modelo);
        ArrayList<String> part = dataBase.getRows1Variable("Parte","NombreParte",nombreParte);
        
        return car.isEmpty() || part.isEmpty();
    }
    
    boolean checkUpdateIntegrity(String nombreProveedor,String nombreParte){
        ArrayList<String> part = dataBase.getRows1Variable("Parte","NombreParte",nombreParte);
        ArrayList<String> provider = dataBase.getRows1Variable("Proveedor","Nombre",nombreProveedor);
        
        return provider.isEmpty() || part.isEmpty();
    }
    
    boolean isCar(String modelo, String año){
        ArrayList<String> car = dataBase.getRows2Variables("Automóvil", "Año", año, "Modelo", modelo);
        
        return car.isEmpty();
    }
    
    boolean isPart(String nombreParte){
        ArrayList<String> part = dataBase.getRows1Variable("Parte", "NombreParte", nombreParte);
        
        return part.isEmpty();
    }
    
    boolean isClient(String cedula){
            ArrayList<String> client;
        if(cedula.length() == 9)
            client = dataBase.getRows1Variable("Persona", "Cedula", cedula);
        else
            client = dataBase.getRows1Variable("Organización", "Cedula", cedula);
        
        return client.isEmpty();
    }
    
    boolean isProvider(String nombreProveedor){
        ArrayList<String> part = dataBase.getRows1Variable("Proveedor", "Nombre", nombreProveedor);
        
        return part.isEmpty();
    }
    
    boolean isOrder(String clientID,String orderDate){
        ArrayList<String> order = dataBase.getRows2Variables("Orden", "ClienteID", clientID, "Fecha", orderDate);
        
        return order.isEmpty();
    }

}
