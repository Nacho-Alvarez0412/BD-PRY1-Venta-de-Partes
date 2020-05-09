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
    private boolean error;
    private ConnectionManager dataBaseConnection;
    public ArrayList<String> tables;

    ErrorManager(ConnectionManager dataBase)  {
        this.dataBaseConnection = dataBase;
        this.tables = tables;
        this.error = false;
    }
    
    public String handleError(EnumTag errorTag, ArrayList<String> parameters){
        
        switch(errorTag){
            
            case ConnectionAttempt:
                
                return "An error has occured while connecting to the Data Base";
                
            case DeleteRow:
                return "";
                
            case EditRow:
                return "";
            
            case GetRow:
                return "";
             
            case GetTable:
                check4Table(parameters.get(0));
                if(error){
                    error = false;
                    return "The table entered doesn´t exist in the actual Database";
                }
                return "";
                
            
            case InsertRow:
                return "";
                    
        }
        return "";
    }

    public void init() {
        try {
            this.tables = dataBaseConnection.getDatabaseMetaData();
        } catch (SQLException ex) {
            Logger.getLogger(ErrorManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void check4Table(String tableName) {
        tableName = tableName.toLowerCase();
        String currentTable = "";
        
        
        for(int i = 0 ; i<tables.size() ; i++){
            currentTable = tables.get(i).toLowerCase();
            if(currentTable.equals(tableName))
                return;
            
        }
        error = true;
    }

}
