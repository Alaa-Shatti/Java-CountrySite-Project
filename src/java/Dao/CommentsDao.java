/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entity.Comments;
import Util.ConnectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ms
 */
public class CommentsDao {

    private PoiDao poiDao;
    ConnectionManager cm = new ConnectionManager();
    Connection connect = cm.connect();

    public List<Comments> findAll() {

        List<Comments> commList = new ArrayList<>();
        try {
            String selectQuery = "select * from comments ";
            PreparedStatement pst = connect.prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Comments tmp = new Comments();
                //tmp.setPoi_id(rs.getLong("poi_id"));
                tmp.setComm_id(rs.getLong("comm_id"));
                tmp.setComment(rs.getString("comment"));
                tmp.setDate(rs.getDate("date"));
                tmp.setPoi(this.getPoiDao().find(rs.getLong("poi_id")));
                commList.add(tmp);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return commList;
    }

    public void create(Comments comment) {

        try {

            String insertQuery = "insert into comments values (?,?,?)";
            PreparedStatement pst = connect.prepareStatement(insertQuery);
            pst.setString(1, comment.getComment());
            Date sqlDate = new Date(comment.getDate().getTime());
            pst.setDate(2, sqlDate);
            pst.setLong(3, comment.getPoi().getPoi_id());
            pst.executeUpdate();

            Long poi_id = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                poi_id = gk.getLong(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /*public void update(Comments comment){
        try{
        String updateQuery = "update comments set commenr = ? , date = ? , poi_id = ? where comm_id = ?" ;
        PreparedStatement pst = connect.prepareStatement(updateQuery);
        
        pst.setString(1, comment.getComment());
        pst.setDate(2, comment.getDate());
        pst.setLong(3, comment.getPoi().getPoi_id());
        pst.setLong(4, comment.getComm_id());
        pst.executeUpdate();
        
        }catch( SQLException ex ){
            System.out.println(ex.getMessage());
        }
    }*/
    public void delete(Comments comment) {

        try {

            String deleteQuery = "delete from comments where comm_id = ? ";
            PreparedStatement pst = connect.prepareStatement(deleteQuery);
            pst.setLong(1, comment.getComm_id());
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public PoiDao getPoiDao() {
        if (this.poiDao == null) {
            this.poiDao = new PoiDao();
        }
        return poiDao;
    }

}
