/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;
//import java.util.Date;

import java.util.Objects;

/**
 *
 * @author ms
 */
public class Country {

    private Long coun_id;
    private String coun_name;
    private int coun_population;

   // public Country() {
   // }

   /* public Country(int coun_id, String coun_name, int coun_population) {
        this.coun_id = coun_id;
        this.coun_name = coun_name;
        this.coun_population = coun_population;
    }*/

    public Long getCoun_id() {
        return coun_id;
    }

    public void setCoun_id(Long coun_id) {
        this.coun_id = coun_id;
    }

    public String getCoun_name() {
        return coun_name;
    }

    public void setCoun_name(String coun_name) {
        this.coun_name = coun_name;
    }

    public int getCoun_population() {
        return coun_population;
    }

    public void setCoun_population(int coun_population) {
        this.coun_population = coun_population;
    }

    /*@Override
    public String toString() {
        return "Country{" + "coun_id=" + coun_id + ", coun_name=" + coun_name + ", coun_population=" + coun_population + '}';
    }*/

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.coun_id);
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
        final Country other = (Country) obj;
        if (!Objects.equals(this.coun_id, other.coun_id)) {
            return false;
        }
        return true;
    }
    
    
}
