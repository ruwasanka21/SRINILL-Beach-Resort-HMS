/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import control.DatabaseLayer;

/**
 *
 * @author ASUS
 */
public class AppConfig {

    private DatabaseConfig  database;

    public DatabaseConfig  getDatabase() { return database; }
    public void setDatabase(DatabaseConfig  database) { this.database = database; }
}

