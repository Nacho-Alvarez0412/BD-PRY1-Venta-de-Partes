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
    
    public ArrayList<String> listPartsByProvider(String providerID){
        
            ArrayList<String> partsID = getPartsID(providerID);
            ArrayList<String> partes = new ArrayList<>();

            for (String parte:partsID){
                ArrayList<String> output = databaseConnection.getRows1Variable("Provision", "ParteID", parte);
                partes.add(output.get(1));

                
            }
            return partes;
    }
    
    public ArrayList<String> listProvidersByPart(String partName){
        if(!databaseConnection.errorManager.isPart(partName)){
            String partID = this.getPartID(partName);

            ArrayList<String> providersID = getProvidersID(partID);
            ArrayList<String> providers = new ArrayList<>();

            for (String provider:providersID){

                ArrayList<String> output = databaseConnection.getRows1Variable("Proveedor", "ProveedorID", provider);
       
                providers.add(output.get(0));

                
            }
            return providers;
        }

        return null;
    }
    
    public boolean insertOrder(String client, String date){
        if(!databaseConnection.errorManager.isClient(client)){
            ArrayList<String> values = new ArrayList<String>();

            values.add("0");
            values.add("0");
            values.add(date);

            ArrayList<String> rowInfo;

            if(client.length() == 9){
                rowInfo = databaseConnection.getRows1Variable("Persona", "Cedula", client);
            }
            else{
                rowInfo = databaseConnection.getRows1Variable("Organización", "Cedula", client);
            }

            values.add(rowInfo.get(4));

            return !(databaseConnection.insertRow(values, "Orden") == 0);
            
        }
        return false;
    }
    
    public String getProviderID(String providerName){
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
    
    public boolean linkDetailToOrder(String client, String orderId, String amount, String partName, String providerName){
        if(!databaseConnection.errorManager.isClient(client) && !databaseConnection.errorManager.isPart(partName) && !databaseConnection.errorManager.isProvider(providerName)){
            String clientID = this.getClientID(client);
            String partID = this.getPartID(partName);
            String providerID = this.getProviderID(providerName);
            
            if(!databaseConnection.errorManager.isOrder(clientID, orderId)){
            
                ArrayList<String> values = new ArrayList<String>();

                values.add(amount);
                values.add(orderId);
                values.add(partID);
                values.add(providerID);
                String total =String.valueOf(getTotalAmount( partName, providerName, amount,providerID,partID));
                databaseConnection.insertRow(values, "Detalle");
                updateOrderPrices(orderId,total);
                return true;
                
            }
            else
                return false;
        }
        return false;
    }
    
    public double getTotalAmount(String partName,String providerName, String amount,String providerID,String partID){
        ArrayList<String> provisionInfo = databaseConnection.getRows2Variables("Provision", "ProveedorID", providerID, "ParteID",partID);
        double sellPrice = Double.valueOf( provisionInfo.get(2));
        sellPrice += Double.valueOf(provisionInfo.get(3));
        return Integer.valueOf(amount) * sellPrice;
    }
    
    public void updateOrderPrices(String orderID,String montoVenta){
        ArrayList<String> order = databaseConnection.getRows1Variable("Orden", "OrdenID", orderID);
        double newMontoVenta = Double.valueOf(montoVenta) + Double.valueOf(order.get(1));
        double newMontoIVA = newMontoVenta*0.13;
        databaseConnection.updateRow("Orden", "MontoVenta", "OrdenID", orderID, String.valueOf(newMontoVenta));
        databaseConnection.updateRow("Orden", "MontoIVA", "OrdenID", orderID, String.valueOf(newMontoIVA));
    }

    private ArrayList<String> getPartsID(String providerID) {
        ArrayList<String> row = databaseConnection.getRows1Variable("Provision", "ProveedorID", providerID);
        ArrayList<String> output = new ArrayList<String>();
        for (int i = 1; i < row.size(); i+=4) {
            output.add(row.get(i));
        }
        return output;
    }
    
}
