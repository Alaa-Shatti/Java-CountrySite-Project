/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Poi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ms
 */
public class PoiDao extends AbstractDao implements Dao<Poi> {

    /* public static void main (String args []){
        PoiDao pd = new PoiDao();
        pd.findAll();
    }*/
    private OblastDao oblastDao;

    

    @Override
    public List<Poi> findAll() {

        List<Poi> poiList = new ArrayList<>();

        try {
            String selectQuery = "select * from poi order by poi_id";
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Poi tmp = new Poi();
                //tmp.setOb_id(rs.getLong("ob_id"));
                tmp.setPoi_id(rs.getLong("poi_id"));
                tmp.setPoi_name(rs.getString("poi_name"));
                tmp.setPoi_tel(rs.getLong("poi_tel"));
                tmp.setPoi_email(rs.getString("poi_email"));
                tmp.setPoi_aderss(rs.getString("poi_address"));
                //rs.getInt("ob_id");
                tmp.setOblast(this.getOblastDao().find(rs.getLong("ob_id")));
                poiList.add(tmp);
                //System.out.println(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return poiList;

    }

    @Override
    public void create(Poi Entity) {

        try {
            String insertQuery = "insert into poi values (?,?,?,?,?)";
            PreparedStatement pst = this.getConnection().prepareStatement(insertQuery);
            pst.setString(1, Entity.getPoi_name());
            pst.setLong(2, Entity.getPoi_tel());
            pst.setString(3, Entity.getPoi_email());
            pst.setString(4, Entity.getPoi_aderss());
            pst.setLong(5, Entity.getOblast().getOb_id());
            pst.executeUpdate();
            //Statement st = connection.createStatement();
            //st.executeUpdate("insert into county values ('"+county.getCounty_name()+"',"+county.getCounty_population()+","+selectedOblast+")");


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void update(Poi Entity) {

        try {
            String insertQuery = "update poi set poi_name = ? ,poi_tel = ? ,poi_email = ?,poi_address = ?, ob_id = ? where poi_id = ?";
            PreparedStatement pst = this.getConnection().prepareStatement(insertQuery);
            pst.setString(1, Entity.getPoi_name());
            pst.setLong(2, Entity.getPoi_tel());
            pst.setString(3, Entity.getPoi_email());
            pst.setString(4, Entity.getPoi_aderss());
            pst.setLong(5, Entity.getOblast().getOb_id());
            pst.setLong(6, Entity.getPoi_id());
            pst.executeUpdate();
            //Statement st = connection.createStatement();
            //st.executeUpdate("insert into county values ('"+county.getCounty_name()+"',"+county.getCounty_population()+","+selectedOblast+")");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void delete(Poi Entity) {

        try {
            String deletQuery = "delete from poi where poi_id = ? ";
            PreparedStatement pst = this.getConnection().prepareStatement(deletQuery);
            pst.setLong(1, Entity.getPoi_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public Poi find(Long id) {

        Poi p = null;

        try {
            String selectQuery = "select * from poi where poi_id = ?";
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();

            rs.next();
            p = new Poi();
            //p.setOb_id(rs.getLong("ob_id"));
            p.setPoi_id(rs.getLong("poi_id"));
            p.setPoi_name(rs.getString("poi_name"));
            p.setPoi_tel(rs.getLong("poi_tel"));
            p.setPoi_email(rs.getString("poi_email"));
            p.setPoi_aderss(rs.getString("poi_address"));
            p.setOblast(this.getOblastDao().find(rs.getLong("ob_id")));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;

    }
    
    public OblastDao getOblastDao() {
        if (this.oblastDao == null) {
            oblastDao = new OblastDao();
        }
        return oblastDao;
    }

    @Override
    public List<Poi> findAll(int page, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int record() {

        int a = 0;
        try {
            String selectQuery = "select * from poi";
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
