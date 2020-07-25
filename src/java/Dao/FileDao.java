/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.EnFile;
import Util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ms
 */
public class FileDao {

    private CountryDao countryDao;

    ConnectionManager cm = new ConnectionManager();
    Connection connect = cm.connect();

    public ArrayList<EnFile> findAll() {
        ArrayList<EnFile> fileList = new ArrayList();

        try {
            String selectQuery = "select * from file";
            PreparedStatement pst = connect.prepareStatement(selectQuery);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                EnFile file = new EnFile();
                //file.setCoun_id(rs.getLong("coun_id"));
                file.setId(rs.getLong("id"));
                file.setName(rs.getString("name"));
                file.setPath(rs.getString("path"));
                file.setType(rs.getString("type"));
                file.setCountry(this.getCountryDao().find(rs.getLong("coun_id")));
                fileList.add(file);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return fileList;

    }

    public void create(EnFile f) {
        try {

            String insertQuery = "insert into file values (? , ? , ? , ? )";
            PreparedStatement pst = connect.prepareStatement(insertQuery);
            pst.setString(1, f.getName());
            pst.setString(2, f.getPath());
            pst.setString(3, f.getType());
            pst.setLong(4, f.getCountry().getCoun_id());
            pst.executeUpdate();

            Long id = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                id = gk.getLong(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(EnFile f) {
        try {

            String deleteQuery = "delete from file where id = ?";
            PreparedStatement pst = this.connect.prepareStatement(deleteQuery);
            pst.setLong(1, f.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public CountryDao getCountryDao() {
        if (this.countryDao == null) {
            this.countryDao = new CountryDao();
        }
        return countryDao;
    }

}
