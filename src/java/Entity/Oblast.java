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
public class Oblast {
    //private Long coun_id ;
    private Long ob_id;
    private String ob_name;
    private int ob_population;
    
    private Country country;

    /*public Long getCoun_id() {
        return coun_id;
    }

    public void setCoun_id(Long coun_id) {
        this.coun_id = coun_id;
    }*/

    public Long getOb_id() {
        return ob_id;
    }

    public void setOb_id(Long ob_id) {
        this.ob_id = ob_id;
    }

    public String getOb_name() {
        return ob_name;
    }

    public void setOb_name(String ob_name) {
        this.ob_name = ob_name;
    }

    public int getOb_population() {
        return ob_population;
    }

    public void setOb_population(int ob_population) {
        this.ob_population = ob_population;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Oblast{" + "ob_id=" + ob_id + ", ob_name=" + ob_name + ", ob_population=" + ob_population + ", country=" + country + '}';
    }
   
    

   

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.ob_id);
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
        final Oblast other = (Oblast) obj;
        if (!Objects.equals(this.ob_id, other.ob_id)) {
            return false;
        }
        return true;
    }  
    
}
