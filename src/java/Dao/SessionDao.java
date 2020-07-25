/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Group;
import Util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ms
 */
public class SessionDao {

    private ConnectionManager connectionManager;
    private Connection connection;

    private GroupDao groupDao;

    public boolean getPerm(Group group, String module, String process) {
        try {
            PreparedStatement pst = this.getConnection().prepareStatement("select * from privilies where group_id =? and module =?  and " + process + " =true");

            pst.setLong(1, group.getGroup_id());
            pst.setString(2, module);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                return true;
                // u.setRole_entity(this.getRoleDao().find(rs.getLong("role_id")));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;

        }
        return false;

    }

    public GroupDao getGroupDao() {
        if (this.groupDao == null) {
            this.groupDao = new GroupDao();
        }
        return groupDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

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
