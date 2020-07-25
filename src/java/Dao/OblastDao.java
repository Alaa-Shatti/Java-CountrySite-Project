/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Oblast;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ms
 */
public class OblastDao extends AbstractDao implements Dao<Oblast> {

    private CountryDao countryDao;

    @Override
    public List<Oblast> findAll() {

        List<Oblast> obList = new ArrayList<>();

        try {
            String selectQuery = "select * from oblast order by ob_id";
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();
            // Statement st = connection.createStatement();
            //ResultSet rs = st.executeQuery("select * from oblast");
            while (rs.next()) {
                Oblast tmp = new Oblast();
                tmp.setOb_id(rs.getLong("ob_id"));
                tmp.setOb_name(rs.getString("ob_name"));
                tmp.setOb_population(rs.getInt("ob_population"));
                tmp.setCountry(this.getCountryDao().find(rs.getLong("coun_id")));
                obList.add(tmp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OblastDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obList;

    }

    @Override
    public void create(Oblast Entity) {

        try {
            String insertQuery = "insert into oblast values (?,?,?)";
            PreparedStatement pst = this.getConnection().prepareStatement(insertQuery);
            pst.setString(1, Entity.getOb_name());
            pst.setInt(2, Entity.getOb_population());
            pst.setLong(3, Entity.getCountry().getCoun_id());
            pst.executeUpdate();
            //Statement st = connection.createStatement();
            //st.executeUpdate("insert into county values ('"+county.getCounty_name()+"',"+county.getCounty_population()+","+selectedOblast+")");

            Long coun_id = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                coun_id = gk.getLong(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void update(Oblast Entity) {

        try {
            String insertQuery = "update oblast set ob_name = ? ,ob_population = ? , coun_id = ? where ob_id = ?";
            PreparedStatement pst = this.getConnection().prepareStatement(insertQuery);
            pst.setString(1, Entity.getOb_name());
            pst.setInt(2, Entity.getOb_population());
            pst.setLong(3, Entity.getCountry().getCoun_id());
            pst.setLong(4, Entity.getOb_id());
            pst.executeUpdate();
            //Statement st = connection.createStatement();
            //st.executeUpdate("insert into county values ('"+county.getCounty_name()+"',"+county.getCounty_population()+","+selectedOblast+")");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void delete(Oblast Entity) {

        try {
            String deletQuery = "delete from oblast where ob_id = ? ";
            PreparedStatement pst = this.getConnection().prepareStatement(deletQuery);
            pst.setLong(1, Entity.getOb_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public Oblast find(Long id) {

        Oblast ob = null;
        try {
            String selectQuery = "select * from oblast where ob_id = ?";
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            ob = new Oblast();
            ob.setCountry(this.getCountryDao().find(rs.getLong("coun_id")));
            ob.setOb_id(rs.getLong("ob_id"));
            ob.setOb_name(rs.getString("ob_name"));
            ob.setOb_population(rs.getInt("ob_population"));

        } catch (SQLException ex) {
            Logger.getLogger(OblastDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ob;

    }

    @Override
    public List<Oblast> findAll(int page, int pageSize) {

        List<Oblast> obList = new ArrayList<>();

        int start = (page - 1) * pageSize;

        try {
            String selectQuery = "select * from oblast order by ob_id limit" + pageSize + " offset " + start;
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Oblast tmp = new Oblast();
                tmp.setCountry(this.getCountryDao().find(rs.getLong("coun_id")));
                tmp.setOb_id(rs.getLong("ob_id"));
                tmp.setOb_name(rs.getString("ob_name"));
                tmp.setOb_population(rs.getInt("ob_population"));
                tmp.setCountry(this.getCountryDao().find(rs.getLong("coun_id")));
                obList.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return obList;

    }

    public int record() {

        int a = 0;
        try {
            String selectQuery = "select * from oblast";
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                a++;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return a;
    }

    public CountryDao getCountryDao() {
        if (this.countryDao == null) {
            this.countryDao = new CountryDao();
        }
        return countryDao;
    }

}
