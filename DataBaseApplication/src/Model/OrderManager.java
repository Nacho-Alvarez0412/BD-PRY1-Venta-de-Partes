/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author sebasgamboa
 */
public class OrderManager {
    
    private ConnectionManager databaseConnection;

    public OrderManager(ConnectionManager aThis) {
        this.databaseConnection = aThis;
    }
    
    private String getPartID(String partName){
        ArrayList<String> row = databaseConnection.getRows1Variable("Parte", "NombreParte", partName);
        return row.get(0);
    }
    
    private ArrayList<String> getProvidersID(String partID){
        ArrayList<String> row = databaseConnection.getRows1Variable("Provision", "ParteID", partID);
        ArrayList<String> output = new ArrayList<String>();
        for (int i = 0; i < row.size()/4; i++) {
            output.add(row.get(i*4));
        }
        return output;
    }
    
    public void listProvidersByPart(String partName){
        
        String partID = this.getPartID(partName);
        
        ArrayList<String> providersID = getProvidersID(partID);
        
        for (String provider:providersID){
            
            ArrayList<String> output = databaseConnection.getRows1Variable("Proveedor", "ProveedorID", provider);
            
            for (int i = 0; i < output.size(); i++) {
            
            System.out.print(output.get(i) + "\t");
            
            }
            System.out.println(" ");
        }
    }
    
    public void insertOrder(String client, String date, String montoVenta,String montoIVA){
        
        ArrayList<String> values = new ArrayList<String>();
        
        values.add(montoVenta);
        values.add(montoIVA);
        values.add(date);
        
        ArrayList<String> rowInfo;
        
        if(client.length() == 9){
            rowInfo = databaseConnection.getRows1Variable("Persona", "Cedula", client);
        }
        else{
            rowInfo = databaseConnection.getRows1Variable("Organización", "Cedula", client);
        }
        
        values.add(rowInfo.get(4));
        
        databaseConnection.insertRow(values, "Orden");
    }
    
    private String getProviderID(String providerName){
        ArrayList<String> row = databaseConnection.getRows1Variable("Proveedor", "Nombre", providerName);
        return row.get(1);
    }
    
    private String getOrderID(String date, String clientID){
        ArrayList<String> row = databaseConnection.getRows2Variables("Orden", "Fecha", date, "ClienteID",clientID);
        return row.get(0);
    }
    
    private String getClientID(String client){
        
        ArrayList<String> rowInfo;
        
        if(client.length() == 9){
            rowInfo = databaseConnection.getRows1Variable("Persona", "Cedula", client);
        }
        else{
            rowInfo = databaseConnection.getRows1Variable("Organización", "Cedula", client);
        }
        return rowInfo.get(4);
    }
    
    public void linkDetailToOrder(String client, String date, String amount, String partName, String providerName){
        
        String partID = this.getPartID(partName);
        String providerID = this.getProviderID(providerName);
        String clientID = this.getClientID(client);
        String orderID = this.getOrderID(date, clientID);
        
        ArrayList<String> values = new ArrayList<String>();
        
        values.add(amount);
        values.add(orderID);
        values.add(partID);
        values.add(providerID);
        
        databaseConnection.insertRow(values, "Detalle");
    }
    
}
