/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author ms
 */
public class Comments {
    
    //private Long poi_id ;
    private Long comm_id ;
    private String comment ;
    private Date date ;
    
    private Poi poi ;

    

    /*public Long getPoi_id() {
        return poi_id;
    }

    public void setPoi_id(Long poi_id) {
        this.poi_id = poi_id;
    }*/

    public Long getComm_id() {
        return comm_id;
    }

    public void setComm_id(Long comm_id) {
        this.comm_id = comm_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Poi getPoi() {
        return poi;
    }

    public void setPoi(Poi poi) {
        this.poi = poi;
    }
    
}
