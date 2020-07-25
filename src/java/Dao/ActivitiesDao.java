/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Activities;
import java.sql.Date;
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
public class ActivitiesDao extends AbstractDao implements Dao<Activities>{
    
    private PoiDao poiDao;

    @Override
    public List<Activities> findAll() {

        List<Activities> activityList = new ArrayList<>();

        try {
            String selectQuery = "select * from activities order by activity_id";
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();
            // Statement st = connection.createStatement();
            //ResultSet rs = st.executeQuery("select * from oblast");
            while (rs.next()) {
                Activities tmp = new Activities();
                tmp.setPoi_id(rs.getLong("poi_id"));
                tmp.setActivity_id(rs.getLong("activity_id"));
                tmp.setActivity_species(rs.getString("activity_species"));
                tmp.setDate(rs.getDate("date"));
                tmp.setPoi(this.getPoiDao().find(rs.getLong("poi_id")));
                activityList.add(tmp);
                
                //System.out.println(tmp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OblastDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return activityList ;

    }

    @Override
    public void create(Activities Entity) {

        try {
            String insertQuery = "insert into activities values (?,?,?)";
            PreparedStatement pst = this.getConnection().prepareStatement(insertQuery);
            pst.setString(1, Entity.getActivity_species());
            Date sqlDate = new Date (Entity.getDate().getTime());
            pst.setDate(2, sqlDate);
            pst.setLong(3, Entity.getPoi().getPoi_id());
            pst.executeUpdate();
            //Statement st = connection.createStatement();
            //st.executeUpdate("insert into county values ('"+county.getCounty_name()+"',"+county.getCounty_population()+","+selectedOblast+")");

            Long poi_id = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                poi_id = gk.getLong(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void update(Activities Entity) {

        try {
            String insertQuery = "update activities set activity_species = ? ,date = ? , poi_id = ? where activity_id = ?";
            PreparedStatement pst = this.getConnection().prepareStatement(insertQuery);
            pst.setString(1, Entity.getActivity_species());
            Date sqlDate = new Date (Entity.getDate().getTime());
            pst.setDate(2, sqlDate);
            pst.setLong(3, Entity.getPoi().getPoi_id());
            pst.setLong(4, Entity.getActivity_id());
            pst.executeUpdate();
            //Statement st = connection.createStatement();
            //st.executeUpdate("insert into county values ('"+county.getCounty_name()+"',"+county.getCounty_population()+","+selectedOblast+")");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void delete(Activities Entity) {

        try {
            String deletQuery = "delete from activities where activity_id = ? ";
            PreparedStatement pst = this.getConnection().prepareStatement(deletQuery);
            pst.setLong(1, Entity.getActivity_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public Activities find(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Activities> findAll(int page, int pageSize) {

         List<Activities> activityList = new ArrayList<>();
         
         int start = (page -1 ) * pageSize ;

        try {
            String selectQuery = "select * from activities order by activity_id limit " +pageSize +" offset " + start;
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();
            // Statement st = connection.createStatement();
            //ResultSet rs = st.executeQuery("select * from oblast");
            while (rs.next()) {
                Activities tmp = new Activities();
                tmp.setPoi_id(rs.getLong("poi_id"));
                tmp.setActivity_id(rs.getLong("activity_id"));
                tmp.setActivity_species(rs.getString("activity_species"));
                tmp.setDate(rs.getDate("date"));
                tmp.setPoi(this.getPoiDao().find(rs.getLong("poi_id")));
                activityList.add(tmp);
                
                //System.out.println(tmp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OblastDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return activityList ;

    }
    
    public PoiDao getPoiDao() {
        if(this.poiDao == null)
            this.poiDao = new PoiDao();
        return poiDao;
    }
    
   /* public static void main (String args []){
        ActivitiesDao AD = new ActivitiesDao();
        AD.findAll();
    }*/

    public int record() {

        int a = 0;
        try {
            String selectQuery = "select * from activities";
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
    
    


    
}
