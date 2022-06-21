/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import db.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Admin
 */
public class UserDAO {

    private Connection conn;

    public UserDAO() {
        conn = new ConnectDB().getConn();
    }

    public boolean registerUser(User user) {
          if (user.getUserName().equalsIgnoreCase("admin")){
            return false;
        }
        String sql = "INSERT INTO users (name, email,userName, password) VALUES (?, ?,?,?);";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getPassword());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean checkLogin(User user) {
        try {
            String sql = "Select * from users where userName = ? and password = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
             return false;
        }
    }
    
    public int getUserIdByUsername(String userName) {
        try {
            String sql = "Select user_id from users where userName = ? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
             return -1;
        }
    }
}

