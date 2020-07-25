/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author ms
 */
public class County {

    //private Long ob_id;
    private Long county_id;
    private String county_name;
    private int county_population;

    private Oblast oblast;

    private Community community;

    private List<Community> countyCommunity;

    /*public Long getOb_id() {
        return ob_id;
    }

    public void setOb_id(Long ob_id) {
        this.ob_id = ob_id;
    }*/
    public Long getCounty_id() {
        return county_id;
    }

    public void setCounty_id(Long county_id) {
        this.county_id = county_id;
    }

    public String getCounty_name() {
        return county_name;
    }

    public void setCounty_name(String county_name) {
        this.county_name = county_name;
    }

    public int getCounty_population() {
        return county_population;
    }

    public void setCounty_population(int county_population) {
        this.county_population = county_population;
    }

    public Oblast getOblast() {
        return oblast;
    }

    public void setOblast(Oblast oblast) {
        this.oblast = oblast;
    }

    public List<Community> getCountyCommunity() {
        return countyCommunity;
    }

    public void setCountyCommunity(List<Community> countyCommunity) {
        this.countyCommunity = countyCommunity;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    @Override
    public String toString() {
        return "County{" + "county_id=" + county_id + ", county_name=" + county_name + ", county_population=" + county_population + ", oblast=" + oblast + ", countyCommunity=" + countyCommunity + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.county_id);
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
        final County other = (County) obj;
        if (!Objects.equals(this.county_id, other.county_id)) {
            return false;
        }
        return true;
    }

}
