/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class User {

    private int id;
    private String name, email, userName,password,avatar;

    public User(String name, String email, String userName, String password, String avatar) {
        this.name = name;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.avatar = avatar;
    }
    
    public User(int id,String name, String email, String userName, String password, String avatar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.avatar = avatar;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String imgLink) {
        this.avatar = imgLink;
    }

    
    public User(int id, String name, String email, String avatar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", userName=" + userName + ", password=" + password + ", avatar=" + avatar + '}';
    }

    

    
}
