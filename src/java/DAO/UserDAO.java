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
        String sql = "INSERT INTO users (name, email,userName, password, avatar) VALUES (?, ?,?,?,?);";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getAvatar());
            statement.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
    
    public User checkLogin(User user) {
        try {
            User userReturn = null;
            String sql = "Select * from users where userName = ? and password = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String userName = rs.getString(4);
                String password = rs.getString(5);
                String avatar = rs.getString(6);
                userReturn = new User(id, name, email, userName, password, avatar);
            }
            return userReturn;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

     public User getUserById(int id) {
        try {
            String sql = "Select * from users where user_id = ? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            User user = null ;
            while (rs.next()) {                
                String name = rs.getString(2);
                String email = rs.getString(3);
                String avatar = rs.getString(6);
                user = new User(id,name, email, avatar);
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
             return null;
        }
    }
     
    public boolean updateUser(User user) {
        try {
            String sql = "update users set avatar = ?, name = ? where user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getAvatar());
            ps.setString(2, user.getName());
            ps.setInt(3, user.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
}

