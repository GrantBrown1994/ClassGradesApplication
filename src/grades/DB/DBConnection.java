/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grades.DB;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
/**
 *
 * @author grant
 * this class gives connection to the database
 * so you can access or modify the data
 */

public class DBConnection {
    
    public static Connection connection;
    public static Connection getDBConnection()throws SQLException{

        if(connection != null){
            return connection;
        }else{
        try{
        String url = "jdbc:mysql://localhost:3306/grades";
        String username = "grades_user";
        String password = "Grant";
        connection = DriverManager.getConnection(url, username, password);
        }catch(SQLException e){
            System.err.println(e);
        }
        return connection;
    }}
    public static synchronized void closeDBConnection(){
       if(connection != null){
        try{
        connection.close();
        }catch(SQLException e){
            System.err.println(e);
        }finally{
        connection = null;
        }
    }
  }
    
}