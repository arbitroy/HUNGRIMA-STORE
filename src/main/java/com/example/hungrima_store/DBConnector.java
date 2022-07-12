package com.example.hungrima_store;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    static Connection conn = null;
    public static Connection conDB()
    {
        try {

            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/HUNGRIMA", "postgres", "1234");
        } catch ( Exception e ) {
            e.printStackTrace();
            return null;
        }
        return conn;
    }
}
