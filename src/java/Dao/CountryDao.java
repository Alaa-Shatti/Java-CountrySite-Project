/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Country;
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
public class CountryDao extends AbstractDao implements Dao <Country>{

    @Override
    public List<Country> findAll() {

        List<Country> LCoun = new ArrayList();

        try {
            String selectQuery = "select * from country order by coun_id";
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Country tmp = new Country();
                tmp.setCoun_id(rs.getLong("coun_id"));
                tmp.setCoun_name(rs.getString("coun_name"));
                tmp.setCoun_population(rs.getInt("Coun_population"));
                LCoun.add(tmp);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            System.out.println("Thanks");
        }
        return LCoun;

    }

    @Override
    public void create(Country Entity) {
        
        try {
            String insertQuery = "insert into country values (?,?)";
            PreparedStatement pst = this.getConnection().prepareStatement(insertQuery);
            pst.setString(1, Entity.getCoun_name());
            pst.setInt(2, Entity.getCoun_population());
            pst.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(CountryDao.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    }

    @Override
    public void update(Country Entity) {

         try {
            String updateQuery = " update country "
                    +"  set coun_name   = ? "
                    +", coun_population = ? "
                    +"where coun_id     = ? ";
            PreparedStatement pst = this.getConnection().prepareStatement(updateQuery);
            pst.setString(1, Entity.getCoun_name());
            pst.setInt(2, Entity.getCoun_population());
            pst.setLong(3, Entity.getCoun_id());
            pst.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(CountryDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Country Entity) {

         try {
             
            String deleteQuery = "delete from country where coun_id = ? ";
            PreparedStatement pst = this.getConnection().prepareStatement(deleteQuery);
            pst.setLong(1, Entity.getCoun_id());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(CountryDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Country find(Long id) {

        Country coun = null;
        try {
            String selectQuery = "select * from country where coun_id = ?";
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            //Statement st = connection.createStatement();
            //ResultSet rs = st.executeQuery("select * from oblast where ob_id = " + id);            
            rs.next();
            coun = new Country();
            coun.setCoun_id(rs.getLong("coun_id"));
            coun.setCoun_name(rs.getString("coun_name"));
            coun.setCoun_population(rs.getInt("coun_population"));

        } catch (SQLException ex) {
            Logger.getLogger(OblastDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return coun;

    }
    
    public int record() {
        
        int a = 0;
        try {
            String selectQuery = "select * from country";
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

    @Override
    public List<Country> findAll(int page, int pageSize) {


        List<Country> countryList = new ArrayList<>();
        
        int start = ( page -1 ) * pageSize ;
        
        try {
            String selectQuery = "select * from country order by coun_id limit "+pageSize+" offset "+start;
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Country tmp = new Country();
                tmp.setCoun_id(rs.getLong("coun_id"));
                tmp.setCoun_name(rs.getString("coun_name"));
                tmp.setCoun_population(rs.getInt("Coun_population"));
                countryList.add(tmp);

                System.out.println(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return countryList ;
        
    }
}
