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
    
    public String insertPart(String partName, String nombreFabricante, String nombreMarca){
        if(!databaseConnection.errorManager.checkPartExistence(partName))
            return "La parte ingresada ya existe en la base de datos";
        if(databaseConnection.errorManager.checkPartIntegrity(nombreMarca, nombreFabricante)){
            ArrayList<String> values = new ArrayList<String>();

            values.add(partName);
            values.add(this.getProducerID(nombreFabricante));
            values.add(this.getBrandID(nombreMarca));

            databaseConnection.insertRow(values, "Parte");
            return "Parte agregada con éxito";
        }
        return "La marca o fabricante no se encuentra en la base de datos";
    }
    
    public String erasePart(String partName){
        if(databaseConnection.errorManager.checkPartDependencies(partName)){
            databaseConnection.deleteRow(partName, "NombreParte", "Parte");
            return "Part "+partName+" deleted";
        }
        else{
            return "La parte no puede ser borrada, ya que forma parte de una orden";
        }
    }
    
    
    private String getProviderID(String providerName){
        ArrayList<String> row = databaseConnection.getRows1Variable("Proveedor", "Nombre", providerName);
        return row.get(1);
    }
    
    private String getPartID(String partName){
        ArrayList<String> row = databaseConnection.getRows1Variable("Parte", "NombreParte", partName);
        return row.get(0);
    }
    
    public String insertProvision(String nombreProveedor,String nombreParte,String precioCompra,String ganancia){
        if(!databaseConnection.errorManager.checkProvisionIntegrity(nombreProveedor, nombreParte)){
            ArrayList<String> values = new ArrayList<String>();

            values.add(this.getProviderID(nombreProveedor));
            values.add(this.getPartID(nombreParte));
            values.add(precioCompra);
            values.add(ganancia);
            databaseConnection.insertRow(values, "Provision");
            return "Relación creada con éxito";
        }
        
        return "Verifique el proveedor o la parte y vuelva a intentar";
    }
    
    private String getVehicleID(String modelo, String año){
        ArrayList<String> row = databaseConnection.getRows2Variables("Automóvil", "Modelo", modelo,"Año",año);
        return row.get(0);
    }
    
    public String linkPartWithVehicle(String nombreParte,String modelo,String año){
        if(!databaseConnection.errorManager.checkComposeIntegrity(modelo, año, nombreParte)){
            ArrayList<String> values = new ArrayList<>();

            values.add(this.getVehicleID(modelo, año));
            values.add(this.getPartID(nombreParte));

            databaseConnection.insertRow(values, "Compone");
            return "Relación creada con éxito";
        }
        return "Verifique los valores de parte y vehículo y vuelva a intentar";
    }
    
    public String updatePrices(String nombreProveedor,String nombreParte,String precioCompra,String ganancia){
        if(!databaseConnection.errorManager.checkUpdateIntegrity(nombreProveedor, nombreParte)){
            String providerID = this.getProviderID(nombreProveedor);
            String partID = this.getPartID(nombreParte);

            databaseConnection.updateRow2Variables("Provision", "PrecioCompra", precioCompra,"ProveedorID", providerID, "ParteID", partID);
            databaseConnection.updateRow2Variables("Provision", "Ganancia", ganancia,"ProveedorID", providerID, "ParteID", partID);
            return "Datos actualizados con éxito";
        }
        return "Verifique los valores de parte y proveedor y vuelva a intentar";
    }
    
    private ArrayList<String> getPartIdByVehicle(String vehicleID){
        ArrayList<String> row = databaseConnection.getRows1Variable("Compone", "AutomovilID", vehicleID);
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0 ; i < row.size() ; i++){
            i++;
            result.add(row.get(i));
        }
        
        return result;
    }
    
    public String listPartsByVehicle(String modelo, String año){
        if(!databaseConnection.errorManager.isCar(modelo, año)){
            String vehicleID = this.getVehicleID(modelo, año);

            ArrayList<String> partsID = this.getPartIdByVehicle(vehicleID);
            ArrayList<String> parts = new ArrayList<>();

            for (int i = 0 ; i < partsID.size() ; i++){
                parts.add(databaseConnection.getRows1Variable("Parte", "ParteID", partsID.get(i)).get(1));
            }

            return parts.toString();
        }
        
        return "El automóvil digitado no existe en la base de datos";
    }
}
