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

/**
 *
 * @author ms
 */
public class CommunityDao extends AbstractDao implements Dao<Community> {

    /*public static void main(String args[]) {

        CommunityDao com = new CommunityDao();
        com.findAll();
    }*/
    private CountyDao countyDao;

    @Override
    public List<Community> findAll() {

        List<Community> communityList = new ArrayList<>();

        try {
            String selectQuery = "select * from community";
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Community tmp = new Community();
                tmp.setComm_id(rs.getLong("comm_id"));
                tmp.setName(rs.getString("comm_name"));

                rs.getInt("comm_id");
                tmp.setCommunitycounty(this.getCountyDao().getCommunitycounty(tmp.getComm_id()));

                communityList.add(tmp);
                //System.out.println(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return communityList;

    }

    @Override
    public void create(Community Entity) {

        try {
            String insertQuery = "insert into community values (?)";
            PreparedStatement pst = this.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, Entity.getName());
            pst.executeUpdate();
            //Statement st = connection.createStatement();
            //st.executeUpdate("insert into county values ('"+county.getCounty_name()+"',"+county.getCounty_population()+","+selectedOblast+")");

            Long comm_id = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                comm_id = gk.getLong(2);
            }
            for (County coun : Entity.getCommunitycounty()) {
                String select = " insert into county_comm (county_id , comm_id) values(?,?) ";
                PreparedStatement pst2 = this.getConnection().prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
                pst2.setLong(1, coun.getCounty_id());
                pst2.setLong(2, comm_id);
                pst2.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void update(Community Entity) {

        try {
            String insertQuery = "update community set comm_name = ? where comm_id = ?";
            PreparedStatement pst = this.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, Entity.getName());
            pst.setLong(2, Entity.getComm_id());
            pst.executeUpdate();
            

            pst = this.getConnection().prepareStatement("delete from county_comm where comm_id =? ");
            pst.setLong(1, Entity.getComm_id());
            pst.executeUpdate();
            
            
            for (County coun : Entity.getCommunitycounty()) {
                String select = " insert into county_comm (county_id , comm_id) values(?,?)";
                PreparedStatement pst2 = this.getConnection().prepareStatement(select, Statement.RETURN_GENERATED_KEYS);
                pst2.setLong(1, coun.getCounty_id());
                pst2.setLong(2, Entity.getComm_id());
                pst2.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void delete(Community Entity) {

        try {
            String deletQuery = "delete from community where comm_id = ? ";
            PreparedStatement pst = this.getConnection().prepareStatement(deletQuery);
            pst.setLong(1, Entity.getComm_id());
            pst.executeUpdate();

            String deleteQuery2 = "delete from county_comm where comm_id = ? " + Entity.getComm_id();
            PreparedStatement pst2 = this.getConnection().prepareStatement(deleteQuery2);
            //pst2.setLong(1, Entity.getComm_id());
            pst2.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public Community find(Long id) {

        Community comm = null;

        try {
            String selectQuery = " select * from community where comm_id =" + id;
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            //pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();

            rs.next();

            comm = new Community();
            comm.setComm_id(rs.getLong("comm_id"));
            comm.setName(rs.getString("comm_name"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return comm;

    }

    @Override
    public List<Community> findAll(int page, int pageSize) {

        List<Community> communityList = new ArrayList<>();

        int start = (page - 1) * pageSize;

        try {
            String selectQuery = "select * from community order by id limit " + pageSize + " offset " + start;
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Community tmp = new Community();
                //tmp.setOb_id(rs.getLong("ob_id"));
                tmp.setComm_id(rs.getLong("comm_id"));
                tmp.setName(rs.getString("comm_name"));
                rs.getInt("county_id");
                communityList.add(tmp);
                // System.out.println(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return communityList;

    }

    public CountyDao getCountyDao() {
        if (this.countyDao == null) {
            this.countyDao = new CountyDao();
        }
        return countyDao;
    }

    public int record() {

        int a = 0;
        try {
            String selectQuery = "select * from community";
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

    public List<Community> getCountyCommunity(Long county_id) {

        List<Community> countyCommunity = new ArrayList<>();

        try {

            String selectQuery = " select * from county_comm where county_id = " + county_id;
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            //pst.setLong(1, county_id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                countyCommunity.add(this.find(rs.getLong("comm_id")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return countyCommunity;

    }

}
