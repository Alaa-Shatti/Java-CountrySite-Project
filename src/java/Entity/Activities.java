/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author ms
 */
public class Activities {
    
    private Long poi_id ;
    private Long activity_id ;
    private String activity_species ;
    private Date date ; 
    
    private Poi poi ;

    public Long getPoi_id() {
        return poi_id;
    }

    public void setPoi_id(Long poi_id) {
        this.poi_id = poi_id;
    }

    public Long getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Long activity_id) {
        this.activity_id = activity_id;
    }

    public String getActivity_species() {
        return activity_species;
    }

    public void setActivity_species(String activity_species) {
        this.activity_species = activity_species;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.poi_id);
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
        final Activities other = (Activities) obj;
        if (!Objects.equals(this.poi_id, other.poi_id)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "Activities{" + "poi_id=" + poi_id + ", activity_id=" + activity_id + ", activity_species=" + activity_species + ", date=" + date + ", poi=" + poi + '}';
    }

}
