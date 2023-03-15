/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.memeparadis.Configuration;

/**
 *
 * @author krist
 */
public class Database {
     private static final String persistenceUnitName = "com.mycompany_MemeparadisEE7_war_1.0-SNAPSHOTPU";
    public static String  getPuName(){
        return Database.persistenceUnitName;
    }
}
