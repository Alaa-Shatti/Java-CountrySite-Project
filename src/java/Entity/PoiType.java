/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author ms
 */
public class PoiType {
    
    //private Long poi_id ;
    private Long ptype_id ;
    private String touristAttractions ;
    private String naturalBeauties ;
    private String restaurant ;
    private String layover ;
    
    private Poi poi ;

    /*public Long getPoi_id() {
        return poi_id;
    }

    public void setPoi_id(Long poi_id) {
        this.poi_id = poi_id;
    }*/

    public Long getPtype_id() {
        return ptype_id;
    }

    public void setPtype_id(Long ptype_id) {
        this.ptype_id = ptype_id;
    }

    public String getTouristAttractions() {
        return touristAttractions;
    }

    public void setTouristAttractions(String touristAttractions) {
        this.touristAttractions = touristAttractions;
    }

    public String getNaturalBeauties() {
        return naturalBeauties;
    }

    public void setNaturalBeauties(String naturalBeauties) {
        this.naturalBeauties = naturalBeauties;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getLayover() {
        return layover;
    }

    public void setLayover(String layover) {
        this.layover = layover;
    }

    
    public Poi getPoi() {
        return poi;
    }

    public void setPoi(Poi poi) {
        this.poi = poi;
    }

     
    
}
