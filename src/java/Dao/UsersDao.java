/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Users;
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
public class UsersDao extends AbstractDao implements Dao<Users> {

    /* public static void main (String args []){
        UsersDao UD = new UsersDao() ;
        UD.findAll();
       
    }*/
    
    private PoiDao poiDao;
    
    private GroupDao groupDao;

    public Users login(Users user) {
        Users coun = null;
        try {
            String selectQuery = "select * from Users where user_name = ? and password = ?";
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            pst.setString(1, user.getUser_name());
            pst.setString(2, user.getPassword());
            ResultSet rs = pst.executeQuery();
            //Statement st = connection.createStatement();
            //ResultSet rs = st.executeQuery("select * from oblast where ob_id = " + id);            
            if (rs.next()) {
                coun = new Users();
                coun.setUser_id(rs.getLong("user_id"));
                coun.setUser_name(rs.getString("user_name"));
                coun.setPassword(rs.getString("password"));
                coun.setGroup(this.getGroupDao().find(rs.getLong("group_id")));
                coun.setPoi(this.getPoiDao().find(rs.getLong("poi_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OblastDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return coun;

    }

    public int record() {

        int a = 0;
        try {
            String selectQuery = "select * from users";
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
    public List<Users> findAll() {

        List<Users> userList = new ArrayList<>();

        try {

            String selectQuery = "select * from users order by user_id ";
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Users tmp = new Users();
                tmp.setUser_id(rs.getLong("user_id"));
                tmp.setUser_name(rs.getString("user_name"));
                tmp.setPassword(rs.getString("password"));
                tmp.setPoi(this.getPoiDao().find(rs.getLong("poi_id")));
                tmp.setGroup(this.getGroupDao().find(rs.getLong("group_id")));

                userList.add(tmp);

                //System.out.println(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userList;

    }

    @Override
    public void create(Users Entity) {

        try {
            String insertQuery = "insert into users values (?,?,?,?)";
            PreparedStatement pst = this.getConnection().prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, Entity.getUser_name());
            pst.setString(2, Entity.getPassword());
            pst.setLong(3, Entity.getPoi().getPoi_id());
            pst.setLong(4, Entity.getGroup().getGroup_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void update(Users Entity) {

        try {

            String updateQuery = "update users set user_name = ? , password = ? , poi_id = ? , group_id = ? where user_id = ? ";
            PreparedStatement pst = this.getConnection().prepareStatement(updateQuery);
            pst.setString(1, Entity.getUser_name());
            pst.setString(2, Entity.getPassword());
            pst.setLong(3, Entity.getPoi().getPoi_id());
            pst.setLong(4, Entity.getGroup().getGroup_id());
            pst.setLong(5, Entity.getUser_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getNextException());
        }

    }

    @Override
    public void delete(Users Entity) {

        try {
            String deleteQuery = "delete from users where user_id = ? ";
            PreparedStatement pst = this.getConnection().prepareStatement(deleteQuery);
            pst.setLong(1, Entity.getUser_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public Users find(Long id) {
        Users user = null;

        try {

            String selectQuery = "select * from users where user_id = ? ";
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            pst.setLong(1, id);

            ResultSet rs = pst.executeQuery();

            rs.next();
            user = new Users();
            user.setPoi(this.getPoiDao().find(rs.getLong("poi_id")));
            //user.setPoi_id(rs.getLong("poi_id"));
            user.setUser_id(rs.getLong("user_id"));
            user.setUser_name(rs.getString("user_name"));
            user.setPassword(rs.getString("password"));

            //System.out.println(tmp);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;

    }

    @Override
    public List<Users> findAll(int page, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PoiDao getPoiDao() {
        if (this.poiDao == null) {
            this.poiDao = new PoiDao();
        }
        return poiDao;
    }

    public GroupDao getGroupDao() {
        if (this.groupDao == null)
            this.groupDao = new  GroupDao();
        return groupDao;
    }
    
    

}
