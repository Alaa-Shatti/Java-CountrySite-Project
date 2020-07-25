/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Dao.CountyDao;
import Entity.County;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ms
 */
@FacesConverter (value = "countyConverter")
public class CountyConverter implements Converter {

    private CountyDao countyDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getCountyDao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        County coun =(County) value;
        return coun.getCounty_id().toString();
    }

    public CountyDao getCountyDao() {
        if (this.countyDao == null) {
            this.countyDao = new CountyDao();
        }
        return countyDao;
    }

}
