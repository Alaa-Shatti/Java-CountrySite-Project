/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Util.ConnectionManager;
import java.sql.Connection;

/**
 *
 * @author ms
 */
public class AbstractDao {

    private ConnectionManager connectionManager;
    private Connection connection;

    public ConnectionManager getConnectionManager() {
        if (this.connectionManager == null) {
            this.connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public Connection getConnection() {
        if (this.connection == null) {
            this.connection = this.getConnectionManager().connect();
        }
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
   

}
