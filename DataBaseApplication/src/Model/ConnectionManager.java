/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.sql.DatabaseMetaData;

/**
 *
 * @author sebasgamboa
 */
public class ConnectionManager {
    
    private Connection connection;
    public static String url;
    public static String user;
    public static String password;
    public ErrorManager errorManager;
    
    
    public ConnectionManager(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
        this.errorManager = new ErrorManager(this);
    }
    
    public ArrayList<String> getDatabaseMetaData() throws SQLException
    {
        ArrayList<String> tables = new ArrayList<>();
        DatabaseMetaData md = connection.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null);
        int cont = 1;
        while (cont <= 14 && rs.next()) {
            tables.add(rs.getString(3));
            cont++;
        }
        return tables;
    }
    
    public void connect(){
        try {
            
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to Microsoft SQL Server");
            errorManager.init();
            
        } catch (SQLException ex) {
            System.out.println(errorManager.handleError(EnumTag.ConnectionAttempt,null));
        }    
    }
    
    public void disconnect() throws SQLException{
        connection.close();
    }
    
    public String getTable(String table){
        ArrayList<String> parameters = new ArrayList<String>();
        parameters.add(table);
        String integrityCheck = errorManager.handleError(EnumTag.GetTable,parameters);
        if(integrityCheck.equals("")){
        
            try {

                Statement sqlStatement = connection.createStatement();

                ResultSet rs = null;

                String queryString = "";
                queryString+="SELECT * FROM "+table;

                System.out.println("\nQuery string:");
                System.out.println(queryString);

                rs=sqlStatement.executeQuery(queryString);
                ResultSetMetaData rsmd = rs.getMetaData();

                int columnsNumber = rsmd.getColumnCount();       

                while (rs.next()) {

                    for(int i = 1 ; i <= columnsNumber; i++){

                        System.out.print(rs.getString(i) + " ");
                    }
                    System.out.println();
                }

                rs.close();

                return table;

            } catch (SQLException ex) {
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            System.err.println(integrityCheck);

        return null;
    }
    
    public String deleteRow(String row, String column, String table){
        
        try {
            Statement sqlStatement = connection.createStatement();
            
            String queryString = "";
            queryString+="DELETE FROM "+table+ " WHERE "+column+" = "+"'"+row+"'"+ ";";
            
            System.out.println("\nQuery string:");
            System.out.println(queryString);
            
            sqlStatement.execute(queryString);
            
            return "Row deleted from "+ table;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void insertRow(ArrayList<String> values, String table){
        
        try {
            Statement sqlStatement = connection.createStatement();
            
            String queryString = "";
            queryString+="INSERT INTO "+table+" Values (";
            
            
            
            for (int i = 0; i < values.size(); i++) {
                
                queryString+=values.get(i);
                
                if(i != values.size()-1){
                   queryString+=" ,";
                }
            }
            
           queryString+=")"; 
            
            System.out.println("\nQuery string:");
            System.out.println(queryString);
            
            sqlStatement.execute(queryString);
            
            System.out.println("Row added in table: "+table);
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
