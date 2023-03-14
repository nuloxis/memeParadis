/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadisback.Configuration;

/**
 *
 * @author krist
 */
public class Database {
     private static final String persistenceUnitName = "my_persistence_unit";
    public static String  getPuName(){
        return Database.persistenceUnitName;
    }
}
