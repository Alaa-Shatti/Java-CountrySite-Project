/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.PoiType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ms
 */
public class PoiTypeDao extends AbstractDao implements Dao<PoiType> {

    /*  public static void main (String args []){
        PoiTypeDao pt = new PoiTypeDao();
        pt.findAll();
    }*/
    private PoiDao poiDao;

    @Override
    public List<PoiType> findAll() {

        List<PoiType> PTList = new ArrayList<>();

        try {
            String selectQuery = "select * from poi_type order by ptype_id  ";
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                PoiType tmp = new PoiType();
               // tmp.setPoi_id(rs.getLong("poi_id"));
                tmp.setPtype_id(rs.getLong("ptype_id"));
                tmp.setTouristAttractions(rs.getString("tourist_attractions"));
                tmp.setNaturalBeauties(rs.getString("natural_beauties"));
                tmp.setRestaurant(rs.getString("restaurant"));
                tmp.setLayover(rs.getString("layover"));
                tmp.setPoi(this.getPoiDao().find(rs.getLong("poi_id")));

                PTList.add(tmp);
                // System.out.println(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return PTList;

    }

    @Override
    public void create(PoiType Entity) {

        try {
            String insertQuery = "insert into poi_type values (?,?,?,?,?)";
            PreparedStatement pst = this.getConnection().prepareStatement(insertQuery);
            pst.setString(1, Entity.getTouristAttractions());
            pst.setString(2, Entity.getNaturalBeauties());
            pst.setString(3, Entity.getRestaurant());
            pst.setString(4, Entity.getLayover());
            pst.setLong(5, Entity.getPoi().getPoi_id());
            pst.executeUpdate();

           

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void update(PoiType Entity) {

        try {
            String updateQuery = "update poi_type set tourist_attractions = ? ,natural_beauties = ? ,"
                    + "restaurant = ? , layover = ? , poi_id = ? where ptype_id = ? ";
            PreparedStatement pst = this.getConnection().prepareStatement(updateQuery);
            pst.setString(1, Entity.getTouristAttractions());
            pst.setString(2, Entity.getNaturalBeauties());
            pst.setString(3, Entity.getRestaurant());
            pst.setString(4, Entity.getLayover());
            pst.setLong(5, Entity.getPoi().getPoi_id());
            pst.setLong(6, Entity.getPtype_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void delete(PoiType Entity) {

        try {
            String deleteQuery = "delete from poi_type where ptype_id = ? ";
            PreparedStatement pst = this.getConnection().prepareStatement(deleteQuery);
            pst.setLong(1, Entity.getPtype_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public PoiType find(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PoiType> findAll(int page, int pageSize) {

        List<PoiType> PTList = new ArrayList<>();

        int start = (page - 1) * pageSize;
        try {
            String selectQuery = "select * from poi_type order by ptype_id limit " + pageSize + " offset " + start;
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                PoiType tmp = new PoiType();
                //tmp.setPoi_id(rs.getLong("poi_id"));
                tmp.setPtype_id(rs.getLong("ptype_id"));
                tmp.setTouristAttractions(rs.getString("tourist_attractions"));
                tmp.setNaturalBeauties(rs.getString("natural_beauties"));
                tmp.setRestaurant(rs.getString("restaurant"));
                tmp.setLayover(rs.getString("layover"));
                tmp.setPoi(this.getPoiDao().find(rs.getLong("poi_id")));

                PTList.add(tmp);
                // System.out.println(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return PTList;

    }

    public int record() {

        int a = 0;
        try {
            String selectQuery = "select * from poi_type";
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

    public PoiDao getPoiDao() {
        if (this.poiDao == null) {
            this.poiDao = new PoiDao();
        }
        return poiDao;
    }
}
