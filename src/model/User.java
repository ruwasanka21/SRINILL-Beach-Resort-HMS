/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ASUS
 */
public class User {

    private int userId;
    private String username;
    private String password;
    private int userStatus;
    private String userLastUpdateTime;

    public User() {
    }

    public User(int userId, String username, String password, int userStatus, String userLastUpdateTime) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userStatus = userStatus;
        this.userLastUpdateTime = userLastUpdateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserLastUpdateTime() {
        return userLastUpdateTime;
    }

    public void setUserLastUpdateTime(String userLastUpdateTime) {
        this.userLastUpdateTime = userLastUpdateTime;
    }
}

