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
    
    public String listProvidersByPart(String partName){
        if(!databaseConnection.errorManager.isPart(partName)){
            String partID = this.getPartID(partName);

            ArrayList<String> providersID = getProvidersID(partID);
            ArrayList<String> providers = new ArrayList<>();

            for (String provider:providersID){

                ArrayList<String> output = databaseConnection.getRows1Variable("Proveedor", "ProveedorID", provider);
       
                providers.add(output.get(0));

                
            }
            return providers.toString();
        }

        return "La parte ingresada no se encuentra en el sistema";
    }
    
    public String insertOrder(String client, String date){
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

            databaseConnection.insertRow(values, "Orden");
            
            return "Orden agregada con éxito";
        }
        return "El cliente ingresado no existe";
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
    
    public String linkDetailToOrder(String client, String date, String amount, String partName, String providerName){
        if(!databaseConnection.errorManager.isClient(client) && !databaseConnection.errorManager.isPart(partName) && !databaseConnection.errorManager.isProvider(providerName)){
            String clientID = this.getClientID(client);
            String partID = this.getPartID(partName);
            String providerID = this.getProviderID(providerName);
            
            if(!databaseConnection.errorManager.isOrder(clientID, date)){
            
                String orderID = this.getOrderID(date, clientID);

                ArrayList<String> values = new ArrayList<String>();

                values.add(amount);
                values.add(orderID);
                values.add(partID);
                values.add(providerID);
                String total =String.valueOf(getTotalAmount( partName, providerName, amount,providerID,partID));
                databaseConnection.insertRow(values, "Detalle");
                updateOrderPrices(orderID,total);
                return "Detalle agregado con éxito a la órden";
                
            }
            else
                return "El cliente seleccionado no posee ordenes";
        }
        return "Alguno de los siguientes datos: Cliente, Parte o Proveedor no se encuentran en la base de datos";
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
    
}
