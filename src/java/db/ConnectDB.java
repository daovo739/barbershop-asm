/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HHPC
 */
public class ConnectDB {
    private String user="sa";
    private String password="123";
        private String url = "jdbc:sqlserver://localhost;trustServerCertificate=true;databaseName=barbershop-asm";

//    private String user = "vvd";
//    private String password = "Vandaone123";
//    String url = "jdbc:sqlserver://barbershop.database.windows.net:1433;database=barbershop;encrypt=true;trustServerCertificate=true;hostNameInCertificate=*.database.windows.net";
    private Connection conn;

    public ConnectDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, user,password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConn() {
        return conn;
    }
    
}
