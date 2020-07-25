/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Community;
import Entity.County;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ms
 */
public class CountyDao extends AbstractDao implements Dao<County> {

    private OblastDao oblastDao;

    private CommunityDao communityDao;

    /*public static void main (String args []){
        CountyDao cm = new CountyDao();
        cm.findAll();
    } */
    public List<County> getCommunitycounty(Long id) {

        List<County> commCounty = new ArrayList<>();

        try {

            String selectQuery = " select * from county_comm where comm_id = " + id;
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            //pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                commCounty.add(this.find(rs.getLong("county_id")));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commCounty;
    }

    @Override
    public List<County> findAll() {

        List<County> countyList = new ArrayList<>();

        try {
            String selectQuery = "select * from county ";
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                County tmp = new County();
                //tmp.setOb_id(rs.getLong("ob_id"));
                tmp.setCounty_id(rs.getLong("county_id"));
                tmp.setCounty_name(rs.getString("county_name"));
                tmp.setCounty_population(rs.getInt("county_population"));

                //rs.getInt("county_id");
                tmp.setOblast(this.getOblastDao().find(rs.getLong("ob_id")));

                tmp.setCountyCommunity(this.getCommunityDao().getCountyCommunity(tmp.getCounty_id()));

                countyList.add(tmp);

                //System.out.println(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return countyList;

    }

    @Override
    public void create(County Entity) {

        try {
            String insertQuery = "insert into county values (?,?,?)";
            PreparedStatement pst = this.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, Entity.getCounty_name());
            pst.setInt(2, Entity.getCounty_population());
            pst.setLong(3, Entity.getOblast().getOb_id());
            pst.executeUpdate();
            //Statement st = connection.createStatement();
            //st.executeUpdate("insert into county values ('"+county.getCounty_name()+"',"+county.getCounty_population()+","+selectedOblast+")");

            Long county_id = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                county_id = gk.getLong(4);
            }

            for (Community comm : Entity.getCountyCommunity()) {
                String select = " insert into county_comm (county_id , comm_id) values(?,?) ";
                PreparedStatement pst2 = this.getConnection().prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
                pst2.setLong(1, county_id);
                pst2.setLong(2, comm.getComm_id());
                pst2.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void update(County Entity) {

        try {

            String insertQuery = "update county set county_name = ? ,county_population = ? , ob_id = ? where county_id = ?";
            PreparedStatement pst = this.getConnection().prepareStatement(insertQuery);
            pst.setString(1, Entity.getCounty_name());
            pst.setInt(2, Entity.getCounty_population());
            pst.setLong(3, Entity.getOblast().getOb_id());
            pst.setLong(4, Entity.getCounty_id());
            pst.executeUpdate();
            //Statement st = connection.createStatement();
            //st.executeUpdate("insert into county values ('"+county.getCounty_name()+"',"+county.getCounty_population()+","+selectedOblast+")");

            pst = this.getConnection().prepareStatement("delete from county_comm where county_id = ?");
            pst.setLong(1, Entity.getCounty_id());
            pst.executeUpdate();

            for (Community comm : Entity.getCountyCommunity()) {
                String select = " insert into county_comm (county_id , comm_id) values(?,?) ";
                PreparedStatement pst2 = this.getConnection().prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
                pst2.setLong(1, Entity.getCounty_id());
                pst2.setLong(2, comm.getComm_id());
                pst2.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void delete(County Entity) {

        try {
            String deletQuery = "delete from county where county_id = ? ";
            PreparedStatement pst = this.getConnection().prepareStatement(deletQuery);
            pst.setLong(1, Entity.getCounty_id());
            pst.executeUpdate();

            String deleteQuery2 = "delete from county_comm where county_id = ? ";
            PreparedStatement pst2 = this.getConnection().prepareStatement(deleteQuery2);
            pst2.setLong(1, Entity.getCounty_id());
            pst2.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public County find(Long id) {

        County coun = null;
        try {
            String selectQuery = "select * from county where county_id = " + id;
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            //pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            //Statement st = connection.createStatement();
            //ResultSet rs = st.executeQuery("select * from oblast where ob_id = " + id);            
            rs.next();
            coun = new County();
            coun.setOblast(this.getOblastDao().find(rs.getLong("ob_id")));
            coun.setCounty_id(rs.getLong("county_id"));
            coun.setCounty_name(rs.getString("county_name"));
            coun.setCounty_population(rs.getInt("county_population"));

            coun.setCountyCommunity(this.getCommunityDao().getCountyCommunity(coun.getCounty_id()));

        } catch (SQLException ex) {
            Logger.getLogger(OblastDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return coun;

    }

    @Override
    public List<County> findAll(int page, int pageSize) {

        List<County> countyList = new ArrayList<>();

        int start = (page - 1) * pageSize;

        try {
            String selectQuery = "select * from county order by county_id limit " + pageSize + " offset " + start;
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                County tmp = new County();
                //tmp.setOb_id(rs.getLong("ob_id"));
                tmp.setCounty_id(rs.getLong("county_id"));
                tmp.setCounty_name(rs.getString("county_name"));
                tmp.setCounty_population(rs.getInt("county_population"));
                rs.getInt("ob_id");
                tmp.setOblast(this.getOblastDao().find(rs.getLong("ob_id")));
                countyList.add(tmp);
                // System.out.println(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return countyList;

    }

    public int record() {

        int a = 0;
        try {
            String selectQuery = "select * from county";
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

    /*public static void main (String args []){
        CountyDao Co = new CountyDao();
        Co.findAll();
    }*/
    public OblastDao getOblastDao() {
        if (this.oblastDao == null) {
            oblastDao = new OblastDao();
        }
        return oblastDao;
    }

    public CommunityDao getCommunityDao() {
        if (this.communityDao == null) {
            this.communityDao = new CommunityDao();
        }
        return communityDao;
    }

    public void setCommunityDao(CommunityDao communityDao) {
        this.communityDao = communityDao;
    }

}
