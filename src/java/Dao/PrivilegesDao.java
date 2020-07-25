/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Privileges;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ms
 */
public class PrivilegesDao extends AbstractDao implements Dao<Privileges>{
    
    /*public static void main(String args []){
        PrivilegesDao pd = new PrivilegesDao();
        pd.findAll();
    }*/
    
    private GroupDao groupDao;

    @Override
    public List<Privileges> findAll() {
        
        List<Privileges> privList = new ArrayList<>();

        try {
            String selectQuery = "select * from privilies order by priv_id";
            PreparedStatement pst = this.getConnection().prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Privileges tmp = new Privileges();
                //tmp.setOb_id(rs.getLong("ob_id"));
                tmp.setId(rs.getLong("priv_id"));
                tmp.setModule(rs.getString("module"));
                tmp.setC(rs.getBoolean("c"));
                tmp.setD(rs.getBoolean("d"));
                tmp.setR(rs.getBoolean("r"));
                tmp.setU(rs.getBoolean("u"));
                tmp.setGroup(this.getGroupDao().find(rs.getLong("group_id")));
                
                privList.add(tmp);
              //System.out.println(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return privList;
        
    }

    @Override
    public void create(Privileges Entity) {

        try {
            String insertQuery = "insert into privilies values (?,?,?,?,?,?)";
            PreparedStatement pst = this.getConnection().prepareStatement(insertQuery);
            pst.setString(1, Entity.getModule());
            pst.setBoolean(2, Entity.isC());
            pst.setBoolean(3, Entity.isR());
            pst.setBoolean(4, Entity.isU());
            pst.setBoolean(5, Entity.isD());
            pst.setLong(6, Entity.getGroup().getGroup_id());
            pst.executeUpdate();
            
            //Statement st = connection.createStatement();
            //st.executeUpdate("insert into county values ('"+county.getCounty_name()+"',"+county.getCounty_population()+","+selectedOblast+")");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void update(Privileges Entity) {

        try {
            String insertQuery = "update privilies set module = ? ,c = ? ,r = ?, u = ? , d = ? ,group_id = ? where priv_id = ?";
            PreparedStatement pst = this.getConnection().prepareStatement(insertQuery);
            pst.setString(1, Entity.getModule());
            pst.setBoolean(2, Entity.isC());
            pst.setBoolean(3, Entity.isR());
            pst.setBoolean(4, Entity.isU());
            pst.setBoolean(5, Entity.isD());
            pst.setLong(6, Entity.getGroup().getGroup_id());
            pst.setLong(7, Entity.getId());
            pst.executeUpdate();
            
            //System.out.println("=======================================================================");
            //Statement st = connection.createStatement();
            //st.executeUpdate("insert into county values ('"+county.getCounty_name()+"',"+county.getCounty_population()+","+selectedOblast+")");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void delete(Privileges Entity) {

        try {
            String deletQuery = "delete from privilies where priv_id = ? ";
            PreparedStatement pst = this.getConnection().prepareStatement(deletQuery);
            pst.setLong(1, Entity.getId());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public Privileges find(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Privileges> findAll(int page, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public GroupDao getGroupDao() {
        if(this.groupDao == null)
            this.groupDao = new GroupDao();
        return groupDao;
    }
    
}
