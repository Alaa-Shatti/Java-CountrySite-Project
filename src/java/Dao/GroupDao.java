/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Group;
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
public class GroupDao extends AbstractDao implements Dao<Group> {

    private UsersDao userDao;

    @Override
    public List<Group> findAll() {

        List<Group> groupList = new ArrayList<>();

        try {
            String selectQuery = "select * from groups order by group_id ";
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Group tmp = new Group();
                tmp.setGroup_id(rs.getLong("group_id"));
                tmp.setGroup_name(rs.getString("group_name"));

                groupList.add(tmp);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return groupList;

    }

    @Override
    public void create(Group Entity) {

        try {
            String insertQuery = "insert into groups values (?)";
            PreparedStatement pst = this.getConnection().prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, Entity.getGroup_name());
            pst.executeUpdate();

            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void update(Group Entity) {

        try {
            String updateQuery = "update groups set group_name = ? where group_id = ?";
            PreparedStatement pst = this.getConnection().prepareStatement(updateQuery);
            pst.setString(1, Entity.getGroup_name());
            pst.setLong(2, Entity.getGroup_id());
            pst.executeUpdate();
            
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void delete(Group Entity) {

        try {
            String deleteQuery = "delete from groups where group_id = ? ";
            PreparedStatement pst = this.getConnection().prepareStatement(deleteQuery);
            pst.setLong(1, Entity.getGroup_id());
            pst.executeUpdate();
            
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public Group find(Long id) {

        Group group = null;

        try {
            String selectQuery = " select * from groups where group_id = ? ";
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();

            rs.next();

            group = new Group();
            group.setGroup_id(rs.getLong("group_id"));
            group.setGroup_name(rs.getString("group_name"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return group;

    }

    @Override
    public List<Group> findAll(int page, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // public static void main (String args []) {
    //GroupDao gd = new GroupDao() ;
    //gd.findAll() ;
    // System.out.println(gd.findAll());
    // }
    

}
