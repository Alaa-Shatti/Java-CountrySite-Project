/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ms
 */
public class ConnectionManager {

    public Connection connect() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            String DriverName = "jdbc:postgresql://localhost:5432/webSite";
            String UserName = "postgres";
            String Password = "sifre";
            c = DriverManager.getConnection(DriverName, UserName, Password);
            System.out.println("Basarili");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Basarisiz");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println(ex.getMessage());
        }

        return c;
    }

}
