/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Dao.PoiDao;
import Entity.Poi;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ms
 */
@FacesConverter(value = "poiConverter")
public class PoiConverter implements Converter {

    private PoiDao poiDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        return this.getPoiDao().find(Long.valueOf(value));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        Poi poi = (Poi) value;
        return poi.getPoi_id().toString();

    }

    public PoiDao getPoiDao() {

        if (this.poiDao == null) {
            this.poiDao = new PoiDao();

        }
        return poiDao;
    }

}
