/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author ms
 */
public class Poi {
    
   // private Long ob_id ;
    private Long poi_id ;
    private String poi_name ;
    private long poi_tel ;
    private String poi_email;
    private String poi_aderss ;
    
    private Oblast oblast ;

    /*public Long getOb_id() {
        return ob_id;
    }

    public void setOb_id(Long ob_id) {
        this.ob_id = ob_id;
    }*/

    public Long getPoi_id() {
        return poi_id;
    }

    public void setPoi_id(Long poi_id) {
        this.poi_id = poi_id;
    }

    public String getPoi_name() {
        return poi_name;
    }

    public void setPoi_name(String poi_name) {
        this.poi_name = poi_name;
    }

    public long getPoi_tel() {
        return poi_tel;
    }

    public void setPoi_tel(long poi_tel) {
        this.poi_tel = poi_tel;
    }


    public String getPoi_email() {
        return poi_email;
    }

    public void setPoi_email(String poi_email) {
        this.poi_email = poi_email;
    }

    public String getPoi_aderss() {
        return poi_aderss;
    }

    public void setPoi_aderss(String poi_aderss) {
        this.poi_aderss = poi_aderss;
    }

    public Oblast getOblast() {
        return oblast;
    }

    public void setOblast(Oblast oblast) {
        this.oblast = oblast;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.poi_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Poi other = (Poi) obj;
        if (!Objects.equals(this.poi_id, other.poi_id)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
