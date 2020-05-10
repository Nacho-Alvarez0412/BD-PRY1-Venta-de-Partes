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
public class PartManager {
    
    private ConnectionManager databaseConnection;

    public PartManager(ConnectionManager aThis) {
        this.databaseConnection = aThis;
    }
    
    private String getBrandID(String brandName){
        ArrayList<String> row = databaseConnection.getRows1Variable("MarcaParte", "Marca", brandName);
        return row.get(0);
    }
    
    private String getProducerID(String producerName){
        ArrayList<String> row = databaseConnection.getRows1Variable("FabricantePartes", "NombreFabricante", producerName);
        return row.get(0);
    }
    
    public void insertPart(String partName, String nombreFabricante, String nombreMarca){
        
        ArrayList<String> values = new ArrayList<String>();
        
        values.add(partName);
        values.add(this.getProducerID(nombreFabricante));
        values.add(this.getBrandID(nombreMarca));
        
        databaseConnection.insertRow(values, "Parte");
    }
    
    public void erasePart(String partName){
        
        databaseConnection.deleteRow(partName, "NombreParte", "Parte");
        System.out.println("Part "+partName+" deleted");
    }
    
    
    private String getProviderID(String providerName){
        ArrayList<String> row = databaseConnection.getRows1Variable("Proveedor", "Nombre", providerName);
        return row.get(1);
    }
    
    private String getPartID(String partName){
        ArrayList<String> row = databaseConnection.getRows1Variable("Parte", "NombreParte", partName);
        return row.get(0);
    }
    
    public void insertProvision(String nombreProveedor,String nombreParte,String precioCompra,String ganancia){
        
        ArrayList<String> values = new ArrayList<String>();
        
        values.add(this.getProviderID(nombreProveedor));
        values.add(this.getPartID(nombreParte));
        values.add(precioCompra);
        values.add(ganancia);
        
        databaseConnection.insertRow(values, "Provision");
    }
    
    private String getVehicleID(String modelo, String año){
        ArrayList<String> row = databaseConnection.getRows2Variables("Automóvil", "Modelo", modelo,"Año",año);
        return row.get(0);
    }
    
    public void linkPartWithVehicle(String nombreParte,String modelo,String año){
        
        ArrayList<String> values = new ArrayList<String>();
        
        values.add(this.getVehicleID(modelo, año));
        values.add(this.getPartID(nombreParte));
        
        databaseConnection.insertRow(values, "Compone");
    }
    
    public void updatePrices(String nombreProveedor,String nombreParte,String precioCompra,String ganancia){
        
        String providerID = this.getProviderID(nombreProveedor);
        String partID = this.getPartID(nombreParte);
        
        databaseConnection.updateRow2Variables("Provision", "PrecioCompra", precioCompra,"ProveedorID", providerID, "ParteID", partID);
        databaseConnection.updateRow2Variables("Provision", "Ganancia", ganancia,"ProveedorID", providerID, "ParteID", partID);
    }
    
    private String getPartIdByVehicle(String vehicleID){
        ArrayList<String> row = databaseConnection.getRows1Variable("Compone", "AutomovilID", vehicleID);
        return row.get(1);
    }
    
    public void listPartsByVehicle(String modelo, String año){
        
        String vehicleID = this.getVehicleID(modelo, año);
        
        String partID = this.getPartIdByVehicle(vehicleID);
        
        ArrayList<String> parts = databaseConnection.getRows1Variable("Parte", "ParteID", partID);
        
        int counter = 0;
        
        for (int i = 0; i < parts.size(); i++) {
            
            System.out.print(parts.get(i) + "\t");
            counter++;
            
            if (counter == 4){
                System.out.println(" ");
                counter = 0;
            }
        }
        
    }
}
