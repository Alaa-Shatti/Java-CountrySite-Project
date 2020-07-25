/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import Dao.CountryDao;
import Entity.Country;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ms
 */
@FacesConverter(value = "countryConverter")
public class CountryConverter implements Converter {

    private CountryDao countryDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getCountryDao().find(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Country coun = (Country) value ;
        return coun.getCoun_id().toString();
    }

    public CountryDao getCountryDao() {
        if(this.countryDao == null)
            this.countryDao = new CountryDao();
        return countryDao;
    }

    
}
